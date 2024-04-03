package com.fraga.APIRest.controller;

import com.fraga.APIRest.dto.FilmeRequestDTO;
import com.fraga.APIRest.service.FilmesAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/admin/catalogo-filmes")
@Tag(name = "Movies", description = "EndPoints for Managing Movies")
public class FilmesAdminController {

    private final FilmesAdminService filmesAdminService;

    public FilmesAdminController(FilmesAdminService filmesAdminService) {
        this.filmesAdminService = filmesAdminService;
    }

    /**
     * Adicionar um novo filme
     *
     * @param filmeRequestDTO com dados para criar o filme
     */
    @PostMapping("/salvar")
    @Operation(summary = "Faz o cadastro de um novo filme.", description = "Faz o cadastro de um novo filme.", tags = {"Filmes"},
            responses =
                    {
                            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
                            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
                    })
    public ResponseEntity<?> adicionarFilme(@RequestBody @Valid FilmeRequestDTO filmeRequestDTO) {
        filmesAdminService.adicionarFilmes(filmeRequestDTO);
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    /**
     * Atualizar dados de um filme
     *
     * @param id com id do filme a ser atualizado
     * @param filmeRequestDTO com dados para atualizar o filme
     */
    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualiza dados de um filme.", description = "Atualiza dados de um filme.", tags = {"Filmes"},
            responses =
                    {
                            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
                            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
                    })
    public ResponseEntity<?> atualizarFilme(@PathVariable("id") @NotNull Long id,
                                            @RequestBody @Valid FilmeRequestDTO filmeRequestDTO) {
        filmesAdminService.atualizarFilmes(id, filmeRequestDTO);
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    /**
     * Deletar um filme
     *
     * @param id com id do filme a ser deletado
     */
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Atualiza dados de um filme.", description = "Atualiza dados de um filme.", tags = {"Filmes"},
            responses =
                    {
                            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
                            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
                    })
    public ResponseEntity<?> atualizarFilme(@PathVariable ("id") @NotNull Long id) {
        filmesAdminService.deletarFilmesPorId(id);
        return (ResponseEntity<?>) ResponseEntity.ok();
    }
}
