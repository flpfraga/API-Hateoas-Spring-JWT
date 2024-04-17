package com.fraga.APIRest.service;

import com.fraga.APIRest.data.model.Filme;
import com.fraga.APIRest.data.model.NotaUsuarioFilme;
import com.fraga.APIRest.service.observer.NotaUsuarioFilmeObservable;
import com.fraga.APIRest.service.observer.impl.NotaUsuarioFilmeObservableImpl;

import java.util.List;

public interface NotaUsuarioFilmeService {
    void atualizarNotaFilme(Long idUsuario, Long idFilme, Integer nota);

    List<Integer> buscarTodasNotasPorFilme(Filme filme);

    List<NotaUsuarioFilme> buscarTodosNotasTodosFilmes();


}
