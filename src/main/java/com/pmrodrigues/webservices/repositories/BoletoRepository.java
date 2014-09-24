package com.pmrodrigues.webservices.repositories;

import com.pmrodrigues.webservices.models.OrdemPagamento;
import com.pmrodrigues.webservices.models.Pagador;

import java.util.List;

/**
 * Created by Marceloo on 23/09/2014.
 */
public interface BoletoRepository extends Repository<OrdemPagamento> {
    List<OrdemPagamento> listAllBoletosByPagador(Pagador pagador);

    List<OrdemPagamento> listAllBoletosByCPF(String cpf);
}
