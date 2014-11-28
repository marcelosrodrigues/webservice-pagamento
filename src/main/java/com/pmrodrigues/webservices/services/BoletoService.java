package com.pmrodrigues.webservices.services;

import br.com.caelum.stella.boleto.Boleto;
import com.pmrodrigues.webservices.models.OrdemPagamento;
import com.pmrodrigues.webservices.models.Pagador;
import com.pmrodrigues.webservices.repositories.BoletoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Marceloo on 23/09/2014.
 */
@Service
public class BoletoService {

    @Resource(name = "BoletoRepository")
    private BoletoRepository repository;

    public List<OrdemPagamento> listAllBoletosByPagador(final Pagador pagador) {
        return repository.listAllBoletosByPagador(pagador);
    }

    public OrdemPagamento getById(Long id) {
        return repository.findById(id);
    }

    public List<OrdemPagamento> listAllBoletosByCPF(final String c) {
        return repository.listAllBoletosByCPF(c);
    }

    public List<OrdemPagamento> listAll() {
        return repository.listAll();
    }

    public List<OrdemPagamento> listByIds(Long[] id) {
        return repository.listByIds(id);
    }

    public List<OrdemPagamento> findBy(final String banco, final Date de, final Date ate) {
        return repository.findById(banco,de,ate);
    }
}
