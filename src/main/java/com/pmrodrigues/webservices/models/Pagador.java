package com.pmrodrigues.webservices.models;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

import static org.apache.commons.lang.RandomStringUtils.*;

/**
 * Created by Marceloo on 19/09/2014.
 */
@Entity
@Table(name= "pagador")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Pagador implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String cpf;

    @Column
    private String nome;

    @Embedded
    private Endereco endereco;

    @Column
    private String email;

    @Column
    private String password;

    public Pagador(String cpf, String nome, Endereco endereco, String email) {
        this();
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
    }

    public Pagador() {}

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

    public String reemitirSenha() {
        this.password = randomAlphanumeric(8);
        return this.password;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() { return id;}
}
