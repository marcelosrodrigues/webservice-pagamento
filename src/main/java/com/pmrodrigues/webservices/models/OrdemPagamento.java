package com.pmrodrigues.webservices.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Marceloo on 19/09/2014.
 */
@XmlRootElement(name = "Boleto" , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
public class OrdemPagamento implements Serializable {

    @XmlElement(name = "DataVencimento" , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private Date dataVencimento;

    @XmlElement(name = "DataEmissao"  , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private Date dataEmissao;

    @XmlElement(name = "Agencia"  , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private Agencia agencia;

    @XmlElement(name = "ContaCorrente"  , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private ContaCorrente contaCorrente;

    @XmlElement(name = "NumeroBoleto"  , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private String numeroDoDocumento;

    @XmlElement(name = "NossoNumero"  , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private NossoNumero nossoNumero;

    @XmlElement(name = "Emissor"  , required = true, namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private Cedente cedente;

    @XmlElement(name = "Destinatario"  , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private Pagador pagador;

    @XmlElement(name = "ValorBoleto"  , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private BigDecimal valorBoleto;

    @XmlElement(name = "Carteira" , required = true , namespace = "http://schemata.pmrodrigues.biz/Pagamento/1.0")
    private Integer carteira;

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
}
