package com.fraga.APIRest.data.model;

import java.time.LocalDate;

public abstract class DataRegistro {

    private final LocalDate criadoEm = LocalDate.now();
    private LocalDate atualizadoEm;

    public DataRegistro(LocalDate atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public DataRegistro() {
    }

    public LocalDate getCriadoEm() {
        return criadoEm;
    }

    public LocalDate getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDate atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }
}
