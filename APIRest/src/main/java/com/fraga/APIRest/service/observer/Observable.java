package com.fraga.APIRest.service.observer;

public interface Observable<T> {

    void notifyObservers(String evento, T dado);
}
