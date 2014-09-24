package com.pmrodrigues.webservices.services;

import com.pmrodrigues.webservices.models.OrdemPagamento;
import com.pmrodrigues.webservices.models.Pagador;
import com.pmrodrigues.webservices.repositories.BoletoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
