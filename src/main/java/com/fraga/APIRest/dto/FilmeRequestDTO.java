package com.fraga.APIRest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class FilmeRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 2, max = 100, message = "O título deve ter entre 2 e 100 caracteres")
    private String titulo;

    @NotBlank(message = "O diretor é obrigatório")
    @Size(min = 3, max = 100, message = "O nome do diretor deve ter entre 3 e 100 caracteres")
    private String diretor;

    @NotBlank(message = "O gênero é obrigatório")
    @Size(min = 3, max = 50, message = "O gênero deve ter entre 3 e 50 caracteres")
    private String genero;

    @Size(max = 500, message = "Os detalhes não podem ultrapassar 500 caracteres")
    private String detalhes;

    private List<String> atores;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public List<String> getAtores() {
        return atores;
    }

    public void setAtores(List<String> atores) {
        this.atores = atores;
    }
}
