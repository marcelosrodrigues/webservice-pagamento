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
public class NossoNumero implements Serializable {

    @Column
    private Long numero;

    @Column
    private String digito;

    public NossoNumero(Long numero, String digito) {
        this();
        this.numero = numero;
        this.digito = digito;
    }

    public NossoNumero() {}

    public Long getNumero() {
        return numero;
    }

    public String getDigito() {
        return digito;
    }
}
