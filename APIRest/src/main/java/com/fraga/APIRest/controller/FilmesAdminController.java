package com.fraga.APIRest.controller;

import com.fraga.APIRest.dto.DefaultResponseDTO;
import com.fraga.APIRest.dto.FilmeRequestDTO;
import com.fraga.APIRest.dto.FilmeResponseDTO;
import com.fraga.APIRest.service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping("/api/admin/catalogo-filmes/v1")
@Tag(name = "Admin Filmes", description = "Endpoints para gerenciar filmes")
public class FilmesAdminController implements DefaultController {

    private final FilmeService filmeService;

    public FilmesAdminController(FilmeService filmeService) {
        this.filmeService = filmeService;
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
    public ResponseEntity<DefaultResponseDTO<FilmeResponseDTO>> adicionarFilme(@RequestBody @Valid FilmeRequestDTO filmeRequestDTO) {
        return retornarSucesso(filmeService.adicionarFilmes(filmeRequestDTO));
    }

    /**
     * Atualizar dados de um filme
     *
     * @param id              com id do filme a ser atualizado
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
    public ResponseEntity<DefaultResponseDTO<FilmeResponseDTO>> atualizarFilme(@PathVariable("id") @NotNull Long id,
                                                                               @RequestBody @Valid FilmeRequestDTO filmeRequestDTO) {

        return retornarSucesso(filmeService.atualizarFilmes(id, filmeRequestDTO));
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
    public ResponseEntity<DefaultResponseDTO<String>> atualizarFilme(@PathVariable("id") @NotNull Long id) {
        filmeService.deletarFilmesPorId(id);
        return retornarSucesso("Filme deletado com sucesso");
    }

    /**
     * Adicionar atores a um filme
     *
     * @param id        com id do filme que vai ter os atores adicionados
     * @param idsAtores com ids dos atores a ser adicionados ao filme
     */

    @Operation(summary = "Adiciona atores a um filme.", description = "Adiciona atores a um filme.", tags = {"Filmes"},
            responses =
                    {
                            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
                            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
                    })
    @PatchMapping("/atualizar-atores/filme/{id}")
    public ResponseEntity<DefaultResponseDTO<String>> atualizarAtoresFilme(@PathVariable("id") Long id,
                                                                           @RequestBody @Valid List<Long> idsAtores) {
        filmeService.atualizarAtoresFilme(id, idsAtores);
        return retornarSucesso("Lista de atores atualizada com sucesso");
    }
}
