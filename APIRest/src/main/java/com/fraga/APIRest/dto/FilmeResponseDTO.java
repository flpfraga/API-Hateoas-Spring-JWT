package com.fraga.APIRest.dto;

import java.util.List;

public class FilmeResponseDTO {

    private String titulo;
    private String diretor;
    private String genero;
    private String detalhes;
    private List< Long> atores;

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

    public List<Long> getAtores() {
        return atores;
    }

    public void setAtores(List<Long> atores) {
        this.atores = atores;
    }
}
