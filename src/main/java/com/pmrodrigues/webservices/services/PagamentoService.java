package com.pmrodrigues.webservices.services;


import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Emissor;
import br.com.caelum.stella.boleto.Sacado;
import br.com.caelum.stella.boleto.bancos.Bradesco;
import br.com.caelum.stella.boleto.bancos.Itau;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;
import com.pmrodrigues.webservices.enums.Status;
import com.pmrodrigues.webservices.models.Cedente;
import com.pmrodrigues.webservices.models.OrdemPagamento;
import com.pmrodrigues.webservices.models.Pagador;
import com.pmrodrigues.webservices.repositories.CedenteRepository;
import com.pmrodrigues.webservices.repositories.OrdemPagamentoRepository;
import com.pmrodrigues.webservices.repositories.PagadorRepository;
import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Marceloo on 19/09/2014.
 */
@Service
public class PagamentoService {

    private SendMail email = new SendMail();

    @Resource(name = "OrdemPagamentoRepository")
    private OrdemPagamentoRepository repository;

    @Resource(name = "PagadorRepository")
    private PagadorRepository pagadorRepository;

    @Resource( name = "CedenteRepository")
    private CedenteRepository cedenteRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public OrdemPagamento enviarBoleto(final OrdemPagamento ordemPagamento)  {

        OrdemPagamento ordem = prepareOrdemServico(ordemPagamento);

        try {
            InputStream boleto = gerarBoleto(ordem);
            enviar(boleto,ordem);
            ordem.setStatus(Status.SUCESSO);
            ordem.setHistorico("Boleto enviado com sucesso");
        } catch (IOException e) {
            ordem.setStatus(Status.ERRO);
            ordem.setHistorico(e.getMessage());
        } catch (EmailException e) {
            ordem.setStatus(Status.ERRO);
            ordem.setHistorico(e.getMessage());
        } catch (MessagingException e) {
            ordem.setStatus(Status.ERRO);
            ordem.setHistorico(e.getMessage());
        }

        return ordem;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public OrdemPagamento prepareOrdemServico(OrdemPagamento ordemPagamento) {

        Cedente cedente = cedenteRepository.findCedenteByName(ordemPagamento.getCedente().getNome());
        Pagador pagador = pagadorRepository.getPagadorByCPF(ordemPagamento.getPagador().getCpf());

        if( cedente != null ) {
            ordemPagamento.setCedente(cedente);
        }

        if( pagador != null ){
            pagador.setEmail(ordemPagamento.getPagador().getEmail());
            pagador.setEndereco(ordemPagamento.getPagador().getEndereco());
            pagador.setNome(ordemPagamento.getPagador().getNome());
            ordemPagamento.setPagador(pagador);
        }

        OrdemPagamento existed = repository.findByNumeroDocumento(ordemPagamento.getNumeroDoDocumento());

        if( existed != null ) {
            existed.setAgencia(ordemPagamento.getAgencia());
            existed.setCedente(cedente);
            existed.setCarteira(ordemPagamento.getCarteira());
            existed.setContaCorrente(ordemPagamento.getContaCorrente());
            existed.setDataEmissao(ordemPagamento.getDataEmissao());
            existed.setDataProcessamento(ordemPagamento.getDataProcessamento());
            existed.setDataVencimento(ordemPagamento.getDataVencimento());
            existed.setInstrucoes(ordemPagamento.getInstrucoes());
            existed.setNossoNumero(ordemPagamento.getNossoNumero());
            existed.setNumeroDoDocumento(ordemPagamento.getNumeroDoDocumento());
            existed.setStatus(ordemPagamento.getStatus());
            existed.setValorBoleto(ordemPagamento.getValorBoleto());
            existed.setHistorico(ordemPagamento.getHistorico());
            existed.setPagador(ordemPagamento.getPagador());
            existed.setBanco(ordemPagamento.getBanco());
            existed.setParcela(ordemPagamento.getParcela());
            existed.setContrato(ordemPagamento.getContrato());
            existed.setOperadora(ordemPagamento.getOperadora());
            existed.permitirReemissao();
            repository.set(existed);
            return existed;
        } else {
            repository.add(ordemPagamento);
            return ordemPagamento;
        }
    }

    private void enviar(InputStream boleto, OrdemPagamento ordemPagamento) throws IOException, EmailException, MessagingException {


        if( ordemPagamento.podeEnviar() ) {
            email.from("marsilvarodrigues@gmail.com")
                    .to(ordemPagamento.getPagador().getEmail())
                    .cc("romeubf@gmail.com")
                    .subject("Segue anexo seu boleto para pagamento")
                    .message("Segue anexo seu boleto para pagamento")
                    .attachament(boleto)
                    .send();
        }
    }

    public InputStream gerarBoleto(OrdemPagamento ordemPagamento) {

        Boleto boleto = getBoleto(ordemPagamento);

        GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);
        return new ByteArrayInputStream(gerador.geraPDF());

    }

