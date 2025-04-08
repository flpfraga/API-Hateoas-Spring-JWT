package com.fraga.APIRest.data.enums;

public enum EDirecaoOrdenacao {
    ASC("Asc"), DESC("Desc");
    private final String direcao;

    public String getDirecao() {
        return direcao;
    }

    EDirecaoOrdenacao(String direcao) {
        this.direcao = direcao;
    }
}
