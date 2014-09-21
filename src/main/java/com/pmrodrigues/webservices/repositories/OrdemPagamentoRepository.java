package com.pmrodrigues.webservices.repositories;

import com.pmrodrigues.webservices.models.OrdemPagamento;
import com.pmrodrigues.webservices.models.Pagador;

/**
 * Created by Marceloo on 21/09/2014.
 */
public class OrdemPagamentoRepository extends AbstractRepository<OrdemPagamento> {

    @Override
    public void add(final OrdemPagamento ordemPagamento) {

        updatePagador(ordemPagamento);

        super.add(ordemPagamento);
    }

    private void updatePagador(OrdemPagamento ordemPagamento) {
        PagadorRepository pagadorRepository = new PagadorRepository();
        Pagador existed = pagadorRepository.getPagadorByCPF(ordemPagamento.getPagador().getCPF());
        if( existed != null ) {

            existed.setEmail(ordemPagamento.getPagador().getEmail());
            existed.setEndereco(ordemPagamento.getPagador().getEndereco());
            existed.setNome(ordemPagamento.getPagador().getNome());
            ordemPagamento.setPagador(existed);
        }
    }
}


