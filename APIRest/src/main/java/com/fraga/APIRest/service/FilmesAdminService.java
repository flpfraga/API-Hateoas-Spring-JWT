package com.fraga.APIRest.service;

import com.fraga.APIRest.dto.FilmeRequestDTO;

public interface FilmesAdminService {

    /**
     * Adicionar um filme a coleção
     *
     * @param filmeRequestDTO com novo filme a ser adicionado
     */
    void adicionarFilmes(FilmeRequestDTO filmeRequestDTO);

    /**
     * Adicionar um filme a coleção
     *
     * @param id com o valor do identificador do filme a ser deletado
     */
    void deletarFilmesPorId(Long id);

    /**
     * Adicionar um filme a coleção
     *
     * @param id com o valor do identificador do filme a ser atualizado
     * @param filmeRequestDTO com os valores do filme a ser atualizado
     */
    void atualizarFilmes(Long id, FilmeRequestDTO filmeRequestDTO);
}
