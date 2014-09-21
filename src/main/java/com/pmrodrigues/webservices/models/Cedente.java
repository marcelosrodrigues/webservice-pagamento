package com.pmrodrigues.webservices.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Marceloo on 19/09/2014.
 */
@Entity
@Table(name = "Cedente")
public class Cedente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    public Cedente(String nome) {
        this();
        this.nome = nome;
    }

    public Cedente() {}

    public String getNome() {
        return nome;
    }
}
