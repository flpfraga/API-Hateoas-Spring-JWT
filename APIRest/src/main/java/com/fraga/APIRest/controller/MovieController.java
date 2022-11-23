package com.fraga.APIRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fraga.APIRest.data.model.Movie;
import com.fraga.APIRest.data.vo.MovieVO;
import com.fraga.APIRest.exception.InvalidParams;
import com.fraga.APIRest.service.MovieService;
import com.fraga.APIRest.util.queryManager.QueryParams;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/movie/v1")
@Tag(name = "Movies", description = "EndPoints for Managing Movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @Autowired
    private QueryParams<Movie> queryParams;

    /**
     * Get a specific movie by id number.
     * Path variable index the user to be returned!
     * 
     * @param Long
     * @return MovieVO
     */
    @GetMapping("id/{id}")
    @Operation(summary = "Finds a movie", description = "Finds a movie by id", tags = { "Movies" }, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
    public ResponseEntity<MovieVO> readById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.readById(id));
    }

    /**
     * Returns all movies sorted by name. Option pagination
     * 
     * @param Integer
     * @param Integer
     * @param String
     * @return List<MovieVO>
     */
    @GetMapping
    @Operation(summary = "Finds all movies sorted by name", description = "Finds movies sorted by name. Options pageable", tags = {
            "Movies" }, responses = { @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
    public ResponseEntity<?> readAllOrderByName(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size) {

        queryParams.setOrderParam("title");
        queryParams.setPage(page);
        queryParams.setSize(size);
        queryParams.setSortDirection(true);
        if (page == null) {
            return ResponseEntity.ok(service.readAllInOrder(queryParams));
        }

        return ResponseEntity.ok(service.readAllInOrderPagined(queryParams));
    }

    /**
     * Returns all movies sorted by rate. Option pagination
     * 
     * @param Integer
     * @param Integer
     * @param String
     * @return List<MovieVO>
     */
    @GetMapping("/top_rated")
    @Operation(summary = "Finds all movies sorted by rate", description = "Finds movies sorted by rate. Options pageable", tags = {
            "Movies" }, responses = { @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
    public ResponseEntity<?> readAllSortByRate(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size) {

        queryParams.setOrderParam("voteAverage");
        queryParams.setPage(page);
        queryParams.setSize(size);
        queryParams.setSortDirection(false);
        if (page == null) {
            return ResponseEntity.ok(service.readAllInOrder(queryParams));
        }

        return ResponseEntity.ok(service.readAllInOrderPagined(queryParams));
    }

    /**
     * Return movies with a director filter
     * 
     * @param Integer
     * @param Integer
     * @param String
     * @return MovieVO
     */

    @GetMapping("/director")
    @Operation(summary = "Finds movies by director`s name", description = "Finds movies by director`s name", tags = {
            "Movies" }, responses = { @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
    public ResponseEntity<?> findAllByDirectorName(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "filter", defaultValue = "") String filter) {

        // Set a filter param

        Movie movie = new Movie();
        movie.setDirector(filter);
        queryParams.setEntity(movie);
        queryParams.setPage(page);
        queryParams.setSize(size);
        
        // Option no pagination
        if (page == null) {
            return ResponseEntity.ok(service.findAllWithFilter(queryParams));
        } 
        else if (!queryParams.pageIsValid()) {
            throw new InvalidParams("Invalids values for pagination!");
        }
        return ResponseEntity.ok(service.findAllWithFilterAndPagination(queryParams));
    }

    /**
     * Return movies with a title name filter
     * 
     * @param Integer
     * @param Integer
     * @param String
     * @return MovieVO
     */
    @GetMapping("/title")
    @Operation(summary = "Finds movies by title`s name", description = "Finds movies by title`s name", tags = {
            "Movies" }, responses = { @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
    public ResponseEntity<?> findAllByTitleName(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "filter") String filter) {

     // Set a filter param

        Movie movie = new Movie();
        movie.setTitle(filter);
        queryParams.setEntity(movie);
        queryParams.setPage(page);
        queryParams.setSize(size);
        
        // Option no pagination
        if (page == null) {
            return ResponseEntity.ok(service.findAllWithFilter(queryParams));
        } 
        else if (!queryParams.pageIsValid()) {
            throw new InvalidParams("Invalids values for pagination!");
        }
        return ResponseEntity.ok(service.findAllWithFilterAndPagination(queryParams));
    }

    /**
     * Return movies with a genre filter
     * 
     * @param Integer
     * @param Integer
     * @param String
     * @return MovieVO
     */
    @GetMapping("/genre")
    @Operation(summary = "Finds movies by genre", description = "Finds movies by genre", tags = {
            "Movies" }, responses = { @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
    public ResponseEntity<?> findAllByGenre(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "filter", defaultValue = "") String filter) {

     // Set a filter param

        Movie movie = new Movie();
        movie.setGenre(filter);
        queryParams.setEntity(movie);
        queryParams.setPage(page);
        queryParams.setSize(size);
        
        // Option no pagination
        if (page == null) {
            return ResponseEntity.ok(service.findAllWithFilter(queryParams));
        } 
        else if (!queryParams.pageIsValid()) {
            throw new InvalidParams("Invalids values for pagination!");
        }
        return ResponseEntity.ok(service.findAllWithFilterAndPagination(queryParams));
    }

}
