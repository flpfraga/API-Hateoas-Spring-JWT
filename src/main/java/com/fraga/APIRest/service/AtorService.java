package com.fraga.APIRest.service;

import com.fraga.APIRest.data.model.Ator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AtorService {

    /**
     * Busca uma lista de atores de acordo com uma lista de ids
     *
     * @param idAtores com ids dos atores a serem buscados
     * @return List<Ator>
     */
    List<Ator> buscarAtoresPorId(List<Long> idAtores);

    /**
     * Salva uma lista de atores
     *
     * @param atores os atores a serem salvos/atualizados
     * @return List<Ator>
     */

    List<Ator> salvarListaAtores(List<String> atores);

}
