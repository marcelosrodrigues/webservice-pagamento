package com.pmrodrigues.webservices;

import com.pmrodrigues.webservices.dto.ServiceStatus;
import com.pmrodrigues.webservices.dto.Status;
import com.pmrodrigues.webservices.models.OrdemPagamento;
import com.pmrodrigues.webservices.services.PagamentoService;
import org.apache.commons.mail.EmailException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.BindingType;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Marceloo on 19/09/2014.
 */
@WebService(name = "Pagamento" , portName = "Pagamento" , targetNamespace = "http://services.pmrodrigues.biz/Pagamento/1.0")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class PagamentoWS implements Serializable {

    @WebMethod(action = "enviar" , operationName = "enviar")
    public ServiceStatus enviarBoleto(@WebParam(name = "Boleto", mode = WebParam.Mode.IN, targetNamespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0") OrdemPagamento ordemPagamento) throws IOException, EmailException {
        PagamentoService service = new PagamentoService();
        service.enviarBoleto(ordemPagamento);

        return new ServiceStatus(Status.SUCESSO , "Boleto enviado com sucesso");

    }

}
