package com.pmrodrigues.webservices.repositories.impl;

import com.pmrodrigues.webservices.models.Cedente;
import com.pmrodrigues.webservices.models.OrdemPagamento;
import com.pmrodrigues.webservices.models.Pagador;
import com.pmrodrigues.webservices.repositories.CedenteRepository;
import com.pmrodrigues.webservices.repositories.PagadorRepository;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Marceloo on 21/09/2014.
 */
@Repository("OrdemPagamentoRepository")
public class OrdemPagamentoRepositoryImpl extends AbstractRepository<OrdemPagamento> implements com.pmrodrigues.webservices.repositories.OrdemPagamentoRepository {


    @Override
    public OrdemPagamento findByNumeroDocumento(String numeroDoDocumento) {
        return (OrdemPagamento) this.getSession().createCriteria(OrdemPagamento.class,"o")
                         .createAlias("o.pagador","p",JoinType.INNER_JOIN)
                         .createAlias("o.cedente","c",JoinType.INNER_JOIN)
                         .add(Restrictions.eq("numeroDoDocumento",numeroDoDocumento))
                         .uniqueResult();
    }

}


