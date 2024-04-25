package com.fraga.APIRest.service;

import com.fraga.APIRest.data.model.Filme;
import com.fraga.APIRest.data.model.NotaUsuarioFilme;
import com.fraga.APIRest.data.model.Usuario;

import java.util.List;

public interface NotaUsuarioFilmeService {

    /**
     * Atualizar a nota de um filme dada por um usuário
     *
     * @param idUsuario com id do usuário votante
     * @param idFilme com id do filme votado
     */
    void atualizarNotaFilme(Long idUsuario, Long idFilme, Integer nota);

    /**
     * Buscar uma lista com todas as notas de um filme
     *
     * @param filme com objeto filme que se deseja obter as notas
     * @return Intger
     */
    List<Integer> buscarTodasNotasPorFilme(Filme filme);

    /**
     * Buscar uma lista com todas as notas de todos os filmes
     *
     * @return List<NotaUsuarioFilme>
     */
    List<NotaUsuarioFilme> buscarTodosNotasTodosFilmes();


    /**
     * Buscar listagem de filmes votados por um usuãrio
     *
     * @param usuario com usuãrio do qual se deseja obter a lista de filmes com votos
     * @return List<Filme>
     */
    List<Filme> buscarFilmesVotadosPorUsuario(Usuario usuario);

}
