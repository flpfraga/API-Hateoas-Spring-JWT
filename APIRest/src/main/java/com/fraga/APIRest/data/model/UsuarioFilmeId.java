package com.fraga.APIRest.data.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UsuarioFilmeId implements Serializable {
    private Long idUsuario;
    private Long idFilme;

    public UsuarioFilmeId(Long idUsuario, Long idFilme) {
        this.idUsuario = idUsuario;
        this.idFilme = idFilme;
    }

    public UsuarioFilmeId() {
    }
}
