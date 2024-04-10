package com.fraga.APIRest.dto;

import org.springframework.http.ResponseEntity;

public class DefaultResponseDTO<T> {
    private Integer status;
    private T resposta;

    public DefaultResponseDTO() {
    }


    public DefaultResponseDTO(Integer status, T resposta) {
        this.status = status;
        this.resposta = resposta;
    }
}
