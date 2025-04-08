package com.fraga.APIRest.service.observer.impl;

import com.fraga.APIRest.dto.FilmeObserverDTO;
import com.fraga.APIRest.service.observer.Observable;
import com.fraga.APIRest.service.observer.FilmeObserver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaUsuarioFilmeObservableImpl implements Observable<FilmeObserverDTO> {
    private final List<FilmeObserver> observers;

    public NotaUsuarioFilmeObservableImpl(List<FilmeObserver> observers) {
        this.observers = observers;
    }


    @Override
    public void notifyObservers(String evento, FilmeObserverDTO dado) {
        observers.forEach(obs -> obs.update(evento, dado));
    }
}
