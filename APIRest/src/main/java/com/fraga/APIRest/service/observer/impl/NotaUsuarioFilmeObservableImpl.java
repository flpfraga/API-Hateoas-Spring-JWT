package com.fraga.APIRest.service.observer.impl;

import com.fraga.APIRest.service.observer.NotaUsuarioFilmeObservable;
import com.fraga.APIRest.service.observer.FilmeObserver;

import java.util.ArrayList;
import java.util.List;

public class NotaUsuarioFilmeObservableImpl implements NotaUsuarioFilmeObservable {
    private final List<FilmeObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(FilmeObserver filmeObserver) {
        observers.add(filmeObserver);
    }

    @Override
    public void removeObserver(FilmeObserver filmeObserver) {
        observers.remove(filmeObserver);
    }

    @Override
    public void notifyObservers(Long idFilme, Double mediaVotos, Boolean isVotado) {
        observers.forEach(obs -> obs.atualizaNotaFilme(idFilme, mediaVotos, isVotado));
    }
}
