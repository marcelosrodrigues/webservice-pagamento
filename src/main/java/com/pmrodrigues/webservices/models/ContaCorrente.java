package com.pmrodrigues.webservices.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Marceloo on 19/09/2014.
 */
@Embeddable
public class ContaCorrente implements Serializable {

    @Column(name = "numeroContaCorrente")
    private Long numero;

    @Column(name = "digitoContaCorrente")
    private String digito;

    public ContaCorrente(Long numero, String digito) {
        this();
        this.numero = numero;
        this.digito = digito;
    }

    public ContaCorrente() {}

    public Long getNumero() {
        return numero;
    }

    public char getDigito() {
        if( !"".equalsIgnoreCase(digito) && digito !=null ) {
            return digito.charAt(0);
        } else {
            return ' ';
        }
    }
}
