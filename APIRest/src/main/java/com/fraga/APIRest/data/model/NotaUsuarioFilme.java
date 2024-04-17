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

    public UsuarioFilmeId getId() {
        return id;
    }

    public void setId(UsuarioFilmeId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
}
