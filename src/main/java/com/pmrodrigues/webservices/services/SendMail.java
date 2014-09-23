package com.pmrodrigues.webservices.services;

import javax.mail.util.ByteArrayDataSource;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import javax.mail.MessagingException;
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

    public void send() throws EmailException, IOException, MessagingException {

        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com");
        email.setSslSmtpPort("465");
        email.setAuthentication("marsilvarodrigues@gmail.com", "powerslave");
        email.setSSLOnConnect(true);
        email.setStartTLSEnabled(true);
        email.setFrom(this.from);
        email.setSubject(this.subject);
        email.setMsg(this.message);
        email.setDebug(true);
        email.addTo(this.to);

        if (this.boleto != null){
            email.attach(new ByteArrayDataSource(this.boleto, "application/pdf"), "boleto.pdf", "Boleto para pagamento");
        }

        email.setDebug(true);
        email.send();
    }

}
