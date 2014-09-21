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
@XmlRootElement(name="Endereco" , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
public class Endereco implements Serializable {

    @Column
    @XmlElement(name="Rua" , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private String logradouro;

    @Column
    @XmlElement(name="Bairro" , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private String bairro;

    @Column
    @XmlElement(name="CEP" , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private String cep;

    @Column
    @XmlElement(name="Cidade" , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private String cidade;

    @Column
    @XmlElement(name="Estado" , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private String uf;

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }
}
