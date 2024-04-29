package com.fraga.APIRest.mocks;

import com.fraga.APIRest.data.model.Filme;
import com.fraga.APIRest.dto.FilmeRequestDTO;
import com.fraga.APIRest.dto.FilmeResponseDTO;

import java.util.Arrays;
import java.util.List;

public class FilmeMock {

    public Filme getFilme(){
        Filme filme = new Filme();
        filme.setId(1L);
        filme.setDetalhes("Detalhes Filme");
        filme.setTitulo("Titulo filme");
        filme.setDiretor("Diretor filme");
        filme.setGenero("Genero filme");
        filme.setMediaVotos(4.4);
        filme.setContagemVotos(100L);
        return filme;
    }

    public Filme getFilme2(){
        Filme filme = new Filme();
        filme.setId(1L);
        filme.setDetalhes("Detalhes2 Filme");
        filme.setTitulo("Titulo2 filme");
        filme.setDiretor("Diretor2 filme");
        filme.setGenero("Genero2 filme");
        filme.setMediaVotos(3.4);
        filme.setContagemVotos(200L);
        return filme;
    }
    public List<Filme> getFilmes(){
        return Arrays.asList(getFilme(), getFilme2());
    }
    public FilmeRequestDTO getFilmeRequestDTO(){
        FilmeRequestDTO filme = new FilmeRequestDTO();
        filme.setDetalhes("DetalhesDTO Filme");
        filme.setTitulo("TituloDTO filme");
        filme.setDiretor("DiretorDTO filme");
        filme.setGenero("GeneroDTO filme");
        return filme;
    }

    public FilmeResponseDTO getFilmeResponseDTO(){
        FilmeResponseDTO filme = new FilmeResponseDTO();
        filme.setDetalhes("DetalhesDTO Filme");
        filme.setTitulo("TituloDTO filme");
        filme.setDiretor("DiretorDTO filme");
        filme.setGenero("GeneroDTO filme");
        return filme;
    }
}
