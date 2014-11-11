package com.pmrodrigues.webservices.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Marceloo on 19/09/2014.
 */
@Entity
@Table(name = "cedente")
public class Cedente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true )
    private String nome;

    public Cedente(String nome) {
        this();
        this.nome = nome;
    }

    public Cedente() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
