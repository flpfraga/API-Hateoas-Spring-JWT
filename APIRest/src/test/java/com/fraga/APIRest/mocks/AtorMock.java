package com.fraga.APIRest.mocks;

import com.fraga.APIRest.data.model.Ator;

import java.util.Arrays;
import java.util.List;

public class AtorMock {

    public Ator getAtor(){
        return new Ator(1L, "Ator 1");
    }

    public Ator getAtor2(){
        return new Ator(2L, "Ator 2");
    }

    public List<Ator> getAtores(){
        return Arrays.asList(new Ator(1L, "Ator 1"), new Ator(2L,"Ator 2"));
    }
}
