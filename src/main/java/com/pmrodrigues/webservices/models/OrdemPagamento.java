package com.pmrodrigues.webservices.models;

import com.pmrodrigues.webservices.enums.Status;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Marceloo on 19/09/2014.
 */
@Entity
@Table(name = "OrdemPagamento")
@XmlRootElement(name = "Boleto" , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
public class OrdemPagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @XmlElement(name = "DataVencimento" , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private Date dataVencimento;

    @Column
    @XmlElement(name = "DataEmissao"  , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private Date dataEmissao;

    @Column
    @XmlElement(name = "Agencia"  , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private Agencia agencia;

    @Column
    @XmlElement(name = "ContaCorrente"  , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private ContaCorrente contaCorrente;

    @Column
    @XmlElement(name = "NumeroBoleto"  , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private String numeroDoDocumento;

    @Column
    @XmlElement(name = "NossoNumero"  , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private NossoNumero nossoNumero;

    @ManyToOne(optional = false,cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @XmlElement(name = "Emissor"  , required = true, namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private Cedente cedente;

    @ManyToOne(optional = false,cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @XmlElement(name = "Destinatario"  , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private Pagador pagador;

    @Column
    @XmlElement(name = "ValorBoleto"  , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private BigDecimal valorBoleto;

    @Column
    @XmlElement(name = "Carteira" , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private Integer carteira;

    @Column
    private String historico;

    @Enumerated
    private Status status;

    public BigDecimal getValorBoleto() {
        return valorBoleto;
    }

    public String getNumeroDoDocumento() {
        return numeroDoDocumento;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public Integer getCarteira() {
        return carteira;
    }

    public ContaCorrente getContaCorrente() {
        return this.contaCorrente;
    }

    public NossoNumero getNossoNumero() {
        return nossoNumero;
    }

    public Pagador getPagador() {
        return pagador;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public Cedente getCedente() {
        return cedente;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setHistorico(String messagem) {
        this.historico = messagem;
    }

    public Status getStatus() {
        return status;
    }

    public String getHistorico() {
        return historico;
    }

    public void setPagador(Pagador pagador) {
        this.pagador = pagador;
    }
}
