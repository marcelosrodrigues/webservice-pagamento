package com.pmrodrigues.webservices.repositories.impl;

import com.pmrodrigues.webservices.models.Pagador;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by Marceloo on 21/09/2014.
 */
@Repository("PagadorRepository")
public class PagadorRepositoryImpl extends AbstractRepository<Pagador> implements com.pmrodrigues.webservices.repositories.PagadorRepository {

    @Override
    public Pagador getPagadorByCPF(String cpf) {

         Session session = super.getSession();
         return (Pagador) session.createCriteria(Pagador.class)
                    .add(Restrictions.eq("cpf",cpf))
                    .uniqueResult();

    }

}
