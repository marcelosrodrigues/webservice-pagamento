package com.pmrodrigues.webservices.utilities;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import com.pmrodrigues.webservices.models.Pagador;

/**
 * Created by Marceloo on 23/09/2014.
 */
@Component
@SessionScoped
public class UserSession {

    private Pagador pagador;

    public void setPagador(final Pagador pagador){
        this.pagador = pagador;
    }

    public Pagador getPagador() {
        return pagador;
    }
}
