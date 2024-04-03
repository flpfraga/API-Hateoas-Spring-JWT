package com.fraga.APIRest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class FilmeRequestDTO {

    @NotBlank(message = "O título do filme não pode estar em vazio.")
    private String titulo;
    @NotBlank(message = "O diretor do filme não pode estar em vazio.")
    private String diretor;
    @NotBlank(message = "O gênero do filme não pode estar em vazio.")
    private String genero;
    private String detalhes;
    @NotNull(message = "Incluao ao menos o ID de um ator para este filme.")
    private List< Long> atores;
}
