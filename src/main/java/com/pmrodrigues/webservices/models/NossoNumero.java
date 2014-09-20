package com.pmrodrigues.webservices.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Marceloo on 19/09/2014.
 */
@XmlRootElement(name="NossoNumero", namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
public class NossoNumero implements Serializable {

    @XmlElement(name="Numero" , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private Long numero;

    @XmlElement(name="Digito" , required = true, namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private String digito;

    public Long getNumero() {
        return numero;
    }

    public String getDigito() {
        return digito;
    }
}
