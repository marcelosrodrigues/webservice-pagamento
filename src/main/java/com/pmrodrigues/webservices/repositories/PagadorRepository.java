package com.pmrodrigues.webservices.repositories;

import com.pmrodrigues.webservices.models.Pagador;

/**
 * Created by Marceloo on 22/09/2014.
 */
public interface PagadorRepository extends Repository<Pagador>{
    Pagador getPagadorByCPF(String cpf);
}
