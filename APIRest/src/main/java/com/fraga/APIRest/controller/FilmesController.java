package com.fraga.APIRest.controller;

import com.fraga.APIRest.dto.FilmeResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fraga.APIRest.data.model.Filme;
import com.fraga.APIRest.exception.InvalidParams;
import com.fraga.APIRest.service.impl.FilmeServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/catalogo-filmes")
@Tag(name = "Movies", description = "EndPoints for Managing Movies")
public class FilmesController {

    private final FilmeServiceImpl filmeService;

    public FilmesController(FilmeServiceImpl filmeService) {
        this.filmeService = filmeService;
    }


    /**
     *Busca um filme através do ID informado
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
    public ResponseEntity<FilmeResponseDTO> buscarFilmePorId(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(filmeService.buscarFilmePorId(id));
    }

    /**
     * Returns all movies sorted by name. Option pagination
     *
     * @return List<MovieVO>
     */
//    @GetMapping
//    @Operation(summary = "Finds all movies sorted by name", description = "Finds movies sorted by name. Options pageable",
//            tags =
//                    {
//                            "Movies"},
//            responses =
//                    {
//                            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
//                            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//                            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
//                            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//                            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
//                    })
//    public ResponseEntity<?> readAllOrderByName(@RequestParam(value = "page", required = false) Integer page,
//                                                @RequestParam(value = "size", defaultValue = "12") Integer size) {
//
//        return ResponseEntity.ok(filmeService.(queryParams));
//    }
//
//    /**
//     * Returns all movies sorted by rate. Option pagination
//     *
//     * @param Integer
//     * @param Integer
//     * @param String
//     * @return List<MovieVO>
//     */
//    @GetMapping("/top_rated")
//    @Operation(summary = "Finds all movies sorted by rate", description = "Finds movies sorted by rate. Options pageable", tags = {
//            "Movies"}, responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content),
//            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
//            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),})
//    public ResponseEntity<?> readAllSortByRate(
//            @RequestParam(value = "page", required = false) Integer page,
//            @RequestParam(value = "size", defaultValue = "12") Integer size) {
//
//        queryParams.setOrderParam("voteAverage");
//        queryParams.setPage(page);
//        queryParams.setSize(size);
//        queryParams.setSortDirection(false);
//        if (page == null) {
//            return ResponseEntity.ok(service.readAllInOrder(queryParams));
//        }
//
//        return ResponseEntity.ok(service.readAllInOrderPagined(queryParams));
//    }
//
//    /**
//     * Return movies with a director filter
//     *
//     * @param Integer
//     * @param Integer
//     * @param String
//     * @return MovieVO
//     */
//
//    @GetMapping("/director")
//    @Operation(summary = "Finds movies by director`s name", description = "Finds movies by director`s name", tags = {
//            "Movies"}, responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content),
//            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
//            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),})
//    public ResponseEntity<?> findAllByDirectorName(
//            @RequestParam(value = "page", required = false) Integer page,
//            @RequestParam(value = "size", defaultValue = "12") Integer size,
//            @RequestParam(value = "filter", defaultValue = "") String filter) {
//
//        // Set a filter param
//
//        Filme movie = new Filme();
//        movie.setDirector(filter);
//        queryParams.setEntity(movie);
//        queryParams.setPage(page);
//        queryParams.setSize(size);
//
//        // Option no pagination
//        if (page == null) {
//            return ResponseEntity.ok(service.findAllWithFilter(queryParams));
//        } else if (!queryParams.pageIsValid()) {
//            throw new InvalidParams("Invalids values for pagination!");
//        }
//        return ResponseEntity.ok(service.findAllWithFilterAndPagination(queryParams));
//    }
//
//    /**
//     * Return movies with a title name filter
//     *
//     * @param Integer
//     * @param Integer
//     * @param String
//     * @return MovieVO
//     */
//    @GetMapping("/title")
//    @Operation(summary = "Finds movies by title`s name", description = "Finds movies by title`s name", tags = {
//            "Movies"}, responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content),
//            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
//            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),})
//    public ResponseEntity<?> findAllByTitleName(
//            @RequestParam(value = "page", required = false) Integer page,
//            @RequestParam(value = "size", defaultValue = "12") Integer size,
//            @RequestParam(value = "filter") String filter) {
//
//        // Set a filter param
//
//        Filme movie = new Filme();
//        movie.setTitle(filter);
//        queryParams.setEntity(movie);
//        queryParams.setPage(page);
//        queryParams.setSize(size);
//
//        // Option no pagination
//        if (page == null) {
//            return ResponseEntity.ok(service.findAllWithFilter(queryParams));
//        } else if (!queryParams.pageIsValid()) {
//            throw new InvalidParams("Invalids values for pagination!");
//        }
//        return ResponseEntity.ok(service.findAllWithFilterAndPagination(queryParams));
//    }
//
//    /**
//     * Return movies with a genre filter
//     *
//     * @param Integer
//     * @param Integer
//     * @param String
//     * @return MovieVO
//     */
//    @GetMapping("/genre")
//    @Operation(summary = "Finds movies by genre", description = "Finds movies by genre", tags = {
//            "Movies"}, responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content),
//            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
//            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),})
//    public ResponseEntity<?> findAllByGenre(
//            @RequestParam(value = "page", required = false) Integer page,
//            @RequestParam(value = "size", defaultValue = "12") Integer size,
//            @RequestParam(value = "filter", defaultValue = "") String filter) {
//
//        // Set a filter param
//
//        Filme movie = new Filme();
//        movie.setGenre(filter);
//        queryParams.setEntity(movie);
//        queryParams.setPage(page);
//        queryParams.setSize(size);
//
//        // Option no pagination
//        if (page == null) {
//            return ResponseEntity.ok(service.findAllWithFilter(queryParams));
//        } else if (!queryParams.pageIsValid()) {
//            throw new InvalidParams("Invalids values for pagination!");
//        }
//        return ResponseEntity.ok(service.findAllWithFilterAndPagination(queryParams));
//    }

    /**
     * Atribui ou atualiza a nota de um usuário para um filme
     *
     * @param id
     * @param movie_id
     * @param vote
     * @return
     */
    @PatchMapping("/adicionar-voto")
    @Operation(summary = "Adicionar voto para um filme", description = "Adicionar voto para um filme", tags = {
            "Users"}, responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),})
    public ResponseEntity<?> voteForMovie(
            @PathVariable("id") Long id,
            @RequestParam(value = "movie_id") Long movie_id,
            @RequestParam(value = "vote", required = true) Long vote) {

        return null;

    }


}
