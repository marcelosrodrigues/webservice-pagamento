package com.pmrodrigues.webservices.repositories;

import com.pmrodrigues.webservices.models.Cedente;

/**
 * Created by Marceloo on 23/09/2014.
 */
public interface CedenteRepository extends Repository<Cedente> {
    Cedente findCedenteByName(String nome);
}