    public Boleto getBoleto(OrdemPagamento ordemPagamento) {
        Emissor emissor = criarEmissor(ordemPagamento);
        Sacado sacado = criarPagador(ordemPagamento);
        Datas dataVencimento = criarDatasBoleto(ordemPagamento);

        Boleto boleto = criarBoleto(ordemPagamento, emissor, sacado, dataVencimento);
        if( "1".equalsIgnoreCase(ordemPagamento.getBanco()) ){
            boleto.comBanco(new Itau());
        } else if( "2".equalsIgnoreCase(ordemPagamento.getBanco()) ){
            boleto.comBanco(new Bradesco());
        } else {
            boleto.comBanco(new Itau());
        }

        String[] instrucoes = ordemPagamento.getInstrucoes().split("#");
        boleto.comInstrucoes(instrucoes);
        return boleto;
    }

    public void gerarBoletos(final List<OrdemPagamento> ordemPagamentos) {


        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {

                ByteArrayOutputStream output = new ByteArrayOutputStream();
                for (final OrdemPagamento ordem : ordemPagamentos) {
                    GeradorDeBoleto gerador = new GeradorDeBoleto(getBoleto(ordem));
                    output.write(gerador.geraPDF());
                    System.gc();
                }
                //GeradorDeBoleto gerador = new GeradorDeBoleto(boletos.toArray(new Boleto[]{}));
                //return new ByteArrayInputStream(gerador.geraPDF());
                //return new ByteArrayInputStream(output.toByteArray());

                email.from("marsilvarodrigues@gmail.com")
                        //.to("qualivida@qualividabeneficios.com.br")
                        .to("marsilvarodrigues@gmail.com")
                        .cc("romeubf@gmail.com")
                        .subject("Segue os boletos gerados")
                        .message("Seguem anexos os boletos para impressão")
                        .attachament(new ByteArrayInputStream(output.toByteArray()))
                        .send();
                return null;
            }

        });


    }

    private Boleto criarBoleto(OrdemPagamento ordemPagamento, Emissor emissor, Sacado sacado, Datas dataVencimento) {
        return Boleto.novoBoleto()
                              .comValorBoleto(ordemPagamento.getValorBoleto())
                              .comNumeroDoDocumento(ordemPagamento.getNumeroDoDocumento())
                              .comEmissor(emissor)
                              .comSacado(sacado)
                              .comDatas(dataVencimento);
    }

    private Datas criarDatasBoleto(OrdemPagamento ordemPagamento) {
        Calendar vencimento = Calendar.getInstance();
        vencimento.setTime(ordemPagamento.getDataVencimento());

        Calendar emissao = Calendar.getInstance();
        emissao.setTime(ordemPagamento.getDataEmissao());

        Calendar processamento = Calendar.getInstance();
        processamento.setTime(ordemPagamento.getDataProcessamento());

        return Datas.novasDatas()
                    .comVencimento(vencimento)
                    .comDocumento(emissao)
                    .comProcessamento(processamento);
    }

    private Sacado criarPagador(OrdemPagamento ordemPagamento) {
        return Sacado.novoSacado()
                     .comCpf(ordemPagamento.getPagador().getCpf())
                     .comNome(ordemPagamento.getPagador().getNome())
                     .comEndereco(ordemPagamento.getPagador().getEndereco().getLogradouro())
                     .comBairro(ordemPagamento.getPagador().getEndereco().getBairro())
                     .comCep(ordemPagamento.getPagador().getEndereco().getCep())
                     .comCidade(ordemPagamento.getPagador().getEndereco().getCidade())
                     .comUf(ordemPagamento.getPagador().getEndereco().getUf());
    }

    private Emissor criarEmissor(OrdemPagamento ordemPagamento) {
        return Emissor.novoEmissor()
                      .comCedente(ordemPagamento.getCedente().getNome())
                      .comAgencia(ordemPagamento.getAgencia().getNumero())
                      .comDigitoAgencia(ordemPagamento.getAgencia().getDigito())
                      .comCarteira(ordemPagamento.getCarteira())
                      .comContaCorrente(ordemPagamento.getContaCorrente().getNumero())
                      .comDigitoContaCorrente(ordemPagamento.getContaCorrente().getDigito())
                      .comNossoNumero(ordemPagamento.getNossoNumero().getNumero())
                      .comDigitoNossoNumero(ordemPagamento.getNossoNumero().getDigito());
    }

}
