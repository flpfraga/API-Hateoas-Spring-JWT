package com.fraga.APIRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fraga.APIRest.data.vo.MovieVO;
import com.fraga.APIRest.service.MovieService;
import com.fraga.APIRest.util.ValidDataParams;

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
    public ResponseEntity<?> readAll(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size) {

        // Option no pagination
        if (page == null) {
            return ResponseEntity.ok(service.readAll(Sort.by(Direction.ASC, "title")));
        }

        // Verify if the params are valids
        ValidDataParams.validReadAll(page, size);

        // Option with pagination
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, "title"));
        return ResponseEntity.ok(service.readAll(pageable));
    }

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

        // Option no pagination
        if (page == null) {
            return ResponseEntity.ok(service.readAll(Sort.by(Direction.ASC, "voteAverage")));
        }

        // Check if the params are valids
        ValidDataParams.validReadAll(page, size);

        // Option with pagination
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, "voteAverage"));
        return ResponseEntity.ok(service.readAll(pageable));
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
        MovieVO movie = new MovieVO();
        movie.setDirector(filter);

        // Option no pagination
        if (page == null) {
            return ResponseEntity.ok(service.findAllWithFilter(movie));
        }

        // Verify if the params are valids
        ValidDataParams.validReadAll(page, size);

        // Option with pagination
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.findAllWithFilter(movie, pageable));
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
        
        System.out.println("filter " + filter);
        // Set a filter param
        MovieVO movie = new MovieVO();
        movie.setTitle(filter);

        // Option no pagination
        if (page == null) {
            return ResponseEntity.ok(service.findAllWithFilter(movie));
        }

        // Verify if the params are valids
        ValidDataParams.validReadAll(page, size);

        // Option with pagination
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.findAllWithFilter(movie, pageable));
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
        MovieVO movie = new MovieVO();
        movie.setGenre(filter);

        // Option no pagination
        if (page == null) {
            return ResponseEntity.ok(service.findAllWithFilter(movie));
        }

        // Verify if the params are valids
        ValidDataParams.validReadAll(page, size);

        // Option with pagination
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.findAllWithFilter(movie, pageable));
    }

    @GetMapping("/actor")
    @Operation(summary = "Finds movies by actor`s name", description = "Finds movies by actor`s name", tags = {
            "Movies" }, responses = { @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
    public List<MovieVO> findAllByActor(
            @RequestParam(value = "query", defaultValue = "") String query) {
        return service.findAllByActor(query);
    }

}
