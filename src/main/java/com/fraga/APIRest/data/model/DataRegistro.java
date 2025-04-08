package com.fraga.APIRest.data.model;

import javax.persistence.Column;
import java.time.LocalDate;

public abstract class DataRegistro {

    @Column
    private final LocalDate criadoEm = LocalDate.now();
    @Column
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
