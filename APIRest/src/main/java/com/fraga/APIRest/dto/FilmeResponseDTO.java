package com.fraga.APIRest.dto;

import com.fraga.APIRest.data.model.Filme;

import java.util.List;

public class FilmeResponseDTO {

    private String titulo;
    private String diretor;
    private String genero;
    private String detalhes;
    private Integer contagemVotos;
    private Double mediaVotos;
    private List<String> atores;

    public FilmeResponseDTO(Filme filme) {
        this.titulo = filme.getTitulo();
        this.detalhes = filme.getDetalhes();
        this.diretor = filme.getDiretor();
        this.genero = filme.getGenero();
        this.atores = filme.getAtoresNome();
        this.contagemVotos = filme.getContagemVotos();
        this.mediaVotos = filme.getMediaVotos();
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

    public Integer getContagemVotos() {
        return contagemVotos;
    }

    public void setContagemVotos(Integer contagemVotos) {
        this.contagemVotos = contagemVotos;
    }

    public Double getMediaVotos() {
        return mediaVotos;
    }

    public void setMediaVotos(Double mediaVotos) {
        this.mediaVotos = mediaVotos;
    }
}
