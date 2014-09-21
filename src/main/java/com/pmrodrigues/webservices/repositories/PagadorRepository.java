package com.pmrodrigues.webservices.repositories;

import com.pmrodrigues.webservices.models.Pagador;

import javax.persistence.EntityManager;

/**
 * Created by Marceloo on 21/09/2014.
 */
public class PagadorRepository extends AbstractRepository<Pagador> {

    public Pagador getPagadorByCPF( String cpf ) {

        EntityManager em = PagadorRepository.getEntityManager();
        return em.createQuery("FROM Pagador where cpf = :cpf",Pagador.class)
                    .setParameter("cpf",cpf)
                    .getSingleResult();


    }

}
