package com.pmrodrigues.webservices.controllers;

import br.com.caelum.vraptor.*;
import com.pmrodrigues.webservices.services.UserService;
import org.apache.log4j.Logger;

/**
 * Created by Marceloo on 23/09/2014.
 */
@Resource
public class PasswordController {

    private final Result result;
    private final UserService service;

    private static final Logger logger = Logger.getLogger(PasswordController.class);

    public PasswordController(UserService service, Result result) {
        this.service = service;
        this.result = result;
    }

    @Get
    @Path("/reemitir.do")
    public void reemitir() {}


    @Post
    @Path("/reemitir.do")
    public void reemitirSenha(final String cpf) {

        logger.info("reemitindo a senha do cpf " + cpf);

        try {
            if( cpf != null && !"".equalsIgnoreCase(cpf) ){

                service.reemitirSenha(cpf);
                result.include("message","Sua senha foi enviada para o seu email de cadastro. Obrigado!");
                result.forwardTo(LoginController.class).login();

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
