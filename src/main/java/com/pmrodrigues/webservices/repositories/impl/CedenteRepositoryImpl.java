package com.pmrodrigues.webservices.repositories.impl;

import com.pmrodrigues.webservices.models.Cedente;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marceloo on 23/09/2014.
 */
@Repository("CedenteRepository")
public class CedenteRepositoryImpl extends AbstractRepository<Cedente> implements com.pmrodrigues.webservices.repositories.CedenteRepository {

    @Override
    public Cedente findCedenteByName(final String nome) {

        List<Cedente> cedentes =  this.getSession()
                   .createCriteria(Cedente.class)
                   .add(Restrictions.eq("nome",nome))
                   .list();

        if( cedentes != null ) {
            return cedentes.get(0);
        } else {
            return null;
        }

    }
}
