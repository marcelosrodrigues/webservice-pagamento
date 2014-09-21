package com.pmrodrigues.webservices;

import com.pmrodrigues.webservices.dto.ServiceStatus;
import com.pmrodrigues.webservices.models.*;
import com.pmrodrigues.webservices.services.PagamentoService;
import org.apache.commons.mail.EmailException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
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
    public String enviarBoleto(@WebParam(name = "DataVencimento") Date dataVencimento, @WebParam(name = "DataEmissao")Date dataEmissao,
                               @WebParam(name = "NumeroAgencia")Integer numeroAgencia, @WebParam(name = "DigitoAgencia")char digitoAgencia,
                               @WebParam(name = "NumeroContaCorrente") Long numeroContaCorrente, @WebParam(name = "DigitoContaCorrente")char digitoContaCorrente,
                               @WebParam(name = "NumeroBoleto")String numeroBoleto,@WebParam(name = "NossoNumero") Long nossoNumero,
                               @WebParam(name = "DigitoNossoNumero")String digitoNossoNumero, @WebParam(name = "Emissor") String nomeEmissor,
                               @WebParam(name = "SacadoCPF")String sacadoCPF, @WebParam(name = "NomeSacado")String nomeSacado,
                               @WebParam(name = "EnderecoSacado") String enderecoSacado, @WebParam(name = "BairroSacado") String bairroSacado,
                               @WebParam(name = "CEPSacado") String cepSacado, @WebParam(name = "CidadeSacado") String cidadeSacado,
                               @WebParam(name = "EstadoSacado") String estadoSacado, @WebParam(name = "EmailSacado")String emailSacado,
                               @WebParam(name = "ValorBoleto") BigDecimal valorBoleto, @WebParam(name = "Carteira") Integer carteira) throws IOException, EmailException {

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


