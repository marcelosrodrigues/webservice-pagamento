package com.pmrodrigues.webservices.services;

import br.com.caelum.stella.boleto.*;
import br.com.caelum.stella.boleto.bancos.Itau;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;
import com.pmrodrigues.webservices.models.OrdemPagamento;
import com.sun.xml.messaging.saaj.util.ByteInputStream;
import org.apache.commons.mail.EmailException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

/**
 * Created by Marceloo on 19/09/2014.
 */
public class PagamentoService {

    public void enviarBoleto(OrdemPagamento ordemPagamento) throws IOException, EmailException {
        InputStream boleto = gerarBoleto(ordemPagamento);
        enviar(boleto,ordemPagamento);
    }

    private void enviar(InputStream boleto, OrdemPagamento ordemPagamento) throws IOException, EmailException {
        new SendMail()
                .setSmtpServer("smtp.gmail.com")
                .setSmtpPort(587)
                .needAutentication(true)
                .useSSL(true)
                .username("marsilvarodrigues@gmail.com")
                .password("powerslave")
                .from("marsilvarodrigues@gmail.com")
                .to(ordemPagamento.getPagador().getEmail())
                .subject("Segue anexo seu boleto para pagamento")
                .message("Segue anexo seu boleto para pagamento")
                .attachament(boleto)
                .send();
    }

    private InputStream gerarBoleto(OrdemPagamento ordemPagamento) {

        Emissor emissor = criarEmissor(ordemPagamento);
        Sacado sacado = criarPagador(ordemPagamento);
        Datas dataVencimento = criarDatasBoleto(ordemPagamento);

        Boleto boleto = criarBoleto(ordemPagamento, emissor, sacado, dataVencimento);
        boleto.comBanco(new Itau());

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
        emissao.setTime(ordemPagamento.getDataVencimento());

        return Datas.novasDatas()
                                    .comVencimento(vencimento)
                                    .comDocumento(emissao);
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
