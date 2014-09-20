package com.pmrodrigues.webservices.models;

import javax.jws.WebParam;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Marceloo on 19/09/2014.
 */
@XmlRootElement(name="Agencia", namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
public class Agencia implements Serializable{

    @XmlElement(name="Numero" , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private Integer numero;

    @XmlElement(name="Digito" , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private char digito;

    public Integer getNumero() {
        return numero;
    }

    public char getDigito() {
        return digito;
    }

}
