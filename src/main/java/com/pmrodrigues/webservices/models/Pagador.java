package com.pmrodrigues.webservices.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Marceloo on 19/09/2014.
 */
@Entity
@Table(name= "Pagador")
@XmlRootElement(name="Destinatario", namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
public class Pagador implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @XmlElement(name="CPF" , required = true, namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private String cpf;

    @Column
    @XmlElement(name="Nome" , required = true, namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private String nome;

    @Column
    @XmlElement(name="Endereco" , required = true, namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private Endereco endereco;

    @Column
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
