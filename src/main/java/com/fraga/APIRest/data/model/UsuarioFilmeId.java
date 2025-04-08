package com.fraga.APIRest.data.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UsuarioFilmeId implements Serializable {
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "id_filme")
    private Long idFilme;

    public UsuarioFilmeId(Long idUsuario, Long idFilme) {
        this.idUsuario = idUsuario;
        this.idFilme = idFilme;
    }

    public UsuarioFilmeId() {
    }
}
