package com.pmrodrigues.webservices.controllers;

import br.com.caelum.vraptor.*;
import com.pmrodrigues.webservices.models.Pagador;
import com.pmrodrigues.webservices.services.UserService;
import org.apache.log4j.Logger;

/**
 * Created by Marceloo on 23/09/2014.
 */
@Resource
public class LoginController {

    private final UserService service;
    private final Result result;

    private static final Logger logger = Logger.getLogger(LoginController.class);

    public LoginController(final UserService service , final Result result ) {
        this.service = service;
        this.result = result;
    }

    @Get
    @Path("/index.do")
    public void login() {
        logger.debug("abrindo a tela de login");
    }

    @Post
    @Path("/index.do")
    public void doLogin(final String email , final String password ){

        try {
            Pagador pagador = service.autenticate(email,password);
            result.include(pagador);
            result.forwardTo("index.jsp");
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            result.include("message",e.getMessage());
        }

    }

}
