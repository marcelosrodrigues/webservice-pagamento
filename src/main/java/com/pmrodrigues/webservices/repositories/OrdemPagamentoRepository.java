package com.pmrodrigues.webservices.repositories;

import com.pmrodrigues.webservices.models.OrdemPagamento;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Marceloo on 22/09/2014.
 */
public interface OrdemPagamentoRepository extends Repository<OrdemPagamento>{
    @Transactional(propagation = Propagation.REQUIRED)
    void add(OrdemPagamento ordemPagamento);
}
