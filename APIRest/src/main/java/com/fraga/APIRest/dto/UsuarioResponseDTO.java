package com.fraga.APIRest.dto;

import com.fraga.APIRest.data.model.Filme;
import com.fraga.APIRest.data.model.Usuario;

import java.util.List;

public class UsuarioResponseDTO {
    private String nomeUsuario;
    private String nomeCompleto;
    private List<String> filmesVotados;

    public UsuarioResponseDTO(Usuario usuario) {
        this.nomeUsuario = usuario.getNomeUsuario();
        this.nomeCompleto = usuario.getNomeCompleto();
    }

    public UsuarioResponseDTO() {
    }

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

    public List<String> getFilmesVotados() {
        return filmesVotados;
    }

    public void setFilmesVotados(List<String> filmeVotados) {
        this.filmesVotados = filmeVotados;
    }
}
