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
@XmlRootElement(name="Emissor", namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
public class Cedente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @XmlElement(name="Nome" , required = true, namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private String nome;

    public String getNome() {
        return nome;
    }
}
