import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Emissor;
import br.com.caelum.stella.boleto.Sacado;
import br.com.caelum.stella.boleto.bancos.Itau;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;

/**
 * Created by Marceloo on 04/11/2014.
 */
public class TestGeracaoBoleto {

    public static void main(String[] args) {


        Boleto boleto =  Boleto.novoBoleto()
                .comValorBoleto("698.47")
                .comNumeroDoDocumento("1523")
                .comEmissor(Emissor.novoEmissor()
                        .comCedente("QUALIVIDA ADM DE BENEFICIOS")
                        .comAgencia(0310)
                        .comContaCorrente(40152)
                        .comDigitoContaCorrente('6')
                        .comNossoNumero(50003599)
                        .comDigitoNossoNumero("7")
                        .comCarteira(109))
                .comSacado(Sacado.novoSacado()
                        .comNome("ROGER RANGEL CRUZ DE ASSIS")
                        .comCpf("01644927713")
                        .comEndereco("EST.MORRO CAVADO, DO 180 03")
                        .comBairro("QUARATIBA")
                        .comCep("23030360")
                        .comCidade("RIO DE JANEIRO")
                        .comUf("RJ"))
                .comDatas(Datas.novasDatas()
                                .comVencimento(20,11,2014)
                                .comDocumento(4,11,2014)
                                .comProcessamento(4,11,2014))
                .comBanco(new Itau())
                .comInstrucoes("Conforme item 16 do seu contrato, havendo inadimplêcia, poderá ocorrer suspensão automática da utilização do plano.","De acordo com o item 19, o seu plano poderá ser cancelado a partir do 60° dia de mensalidade em aberto, cumulativa ou não.",
                        "ROGER RANGEL CRUZ DE ASSIS - R$ 235,19",
                        "HANNAH PEREIRA RANGEL DE ASSIS - R$ 128,53",
                        "ERICA PEREIRA DA SILVA - R$ 201,22",
                        "ANNE PEREIRA RANGEL DE ASSIS - R$ 128,53",
                        "QUALIVIDA ADM DE BENEFICIOS",
                        "Parcela : 8 Taxa Assoc.: R$ 5,00");


        GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);
        gerador.geraPDF("boleto.pdf");


    }

}
