package com.fraga.APIRest.dto;

import com.fraga.APIRest.data.model.Filme;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRequestDTO {
    @NotBlank
    private String nomeUsuario;
    @NotBlank
    private String nomeCompleto;
    @NotBlank
    private String senha;
    private final Boolean  accountNonExpired = true;
    private final Boolean accountNonLocked= true;
    private final Boolean credentialsNonExpired= true;
    private final Boolean enabled = true;
    private final Boolean active = true;
    private final List<Filme> filmeVotados = new ArrayList<>();

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Boolean getActive() {
        return active;
    }

    public List<Filme> getFilmeVotados() {
        return filmeVotados;
    }
}
