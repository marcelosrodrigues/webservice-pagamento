package com.pmrodrigues.webservices.models;

import javax.jws.WebParam;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Marceloo on 19/09/2014.
 */
@Embeddable
public class Agencia implements Serializable{

    @Column(name = "numeroAgencia")
    private Integer numero;

    @Column(name = "digitoAgencia")
    private char digito;

    public Agencia() {}

    public Agencia(Integer numero, char digito) {
        this();
        this.numero = numero;
        this.digito = digito;
    }

    public Integer getNumero() {
        return numero;
    }

    public char getDigito() {
        return digito;
    }


}
