package com.pmrodrigues.webservices;

import com.pmrodrigues.webservices.dto.ServiceStatus;
import com.pmrodrigues.webservices.models.*;
import com.pmrodrigues.webservices.services.PagamentoService;
import org.apache.commons.mail.EmailException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Marceloo on 19/09/2014.
 */
@WebService(name = "Pagamento", portName = "Pagamento", targetNamespace = "http://services.pmrodrigues.biz/Pagamento/1.0")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class PagamentoWS implements Serializable {

    @WebMethod(action = "enviar", operationName = "enviar")
    public String enviarBoleto(Date dataVencimento, Date dataEmissao, Integer numeroAgencia, char digitoAgencia,
                                      Long numeroContaCorrente, char digitoContaCorrente, String numeroBoleto,
                                      Long nossoNumero, String digitoNossoNumero, String nomeEmissor,
                                      String sacadoCPF, String nomeSacado, String enderecoSacado, String bairroSacado,
                                      String cepSacado, String cidadeSacado, String estadoSacado, String emailSacado,
                                      BigDecimal valorBoleto, Integer carteira) throws IOException, EmailException {

        PagamentoService service = new PagamentoService();
        OrdemPagamento ordemPagamento = new OrdemPagamento(dataVencimento, dataEmissao,
                                                            new Agencia(numeroAgencia,digitoAgencia),
                                                            new ContaCorrente(numeroContaCorrente,digitoContaCorrente), numeroBoleto,
                                                            new NossoNumero(nossoNumero,digitoNossoNumero),new Cedente(nomeEmissor),
                                                            new Pagador(sacadoCPF,nomeSacado ,new Endereco(enderecoSacado,bairroSacado,cepSacado,cidadeSacado,estadoSacado),emailSacado),
                                                            valorBoleto, carteira);

        return ordemPagamento.getStatus().toString();
    }

}


