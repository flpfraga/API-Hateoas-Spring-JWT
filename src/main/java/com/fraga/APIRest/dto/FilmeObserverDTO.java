package com.fraga.APIRest.dto;

public class FilmeObserverDTO {

    private Long idFilme;
    private Double mediaVotos;
    private Boolean isVotado;

    public FilmeObserverDTO() {

    }

    public Long getIdFilme() {
        return idFilme;
    }

    public FilmeObserverDTO(Long idFilme, Double mediaVotos, Boolean isVotado) {
        this.idFilme = idFilme;
        this.mediaVotos = mediaVotos;
        this.isVotado = isVotado;
    }

    public void setIdFilme(Long idFilme) {
        this.idFilme = idFilme;
    }

    public Double getMediaVotos() {
        return mediaVotos;
    }

    public void setMediaVotos(Double mediaVotos) {
        this.mediaVotos = mediaVotos;
    }

    public Boolean getVotado() {
        return isVotado;
    }

    public void setVotado(Boolean votado) {
        isVotado = votado;
    }
}
