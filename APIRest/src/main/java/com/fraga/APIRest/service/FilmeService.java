package com.fraga.APIRest.service;

import com.fraga.APIRest.dto.FilmeParametrosDTO;
import com.fraga.APIRest.dto.FilmeResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FilmeService {


    /**
     * Return all movies sorted by name or vote Average.
     *
     * @param
     * @return List<MovieVO>
     */

    FilmeResponseDTO buscarFilmePorId(Long id);

    /**
     * Return all movies sorted by name or vote Average. Return pagined.
     *
     * @param
     * @return List<MovieVO>
     */

    Page<FilmeResponseDTO> buscarFilmeOrdemInsercaoPaginado(Integer page, Integer size);

    /**
     * Return a movie by id
     *
     * @param
     * @return MovieVO
     */
    List<FilmeResponseDTO> buscarFilmesOrdenadosPorMediaVotos();


    /**
     * Return a page movie filter by params
     *
     * @param
     * @return
     */
    Page<FilmeResponseDTO> buscarFilmesPorParametros(FilmeParametrosDTO filmeParametrosDTO);


}
