package com.pmrodrigues.webservices.controllers;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import com.pmrodrigues.webservices.models.OrdemPagamento;
import com.pmrodrigues.webservices.services.BoletoService;
import com.pmrodrigues.webservices.services.PagamentoService;
import com.pmrodrigues.webservices.utilities.UserSession;
import org.apache.log4j.Logger;

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
    public void listar() {}

    @Get
    @Path("/boletos.do")
    public void listar(String c) {
        List<OrdemPagamento> boletos = service.listAllBoletosByCPF(c);
        result.include("boletos",boletos);
    }

    @Get
    @Path("/boleto/reemitir.do")
    public void reemitir(Long id) {

        OrdemPagamento boleto = service.getById(id);
        boleto.emitirSegundaVia();
        pagamentoService.enviarBoleto(boleto);
        result.include("message","Boleto reenviado com sucesso");
        result.forwardTo(BoletosController.class).listar(boleto.getPagador().getCPF());
    }
}