package com.pmrodrigues.webservices.services;

import org.apache.commons.mail.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.*;
import java.util.Properties;

/**
 * Created by Marceloo on 20/09/2014.
 */
public class SendMail {

    private String from;
    private String to;
    private String subject;
    private String message;
    private InputStream boleto;
    private String username;
    private String password;
    private String hostname;
    private Integer port;
    private boolean autentication;
    private boolean ssl;

    public SendMail setSmtpServer(final String host ) {
        this.hostname = host;
        return this;
    }

    public SendMail setSmtpPort(final Integer port) {
        this.port = port;
        return this;
    }

    public SendMail needAutentication(boolean enabled) {
        this.autentication = enabled;
        return this;
    }

    public SendMail useSSL(boolean enabled) {
        this.ssl = enabled;
        return this;
    }

    public SendMail from(final String email) {
        this.from = email;
        return this;
    }

    public SendMail to(final String email) {
        this.to = email;
        return this;
    }

    public SendMail subject(final String subject) {
        this.subject = subject;
        return this;
    }

    public SendMail message(final String message) {
        this.message = message;
        return this;
    }

    public SendMail attachament(final InputStream boleto) {
        this.boleto = boleto;
        return this;
    }

    public void send() throws EmailException, IOException {
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(this.hostname);
        email.setSmtpPort(this.port);
        email.setAuthentication(this.username,this.password);
        email.setSSLOnConnect(this.ssl);
        email.setStartTLSEnabled(this.ssl);
        email.setFrom(this.from);
        email.setSubject(this.subject);
        email.setMsg(this.message);
        email.addTo(this.to);

        email.attach(new ByteArrayDataSource(this.boleto,"application/pdf"),"boleto.pdf","Boleto para pagamento");
        email.setDebug(true);
        email.send();
    }
    public SendMail username(String username) {
        this.username = username;
        return this;
    }

    public SendMail password(String password) {
        this.password = password;
        return this;
    }
}
