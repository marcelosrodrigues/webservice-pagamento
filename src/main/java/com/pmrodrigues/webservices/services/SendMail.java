package com.pmrodrigues.webservices.services;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import org.apache.commons.mail.ByteArrayDataSource;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Marceloo on 20/09/2014.
 */
public class SendMail {

    private String from;
    private String to;
    private String subject;
    private String message;
    private InputStream boleto;

    public SendMail() {
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

    public void send() throws EmailException, IOException, AddressException {

        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("mail.qualividabeneficios.com.br");
        email.setSmtpPort(25);
        email.setAuthentication("qualivida@qualividabeneficios.com.br", "quali123");
        //email.setHostName("smtp.gmail.com");
        //email.setSmtpPort(465);
        //email.setAuthentication("marsilvarodrigues@gmail.com","aceshigh");
        //email.setSSLOnConnect(true);
        //email.setStartTLSEnabled(true);
        email.setFrom(this.from);
        email.setSubject(this.subject);
        email.setMsg(this.message);
        email.setDebug(false);
        email.addTo(this.to);

        if (this.boleto != null){
            email.attach(new ByteArrayDataSource(this.boleto, "application/pdf"), "boleto.pdf", "Boleto para pagamento");
        }

        email.send();
    }

}
