package com.pmrodrigues.webservices.models;

import com.pmrodrigues.webservices.enums.Status;
import org.hibernate.annotations.Cascade;
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

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Cedente cedente;

    @ManyToOne(optional = false,cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
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

    @Column
    private String contrato;

    @Column
    private String operadora;

    @Column
    private String parcela;

    @Column
    private String banco;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;

    @PrePersist
    public void onInsert() {
        dataCriacao = DateTime.now().toDate();
        dataAlteracao = DateTime.now().toDate();
    }

    @PreUpdate
    public void onUpdate() {
        dataAlteracao = DateTime.now().toDate();
    }


    public OrdemPagamento(){}

    public OrdemPagamento(Date dataVencimento, Date dataEmissao, Date dataProcessamento, Agencia agencia, ContaCorrente contaCorrente, String numeroDoDocumento, NossoNumero nossoNumero, Cedente cedente, Pagador pagador, BigDecimal valorBoleto, Integer carteira, String instrucoes, String contrato, String operadora, String parcela, String banco) {
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
        this.contrato = contrato;
        this.operadora = operadora;
        this.parcela = parcela;
        this.banco = banco;

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

    public void setCarteira(Integer carteira) {
        this.carteira = carteira;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public void setDataProcessamento(Date dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setInstrucoes(String instrucoes) {
        this.instrucoes = instrucoes;
    }

    public void setNossoNumero(NossoNumero nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    public void setNumeroDoDocumento(String numeroDoDocumento) {
        this.numeroDoDocumento = numeroDoDocumento;
    }

    public void setValorBoleto(BigDecimal valorBoleto) {
        this.valorBoleto = valorBoleto;
    }

    public void permitirReemissao() {
        this.reemissao = 0L;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }
}
