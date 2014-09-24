package com.pmrodrigues.webservices.models;

import com.pmrodrigues.webservices.enums.Status;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Days;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Marceloo on 19/09/2014.
 */
@Entity
@Table(name = "ordempagamento")
public class OrdemPagamento implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Date dataVencimento;

    @Column
    private Date dataEmissao;

    @Embedded
    private Agencia agencia;

    @Embedded
    private ContaCorrente contaCorrente;

    @Column
    private String numeroDoDocumento;

    @Embedded
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

    @Column
    private String instrucoes;

    @Column
    private Date dataProcessamento;

    @Column
    private Long reemissao = 0L;

    public OrdemPagamento() {
    }

    public OrdemPagamento(Date dataVencimento, Date dataEmissao, Date dataProcessamento, Agencia agencia, ContaCorrente contaCorrente, String numeroDoDocumento, NossoNumero nossoNumero, Cedente cedente, Pagador pagador, BigDecimal valorBoleto, Integer carteira, String instrucoes) {
        this();
        this.dataProcessamento = dataProcessamento;
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
        this.instrucoes = instrucoes;
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

    public void setPagador(Pagador pagador) {
        this.pagador = pagador;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public Cedente getCedente() {
        return cedente;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String messagem) {
        this.historico = messagem;
    }

    public String getInstrucoes() {
        return instrucoes;
    }

    public Date getDataProcessamento() {
        return dataProcessamento;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setCedente(Cedente cedente) {
        this.cedente = cedente;
    }

    public Long getId() { return id; }

    public void emitirSegundaVia() {
        if(podeReemitir()) {
            DateTime hoje = DateTime.now();
            hoje = hoje.plusDays(2);
            while (hoje.getDayOfWeek() == DateTimeConstants.SATURDAY || hoje.getDayOfWeek() == DateTimeConstants.SUNDAY) {
                hoje = hoje.plusDays(1);
            }
            this.dataVencimento = hoje.toDate();
            this.reemissao++;
        }
    }

    public boolean podeReemitir() {
        return reemissao < 2;
    }
}
