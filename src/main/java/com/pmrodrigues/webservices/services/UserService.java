package com.pmrodrigues.webservices.services;

import com.pmrodrigues.webservices.models.Pagador;
import com.pmrodrigues.webservices.repositories.PagadorRepository;
import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.IOException;

/**
 * Created by Marceloo on 23/09/2014.
 */
@Service
public class UserService {

    private SendMail email = new SendMail();

    @Resource(name = "PagadorRepository")
    private PagadorRepository repository;

    public void reemitirSenha(final String cpf ) throws Exception {

        Pagador pagador = repository.getPagadorByCPF(cpf);
        if( pagador != null ) {

            final String senha = pagador.reemitirSenha();
            repository.set(pagador);
            email.from("qualivida@qualividabeneficios.com.br")
                    .to(pagador.getEmail())
                    .subject("Segue nova senha")
                    .message("Segue abaixo sua senha de acesso para emissão de segunda via do boleto\r\nSenha: " + senha)
                    .send();

        } else {
            throw new Exception("Cliente não encontrado");
        }

    }

    public Pagador autenticate(String email, String password) throws Exception {
        Pagador pagador = repository.findByEmail(email);
        if( pagador != null && password.equalsIgnoreCase(pagador.getPassword()) ) {
            return pagador;
        } else {
            throw new Exception("Usuário não encontrado ou senha inválida");
        }

    }
}
