package com.fraga.APIRest.data.enums;

public enum EPermissaoUsuario {

    ADMIN(1L, "ADMIN"), COMMON_USER (2L, "COMMON_USER");
    private final Long id;
    private final String permissao;

    EPermissaoUsuario(Long id, String permissao) {
        this.id = id;
        this.permissao = permissao;
    }

    public Long getId() {
        return id;
    }

    public String getPermissao() {
        return permissao;
    }
}
