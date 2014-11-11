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

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void set(final OrdemPagamento ordemPagamento ){
        updatePagador(ordemPagamento);
        updateCedente(ordemPagamento);
        super.set(ordemPagamento);
    }

    @Override
    public OrdemPagamento findByNumeroDocumento(String numeroDoDocumento) {
        return (OrdemPagamento) this.getSession().createCriteria(OrdemPagamento.class,"o")
                         .createAlias("o.pagador","p",JoinType.INNER_JOIN)
                         .createAlias("o.cedente","c",JoinType.INNER_JOIN)
                         .add(Restrictions.eq("numeroDoDocumento",numeroDoDocumento))
                         .uniqueResult();
    }

    private void updateCedente( final OrdemPagamento ordemPagamento ) {

        final Cedente cedente = cedenteRepository.findCedenteByName(ordemPagamento.getCedente().getNome());
        if( cedente != null ) {
            System.out.println("cedente " + cedente.getNome() + " encontrado");
            ordemPagamento.setCedente(cedente);
        }else {
            System.out.println("cedente " + ordemPagamento.getCedente().getNome() + " não encontrado");
            cedenteRepository.add(ordemPagamento.getCedente());
        }

    }

    private void updatePagador(final OrdemPagamento ordemPagamento) {

        final Pagador existed = pagadorRepository.getPagadorByCPF(ordemPagamento.getPagador().getCPF());
        if( existed != null ) {
            System.out.println("pagador " + ordemPagamento.getPagador().getNome() + " encontrado com o cpf " + ordemPagamento.getPagador().getCPF());
            existed.setEmail(ordemPagamento.getPagador().getEmail());
            existed.setEndereco(ordemPagamento.getPagador().getEndereco());
            existed.setNome(ordemPagamento.getPagador().getNome());
            ordemPagamento.setPagador(existed);
            pagadorRepository.set(existed);
        } else {
            System.out.println("pagador " + ordemPagamento.getPagador().getNome() + " não encontrado com o cpf " + ordemPagamento.getPagador().getCPF());
            pagadorRepository.add(ordemPagamento.getPagador());
        }
    }
}


