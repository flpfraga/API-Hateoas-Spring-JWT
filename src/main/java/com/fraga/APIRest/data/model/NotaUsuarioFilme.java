package com.fraga.APIRest.data.model;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "nota_usuario_filme")
public class NotaUsuarioFilme implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private UsuarioFilmeId id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_filme", referencedColumnName = "id", insertable = false, updatable = false)
    private Filme filme;

    private Integer nota;

    public NotaUsuarioFilme( Usuario usuario, Filme filme, Integer nota) {
        this.id = new UsuarioFilmeId(usuario.getId(), filme.getId());
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
