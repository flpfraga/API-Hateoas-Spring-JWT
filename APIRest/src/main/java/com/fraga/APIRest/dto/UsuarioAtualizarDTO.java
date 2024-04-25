package com.fraga.APIRest.dto;

import javax.validation.constraints.NotBlank;

public class UsuarioAtualizarDTO {
    @NotBlank
    private String nomeCompleto;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
}
