package com.pmrodrigues.webservices;

import com.pmrodrigues.webservices.dto.ServiceStatus;
import com.pmrodrigues.webservices.models.*;
import com.pmrodrigues.webservices.services.PagamentoService;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Marceloo on 19/09/2014.
 */
@WebService(name = "Pagamento", portName = "Pagamento", targetNamespace = "http://services.pmrodrigues.biz/Pagamento/1.0")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@Service
public class PagamentoWS implements Serializable {

    @Resource
    private WebServiceContext context;


    @WebMethod(action = "enviar", operationName = "enviar")
    public String enviarBoleto(@WebParam(name = "DataVencimento") String dataVencimento, @WebParam(name = "DataEmissao")String dataEmissao,
                               @WebParam(name = "NumeroAgencia")String numeroAgencia, @WebParam(name = "DigitoAgencia")String digitoAgencia,
                               @WebParam(name = "NumeroContaCorrente") String numeroContaCorrente, @WebParam(name = "DigitoContaCorrente")String digitoContaCorrente,
                               @WebParam(name = "NumeroBoleto")String numeroBoleto,@WebParam(name = "NossoNumero") String nossoNumero,
                               @WebParam(name = "DigitoNossoNumero")String digitoNossoNumero, @WebParam(name = "Emissor") String nomeEmissor,
                               @WebParam(name = "SacadoCPF")String sacadoCPF, @WebParam(name = "NomeSacado")String nomeSacado,
                               @WebParam(name = "EnderecoSacado") String enderecoSacado, @WebParam(name = "BairroSacado") String bairroSacado,
                               @WebParam(name = "CEPSacado") String cepSacado, @WebParam(name = "CidadeSacado") String cidadeSacado,
                               @WebParam(name = "EstadoSacado") String estadoSacado, @WebParam(name = "EmailSacado")String emailSacado,
                               @WebParam(name = "ValorBoleto") String valorBoleto, @WebParam(name = "Carteira") String carteira,
                               @WebParam(name = "Instrucoes") String instrucoes, @WebParam(name = "DataProcessamento") String dataProcessamento) throws Exception {


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",new Locale("pt","BR"));
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance(new Locale("pt","BR"));
        decimalFormat.applyPattern("#,##");

        OrdemPagamento ordemPagamento = new OrdemPagamento(dateFormat.parse(dataVencimento), dateFormat.parse(dataEmissao),dateFormat.parse(dataProcessamento),
                                                            new Agencia(Integer.parseInt(numeroAgencia),digitoAgencia),
                                                            new ContaCorrente(Long.parseLong(numeroContaCorrente),digitoContaCorrente), numeroBoleto,
                                                            new NossoNumero(Long.parseLong(nossoNumero),digitoNossoNumero),new Cedente(nomeEmissor),
                                                            new Pagador(sacadoCPF,nomeSacado ,new Endereco(enderecoSacado,bairroSacado,cepSacado,cidadeSacado,estadoSacado),emailSacado),
                                                            new BigDecimal(decimalFormat.parse(valorBoleto).doubleValue()), Integer.parseInt(carteira), instrucoes);
        ServletContext context =
                (ServletContext) this.context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        PagamentoService service = (PagamentoService) WebApplicationContextUtils.getWebApplicationContext(context).getBean("pagamentoService");

        ordemPagamento = service.enviarBoleto(ordemPagamento);


        return ordemPagamento.getStatus().toString();
    }

}


