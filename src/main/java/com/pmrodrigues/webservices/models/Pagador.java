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
public class Pagador implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cpf;

    @Column
    private String nome;

    @Column
    private Endereco endereco;

    @Column
    private String email;

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
}
