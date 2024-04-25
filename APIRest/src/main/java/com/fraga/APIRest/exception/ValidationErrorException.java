package com.fraga.APIRest.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = "trace")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationErrorException extends RuntimeException{

    private final Map<String, String> erros;

    public ValidationErrorException(Map<String, String> erros){
        this.erros = erros;
    }

    public Map<String, String> getErros() {
        return erros;
    }
}
