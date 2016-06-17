package com.pmrodrigues.webservices.controllers;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.interceptor.download.InputStreamDownload;
import com.pmrodrigues.webservices.models.OrdemPagamento;
import com.pmrodrigues.webservices.services.BoletoService;
import com.pmrodrigues.webservices.services.PagamentoService;
import com.pmrodrigues.webservices.utilities.UserSession;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Marceloo on 23/09/2014.
 */
@Resource
public class BoletosController {

    private static final Logger LOGGER = Logger.getLogger(BoletosController.class);
    private final BoletoService service;
    private final Result result;
    private final UserSession userSession;
    private final PagamentoService pagamentoService;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy",new Locale("pt","BR"));

    public BoletosController(final BoletoService service, final Result result, final UserSession userSession, final PagamentoService pagamentoService){
        this.service = service;
        this.result = result;
        this.userSession = userSession;
        this.pagamentoService = pagamentoService;
    }

    @Get
    @Path("/boletos.do")
    public void listar() {
        List<OrdemPagamento> boletos = service.listAllBoletosByPagador(userSession.getPagador());
        result.include("boletos",boletos);
    }

    public void listar(String cpf) {
        List<OrdemPagamento> boletos = service.listAllBoletosByCPF(cpf);
        result.include("boletos",boletos);
    }

    @Get
    @Path("/boletos/pesquisar.do")
    public void pesquisar() {
        if( result.included().get("bancos") == null ) {
            result.include("bancos", new String[]{"ITAU", "BRADESCO"});
        }
        if( result.included().get("boletos") == null ) {
            List<OrdemPagamento> boletos = service.listAll();
            result.include("boletos",boletos);
        }

    }

    @Post
    @Path("/boletos/pesquisar.do")
    public void imprimir(Long[] id , String pesquisar , String banco , String inicial , String  fim ) {

        if( "imprimir".equalsIgnoreCase(pesquisar) ) {
            if (id != null && id.length > 0) {
                List<OrdemPagamento> boletos = service.listByIds(id);
                //InputStream arquivo =
                pagamentoService.gerarBoletos(boletos);
                result.include("message", "Boletos gerados e enviados para o email qualivida@qualividabeneficios.com.br");
                result.forwardTo(BoletosController.class).pesquisar();
                //return new InputStreamDownload(arquivo, "application/pdf", "boletos.pdf");
            } else {
                result.include("message", "Selecione pelo menos um boleto");
                result.forwardTo(BoletosController.class).pesquisar();
                //return null;
            }
        } else {

            Date de = null;
            Date ate = null;

            try {
                if( inicial != null && !"".equalsIgnoreCase(inicial) ) {
                    de = dateFormat.parse(inicial);
                }
                if( fim != null && !"".equalsIgnoreCase(fim) ) {
                    ate = dateFormat.parse(fim);
                }
                List<OrdemPagamento> boletos = service.findBy(banco,de,ate);
                result.include("boletos",boletos);
            } catch (ParseException e) {
                result.include("message", "Data inválida");
            }

            result.include("bancos",new String[]{"ITAU","BRADESCO"});
            result.forwardTo(BoletosController.class).pesquisar();
           // return null;
        }
    }

    @Get
    @Path("/boleto/imprimir.do")
    public InputStreamDownload imprimir(Long id){
        OrdemPagamento boleto = service.getById(id);

        boleto.emitirSegundaVia();
        InputStream arquivo = pagamentoService.gerarBoleto(boleto);
        return new InputStreamDownload(arquivo, "application/pdf", String.format("boleto-%ty-%tm", boleto.getDataVencimento(), boleto.getDataVencimento()));

    }

    @Get
    @Path("/boleto/reemitir.do")
    public void reemitir(Long id) {

        OrdemPagamento boleto = service.getById(id);
        boleto.emitirSegundaVia();
        pagamentoService.enviarBoleto(boleto);
        result.include("message","Boleto reenviado com sucesso");
        result.forwardTo(BoletosController.class).listar();
    }
}
