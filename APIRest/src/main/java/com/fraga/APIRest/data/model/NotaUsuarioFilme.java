package com.fraga.APIRest.data.model;

import javax.persistence.*;

@Entity
public class NotaUsuarioFilme {
    @EmbeddedId
    private UsuarioFilmeId id;

    @ManyToOne
    @MapsId("idUsuario")
    private Usuario usuario;

    @ManyToOne
    @MapsId("idFilme")
    private Filme filme;

    private Integer nota;

    public NotaUsuarioFilme( Usuario usuario, Filme filme, Integer nota) {
        this.usuario = usuario;
        this.filme = filme;
        this.nota = nota;
    }

    public NotaUsuarioFilme() {

    }
}
