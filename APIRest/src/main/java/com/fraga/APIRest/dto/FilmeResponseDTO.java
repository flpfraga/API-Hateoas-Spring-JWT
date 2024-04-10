package com.fraga.APIRest.dto;

import com.fraga.APIRest.data.model.Filme;

import java.util.List;

public class FilmeResponseDTO {

    private String titulo;
    private String diretor;
    private String genero;
    private String detalhes;
    private List<String> atores;

    public FilmeResponseDTO(Filme filme) {
        this.titulo = filme.getTitulo();
        this.detalhes = filme.getDetalhes();
        this.diretor = filme.getDiretor();
        this.genero = filme.getGenero();
        this.atores = filme.getAtoresNome();

    }

    public FilmeResponseDTO() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public List<String> getAtores() {
        return atores;
    }

    public void setAtores(List<String> atores) {
        this.atores = atores;
    }
}
