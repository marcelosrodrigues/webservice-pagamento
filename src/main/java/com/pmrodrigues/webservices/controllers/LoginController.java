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
public class LoginController {

    private final UserService service;
    private final Result result;

    private static final Logger logger = Logger.getLogger(LoginController.class);
    private final UserSession userSession;

    public LoginController(final UserService service , final Result result , final UserSession userSession) {
        this.service = service;
        this.result = result;
        this.userSession = userSession;
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

            boolean error = false;
            String message = "";
            if( email == null || "".equalsIgnoreCase(email) ){
                error = true;
                message = "E-mail é obrigatório\r\n";
            }

            if(password == null || "".equalsIgnoreCase(password) ){
                error = true;
                message = "Senha é obrigatório";
            }


            if( !error ) {
                Pagador pagador = service.autenticate(email, password);
                result.include(pagador);
                userSession.setPagador(pagador);
                result.forwardTo(BoletosController.class).listar();
            } else {
                result.include("message","<ul>" + message + "</ul>");
                result.forwardTo(LoginController.class).login();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            result.include("message",e.getMessage());
            result.forwardTo(LoginController.class).login();
        }

    }

}
