package com.fraga.APIRest.controller;

import com.fraga.APIRest.dto.DefaultResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface DefaultController {

    default <T extends Object> ResponseEntity<DefaultResponseDTO<T>> retornarSucesso (final HttpStatus httpStatus, final T resposta){
        return ResponseEntity.status(httpStatus.value()).body(new DefaultResponseDTO<>(httpStatus.value(), resposta));
    }


    default <T extends Object> ResponseEntity<DefaultResponseDTO<T>> retornarSucesso(final T resposta){
        return retornarSucesso(HttpStatus.OK, resposta);
    }
}
