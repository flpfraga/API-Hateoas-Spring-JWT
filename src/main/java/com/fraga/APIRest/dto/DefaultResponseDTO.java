package com.fraga.APIRest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultResponseDTO<T> {
    private Integer status;
    private T resposta;

    public DefaultResponseDTO() {
    }


    public DefaultResponseDTO(final Integer status, final T resposta) {
        this.status = status;
        this.resposta = resposta;
    }

    public Integer getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultResponseDTO<?> that = (DefaultResponseDTO<?>) o;
        return Objects.equals(status, that.status) && Objects.equals(resposta, that.resposta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, resposta);
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getResposta() {
        return resposta;
    }

    public void setResposta(T resposta) {
        this.resposta = resposta;
    }

    @Override
    public String toString() {
        return "DefaultResponseDTO{" +
                "status=" + status +
                ", resposta=" + resposta +
                '}';
    }
}
