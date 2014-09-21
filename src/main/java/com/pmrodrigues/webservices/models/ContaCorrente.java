package com.pmrodrigues.webservices.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Marceloo on 19/09/2014.
 */
@Embeddable
@XmlRootElement(name="ContaCorrente" , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
public class ContaCorrente implements Serializable {

    @Column
    @XmlElement(name="Numero" , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private Long numero;

    @Column
    @XmlElement(name="Digito" , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private char digito;

    public Long getNumero() {
        return numero;
    }

    public char getDigito() {
        return digito;
    }
}
