package com.fraga.APIRest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioAtualizarDTO {
    @NotBlank(message = "O nome completo é obrigatório")
    @Size(min = 3, max = 100, message = "O nome completo deve ter entre 3 e 100 caracteres")
    private String nomeCompleto;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
}
