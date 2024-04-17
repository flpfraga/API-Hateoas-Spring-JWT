package com.fraga.APIRest.service.observer.impl;

import com.fraga.APIRest.service.observer.FilmeObserver;

public class FilmeObserverImpl implements FilmeObserver {

    private Long idFilme;
    private Double mediaVotos;
    private Boolean isVotado;

    public FilmeObserverImpl(Long idFilme, Double mediaVotos, Boolean isVotado) {
        this.idFilme = idFilme;
        this.mediaVotos = mediaVotos;
        this.isVotado = isVotado;
    }

    @Override
    public void atualizaNotaFilme(Long idFilme, Double mediaVotos, Boolean isVotado) {
        this.idFilme = idFilme;
        this.mediaVotos = mediaVotos;
        this.isVotado = isVotado;
    }

}
