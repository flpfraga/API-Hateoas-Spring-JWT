package com.fraga.APIRest.controller;

import com.fraga.APIRest.dto.DefaultResponseDTO;
import com.fraga.APIRest.dto.FilmeResponseDTO;
import com.fraga.APIRest.service.FilmeService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/catalogo-filmes")
@Tag(name = "Movies", description = "EndPoints for Managing Movies")
public class FilmesController implements DefaultController {

    private final FilmeService filmeService;
    private final ModelMapper mapper;

    public FilmesController(FilmeService filmeService, ModelMapper mapper) {
        this.filmeService = filmeService;
        this.mapper = mapper;
    }


    /**
     * Busca um filme através do ID informado
     *
     * @param id com identificador do filme buscado
     * @return FilmeResponseDTO
     */
    @GetMapping("/filmes/{id}")
    @Operation(summary = "Buscar filme pelo id", description = "Buscar filme pelo id", tags = {"Filmes"},
            responses =
                    {
                            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
                            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
                    })
    public ResponseEntity<DefaultResponseDTO<FilmeResponseDTO>> buscarFilmePorId(@PathVariable @NotNull Long id) {
        return retornarSucesso(mapper.map(filmeService.buscarFilmePorId(id), FilmeResponseDTO.class));
    }

    /**
     * Buscar todos os filmes paginados e ordenados por algum de seus parâmetros
     *
     * @param pagina     com número da página buscada
     * @param tamanho    com tamanho de elementos da página
     * @param ordenarPor com nome do parâmetro para ordenar a listagem de filmes
     * @return Page<FilmeResponseDTO>
     */
    @GetMapping("/filmes")
    @Operation(summary = "Finds all movies sorted by name", description = "Finds movies sorted by name. Options pageable",
            tags = {"Movies"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<DefaultResponseDTO<Page<FilmeResponseDTO>>> buscarFilmesOrdenadosPorParametro(@RequestParam(required = false) Integer pagina,
                                                                                                        @RequestParam(required = false) Integer tamanho,
                                                                                                        @RequestParam(required = false) String ordenarPor) {

        return retornarSucesso(filmeService.buscarFilmesOrdenadosPorParametro(pagina, tamanho, ordenarPor));
    }

    /**
     * Buscar filmes paginados e ordenados pela média dos votos recebidos
     *
     * @param pagina  com número da página buscada
     * @param tamanho com tamanho de elementos da página
     * @param direcao com indicação de ordenação crescente ou decrescente
     * @return List<MovieVO>
     */
    @GetMapping("/media-votos")
    @Operation(summary = "Buscar filmes ordenados pelos média dos votos", description = "Buscar filmes ordenados pelos média dos votos", tags = {
            "Movies"}, responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),})
    public ResponseEntity<DefaultResponseDTO<Page<FilmeResponseDTO>>> buscarOrdenadosMediaVotos(@RequestParam(required = false) Integer pagina,
                                                                            @RequestParam(required = false) Integer tamanho,
                                                                            @RequestParam(required = false, defaultValue = "Desc") String direcao) {

        return retornarSucesso(filmeService.buscarFilmesOrdenadosPorMediaVotos(pagina, tamanho, direcao));
    }


}
