package com.fraga.APIRest.service.observer;

public interface NotaUsuarioFilmeObservable {
    void addObserver(FilmeObserver filmeObserver);

    void removeObserver(FilmeObserver filmeObserver);

    void notifyObservers(Long idFilme, Double mediaVotos, Boolean isVotado);
}
