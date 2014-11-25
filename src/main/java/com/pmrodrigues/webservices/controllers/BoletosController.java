package com.pmrodrigues.webservices.controllers;

import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.interceptor.download.InputStreamDownload;
import br.com.caelum.vraptor.view.Results;
import com.pmrodrigues.webservices.models.OrdemPagamento;
import com.pmrodrigues.webservices.services.BoletoService;
import com.pmrodrigues.webservices.services.PagamentoService;
import com.pmrodrigues.webservices.utilities.UserSession;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.List;

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
        List<OrdemPagamento> boletos = service.listAll();
        result.include("boletos",boletos);
    }

    @Post
    @Path("/boletos/pesquisar.do")
    public InputStreamDownload imprimir(Long[] id) {
        if( id != null && id.length > 0 ) {
            List<OrdemPagamento> boletos = service.listByIds(id);
            InputStream arquivo = pagamentoService.gerarBoletos(boletos);
            return new InputStreamDownload(arquivo, "application/pdf", "boletos.pdf");
        } else {
            result.include("message","Selecione pelo menos um boleto");
            result.forwardTo(BoletosController.class).pesquisar();
            return null;
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
