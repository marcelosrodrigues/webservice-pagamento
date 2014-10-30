package com.pmrodrigues.webservices.repositories.impl;

import com.pmrodrigues.webservices.models.Cedente;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by Marceloo on 23/09/2014.
 */
@Repository("CedenteRepository")
public class CedenteRepositoryImpl extends AbstractRepository<Cedente> implements com.pmrodrigues.webservices.repositories.CedenteRepository {

    @Override
    public Cedente findCedenteByName(final String nome) {

        return (Cedente) this.getSession()
                   .createCriteria(Cedente.class)
                   .add(Restrictions.eq("nome",nome))
                   .setMaxResults(1)
                   .uniqueResult();

    }
}
