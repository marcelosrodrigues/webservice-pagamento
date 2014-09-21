package com.pmrodrigues.webservices.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Marceloo on 19/09/2014.
 */
@Embeddable
@XmlRootElement(name="NossoNumero", namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
public class NossoNumero implements Serializable {

    @Column
    @XmlElement(name="Numero" , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private Long numero;

    @Column
    @XmlElement(name="Digito" , required = true, namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private String digito;

    public Long getNumero() {
        return numero;
    }

    public String getDigito() {
        return digito;
    }
}
