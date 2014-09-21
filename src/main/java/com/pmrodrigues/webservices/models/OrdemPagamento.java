package com.pmrodrigues.webservices.models;

import com.pmrodrigues.webservices.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Marceloo on 19/09/2014.
 */
@Entity
@Table(name = "OrdemPagamento")
public class OrdemPagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date dataVencimento;

    @Column
    private Date dataEmissao;

    @Embedded( { @AttributeOverride(name = "digito" , column = "digito_agencia") ,
            @AttributeOverride(name = "numero" , column = "numero_agencia")} )
    private Agencia agencia;

    @Embedded( { @AttributeOverride(name = "digito" , column = "digito_contacorrente") ,
                 @AttributeOverride(name = "numero" , column = "numero_contacorrente")} )
    private ContaCorrente contaCorrente;

    @Column
    private String numeroDoDocumento;

    @Embedded( { @AttributeOverride(name = "digito" , column = "digito_nossonumero") ,
            @AttributeOverride(name = "numero" , column = "nossonumero")} )
    private NossoNumero nossoNumero;

    @ManyToOne(optional = false, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Cedente cedente;

    @ManyToOne(optional = false, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Pagador pagador;

    @Column
    private BigDecimal valorBoleto;

    @Column
    private Integer carteira;

    @Column
    private String historico;

    @Enumerated
    private Status status;

    public OrdemPagamento() {
    }

    public OrdemPagamento(Date dataVencimento, Date dataEmissao, Agencia agencia, ContaCorrente contaCorrente, String numeroDoDocumento, NossoNumero nossoNumero, Cedente cedente, Pagador pagador, BigDecimal valorBoleto, Integer carteira) {
        this();
        this.dataVencimento = dataVencimento;
        this.dataEmissao = dataEmissao;
        this.agencia = agencia;
        this.contaCorrente = contaCorrente;
        this.numeroDoDocumento = numeroDoDocumento;
        this.nossoNumero = nossoNumero;
        this.cedente = cedente;
        this.pagador = pagador;
        this.valorBoleto = valorBoleto;
        this.carteira = carteira;
    }

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
