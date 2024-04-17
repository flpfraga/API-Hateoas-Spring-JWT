package com.fraga.APIRest.service.observer;

public interface FilmeObserver {
    void atualizaNotaFilme(Long idFilme, Double mediaVotos, Boolean isVotado);

}
