package com.pmrodrigues.webservices.repositories.impl;

import com.pmrodrigues.webservices.models.Cedente;
import com.pmrodrigues.webservices.models.OrdemPagamento;
import com.pmrodrigues.webservices.models.Pagador;
import com.pmrodrigues.webservices.repositories.CedenteRepository;
import com.pmrodrigues.webservices.repositories.PagadorRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Marceloo on 21/09/2014.
 */
@Repository("OrdemPagamentoRepository")
public class OrdemPagamentoRepositoryImpl extends AbstractRepository<OrdemPagamento> implements com.pmrodrigues.webservices.repositories.OrdemPagamentoRepository {

    @Resource(name = "PagadorRepository")
    private PagadorRepository pagadorRepository;

    @Resource( name = "CedenteRepository")
    private CedenteRepository cedenteRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(final OrdemPagamento ordemPagamento) {

        updatePagador(ordemPagamento);
        updateCedente(ordemPagamento);
        super.add(ordemPagamento);
    }

    private void updateCedente( final OrdemPagamento ordemPagamento ) {

        final Cedente cedente = cedenteRepository.findCedenteByName(ordemPagamento.getCedente().getNome());
        if( cedente != null ) {
            ordemPagamento.setCedente(cedente);
        }

    }

    private void updatePagador(final OrdemPagamento ordemPagamento) {

        final Pagador existed = pagadorRepository.getPagadorByCPF(ordemPagamento.getPagador().getCPF());
        if( existed != null ) {
            existed.setEmail(ordemPagamento.getPagador().getEmail());
            existed.setEndereco(ordemPagamento.getPagador().getEndereco());
            existed.setNome(ordemPagamento.getPagador().getNome());
            ordemPagamento.setPagador(existed);
        }
    }
}


