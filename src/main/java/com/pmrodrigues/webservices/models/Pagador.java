package com.pmrodrigues.webservices.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Marceloo on 19/09/2014.
 */
@XmlRootElement(name="Destinatario", namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
public class Pagador implements Serializable{

    @XmlElement(name="CPF" , required = true, namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private String cpf;

    @XmlElement(name="Nome" , required = true, namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private String nome;

    @XmlElement(name="Endereco" , required = true, namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private Endereco endereco;

    @XmlElement(name="Email" , required = true, namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private String email;

    public String getCPF() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }
}
