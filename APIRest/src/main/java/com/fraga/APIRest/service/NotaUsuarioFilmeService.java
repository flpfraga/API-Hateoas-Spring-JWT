package com.fraga.APIRest.service;

import com.fraga.APIRest.data.model.Filme;

import java.util.List;

public interface NotaUsuarioFilmeService {
    void atualizarNotaFilme(Long idUsuario, Long idFilme, Integer nota);

    List<Integer> buscarTodasNotasPorFilme(Filme filme);
}