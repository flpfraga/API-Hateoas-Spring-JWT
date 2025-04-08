package com.fraga.APIRest.mocks;

import com.fraga.APIRest.dto.FilmeObserverDTO;

public class FilmeObserverMock {

    public FilmeObserverDTO getFilmeObserverDTO(){
        FilmeObserverDTO filmeObserverDTO = new FilmeObserverDTO();
        filmeObserverDTO.setIdFilme(1L);
        filmeObserverDTO.setMediaVotos(4.5);
        filmeObserverDTO.setVotado(true);
        return filmeObserverDTO;
    }
}
