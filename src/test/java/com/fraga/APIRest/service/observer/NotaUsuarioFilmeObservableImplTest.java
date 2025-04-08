package com.fraga.APIRest.service.observer;

import com.fraga.APIRest.dto.FilmeObserverDTO;
import com.fraga.APIRest.service.observer.impl.NotaUsuarioFilmeObservableImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class NotaUsuarioFilmeObservableImplTest {

    @InjectMocks
    private NotaUsuarioFilmeObservableImpl notaUsuarioFilmeObservable;
    @Mock
    private List<FilmeObserver> observers;


    @Test
    public void testarObservables(){
        notaUsuarioFilmeObservable.notifyObservers("event", new FilmeObserverDTO());
    }
}
