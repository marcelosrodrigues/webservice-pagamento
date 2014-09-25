package com.pmrodrigues.webservices.controllers;

import br.com.caelum.vraptor.*;
import com.pmrodrigues.webservices.models.Pagador;
import com.pmrodrigues.webservices.services.UserService;
import com.pmrodrigues.webservices.utilities.UserSession;
import org.apache.log4j.Logger;

/**
 * Created by Marceloo on 23/09/2014.
 */
@Resource
public class PasswordController {

    private final Result result;
    private final UserService service;

    private static final Logger logger = Logger.getLogger(PasswordController.class);
    private final UserSession userSession;

    public PasswordController(UserService service, Result result, UserSession userSession) {
        this.service = service;
        this.result = result;
        this.userSession = userSession;
    }

    @Get
    @Path("/index.do")
    public void reemitir() {}


    @Post
    @Path("/index.do")
    public void reemitirSenha(final String cpf) {

        logger.info("reemitindo a senha do cpf " + cpf);

        try {
            if( cpf != null && !"".equalsIgnoreCase(cpf) ){

                Pagador pagador = service.getByCPF(cpf);

                if( pagador != null ) {
                    result.include(pagador);
                    userSession.setPagador(pagador);
                    result.forwardTo(BoletosController.class).listar();
                } else {
                    result.include("message","CPF não encontrado");
                    result.forwardTo(PasswordController.class).reemitir();
                }


            } else {
                result.include("message","CPF é obrigatório");
                result.forwardTo(PasswordController.class).reemitir();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.forwardTo(PasswordController.class).reemitir();
        }

    }
}
