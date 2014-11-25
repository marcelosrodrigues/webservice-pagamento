package com.pmrodrigues.webservices.repositories.impl;

import com.pmrodrigues.webservices.models.OrdemPagamento;
import com.pmrodrigues.webservices.models.Pagador;
import com.pmrodrigues.webservices.repositories.BoletoRepository;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Marceloo on 23/09/2014.
 */
@Repository("BoletoRepository")
public class BoletoRepositoryImpl extends AbstractRepository<OrdemPagamento> implements BoletoRepository{
    @Override
    public List<OrdemPagamento> listAllBoletosByPagador(Pagador pagador) {
        return this.getSession().createCriteria(OrdemPagamento.class)
                                .add(Restrictions.eq("pagador", pagador))
                                .addOrder(Order.asc("dataVencimento"))
                                .list();
    }

    @Override
    public List<OrdemPagamento> listAllBoletosByCPF(String cpf) {
        return this.getSession().createCriteria(OrdemPagamento.class,"o")
                                .createCriteria("o.pagador","p", JoinType.INNER_JOIN)
                                .add(Restrictions.eq("p.cpf",cpf))
                                .add(Restrictions.lt("o.reemissao",2L))
                                .list();
    }

    @Override
    public List<OrdemPagamento> listAll() {
        return this.getSession().createCriteria(OrdemPagamento.class,"o")
                .createCriteria("o.pagador","p", JoinType.INNER_JOIN)
                .addOrder(Order.desc("o.dataVencimento"))
                .list();
    }

    @Override
    public List<OrdemPagamento> listByIds(Long[] id) {
        return this.getSession().createCriteria(OrdemPagamento.class, "o")
                .createCriteria("o.pagador", "p", JoinType.INNER_JOIN)
                .add(Restrictions.in("o.id", Arrays.asList(id)))
                .addOrder(Order.desc("o.dataVencimento"))
                .addOrder(Order.asc("p.nome"))
                .list();
    }
}
