package com.pmrodrigues.webservices.repositories;

import com.pmrodrigues.webservices.models.Pagador;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * Created by Marceloo on 21/09/2014.
 */
public class PagadorRepository extends AbstractRepository<Pagador> {

    public Pagador getPagadorByCPF( String cpf ) {

        try {
            EntityManager em = PagadorRepository.getEntityManager();
            return em.createQuery("FROM Pagador where cpf = :cpf", Pagador.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();
        }catch(NoResultException e){
            return null;
        }


    }

}
