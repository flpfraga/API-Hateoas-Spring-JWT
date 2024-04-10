package com.fraga.APIRest.data.enums;

public enum EFilmeParametro {
    TITULO(1, "Titulo"), DIRETOR(2, "Diretor"), GENERO(3, "Genero"), MEDIA(4, "mediaVotos");

    private final Integer id;
    private final String descricao;


    EFilmeParametro(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
