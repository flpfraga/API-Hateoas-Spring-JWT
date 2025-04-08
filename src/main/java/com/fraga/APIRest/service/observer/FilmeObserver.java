package com.fraga.APIRest.service.observer;

import com.fraga.APIRest.dto.FilmeObserverDTO;
import org.springframework.stereotype.Service;

@Service
public interface FilmeObserver {
    void update(String evento, FilmeObserverDTO dado);

}
