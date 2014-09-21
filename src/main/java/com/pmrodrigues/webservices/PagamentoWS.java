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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Marceloo on 19/09/2014.
 */
@WebService(name = "Pagamento", portName = "Pagamento", targetNamespace = "http://services.pmrodrigues.biz/Pagamento/1.0")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class PagamentoWS implements Serializable {

    @WebMethod(action = "enviar", operationName = "enviar")
    public String enviarBoleto(@WebParam(name = "DataVencimento") String dataVencimento, @WebParam(name = "DataEmissao")String dataEmissao,
                               @WebParam(name = "NumeroAgencia")Integer numeroAgencia, @WebParam(name = "DigitoAgencia")char digitoAgencia,
                               @WebParam(name = "NumeroContaCorrente") Long numeroContaCorrente, @WebParam(name = "DigitoContaCorrente")char digitoContaCorrente,
                               @WebParam(name = "NumeroBoleto")String numeroBoleto,@WebParam(name = "NossoNumero") Long nossoNumero,
                               @WebParam(name = "DigitoNossoNumero")String digitoNossoNumero, @WebParam(name = "Emissor") String nomeEmissor,
                               @WebParam(name = "SacadoCPF")String sacadoCPF, @WebParam(name = "NomeSacado")String nomeSacado,
                               @WebParam(name = "EnderecoSacado") String enderecoSacado, @WebParam(name = "BairroSacado") String bairroSacado,
                               @WebParam(name = "CEPSacado") String cepSacado, @WebParam(name = "CidadeSacado") String cidadeSacado,
                               @WebParam(name = "EstadoSacado") String estadoSacado, @WebParam(name = "EmailSacado")String emailSacado,
                               @WebParam(name = "ValorBoleto") String valorBoleto, @WebParam(name = "Carteira") Integer carteira) throws Exception {


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",new Locale("pt","BR"));
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance(new Locale("pt","BR"));
        decimalFormat.applyPattern("#,##");

        PagamentoService service = new PagamentoService();
        OrdemPagamento ordemPagamento = new OrdemPagamento(dateFormat.parse(dataVencimento), dateFormat.parse(dataEmissao),
                                                            new Agencia(numeroAgencia,digitoAgencia),
                                                            new ContaCorrente(numeroContaCorrente,digitoContaCorrente), numeroBoleto,
                                                            new NossoNumero(nossoNumero,digitoNossoNumero),new Cedente(nomeEmissor),
                                                            new Pagador(sacadoCPF,nomeSacado ,new Endereco(enderecoSacado,bairroSacado,cepSacado,cidadeSacado,estadoSacado),emailSacado),
                                                            new BigDecimal(decimalFormat.parse(valorBoleto).doubleValue()), carteira);

        ordemPagamento = service.enviarBoleto(ordemPagamento);


        return ordemPagamento.getStatus().toString();
    }

}


