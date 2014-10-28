package com.pmrodrigues.webservices.services;


import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Emissor;
import br.com.caelum.stella.boleto.Sacado;
import br.com.caelum.stella.boleto.bancos.Itau;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;
import com.pmrodrigues.webservices.enums.Status;
import com.pmrodrigues.webservices.models.OrdemPagamento;
import com.pmrodrigues.webservices.repositories.OrdemPagamentoRepository;
import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.beans.Transient;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

/**
 * Created by Marceloo on 19/09/2014.
 */
@Service
public class PagamentoService {

    private SendMail email = new SendMail();

    @Resource(name = "OrdemPagamentoRepository")
    private OrdemPagamentoRepository repository;

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

    public OrdemPagamento prepareOrdemServico(OrdemPagamento ordemPagamento) {
        OrdemPagamento existed = repository.findByNumeroDocumento(ordemPagamento.getNumeroDoDocumento());

        if( existed != null ) {
            existed.setAgencia(ordemPagamento.getAgencia());
            existed.setCedente(ordemPagamento.getCedente());
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
            existed.permitirReemissao();
            repository.set(existed);
            return existed;
        } else {
            repository.add(ordemPagamento);
            return ordemPagamento;
        }
    }

    private void enviar(InputStream boleto, OrdemPagamento ordemPagamento) throws IOException, EmailException, MessagingException {
        email.from("qualivida@qualividabeneficios.com.br")
             .to(ordemPagamento.getPagador().getEmail())
             .subject("Segue anexo seu boleto para pagamento")
             .message("Segue anexo seu boleto para pagamento")
             .attachament(boleto)
             .send();
    }

    public InputStream gerarBoleto(OrdemPagamento ordemPagamento) {

        Emissor emissor = criarEmissor(ordemPagamento);
        Sacado sacado = criarPagador(ordemPagamento);
        Datas dataVencimento = criarDatasBoleto(ordemPagamento);

        Boleto boleto = criarBoleto(ordemPagamento, emissor, sacado, dataVencimento);
        boleto.comBanco(new Itau());

        String[] instrucoes = ordemPagamento.getInstrucoes().split("#");
        boleto.comInstrucoes(instrucoes);

        GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);
        return new ByteArrayInputStream(gerador.geraPDF());

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
                     .comCpf(ordemPagamento.getPagador().getCPF())
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
