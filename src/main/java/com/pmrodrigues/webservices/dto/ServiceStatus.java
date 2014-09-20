package com.pmrodrigues.webservices.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marceloo on 19/09/2014.
 */
@XmlRootElement(name = "ServiceStatus" , namespace = "http://services.pmrodrigues.biz/Pagamento/1.0")
public class ServiceStatus implements Serializable {

    @XmlElement( name = "Status" , namespace = "http://services.pmrodrigues.biz/Pagamento/1.0")
    private Status status;

    @XmlElement(name = "Data" , required = true, namespace = "http://services.pmrodrigues.biz/Pagamento/1.0" )
    private Date data = new Date();

    @XmlElement(name = "Messagem" , required = true, namespace = "http://services.pmrodrigues.biz/Pagamento/1.0")
    private String message;

    public ServiceStatus() {}
    public ServiceStatus(Status status , String message ){
        this.status = status;
        this.message = message;
    }
}
