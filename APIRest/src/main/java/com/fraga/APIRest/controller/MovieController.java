package com.fraga.APIRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fraga.APIRest.data.vo.ActorVO;
import com.fraga.APIRest.data.vo.MovieVO;
import com.fraga.APIRest.service.MovieService;

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

	@GetMapping("/order_title")
	@Operation(summary = "Finds movies order by name", description = "Finds movies order by name. Options pageable", tags = {
			"Movies" }, responses = { @ApiResponse(description = "Success", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
	public ResponseEntity<Page<MovieVO>> findAllByOrderTitle(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "12") Integer size,
			@RequestParam(value = "orderBy", defaultValue = "asc") String orderBy) {
		var sortDirection = "desc".equalsIgnoreCase(orderBy) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "title"));
		return ResponseEntity.ok(service.findAll(pageable));
	}

	@GetMapping("/top_rated")
	@Operation(summary = "Finds movies order by top rated", description = "Finds  movies order by top rated. Option pageable.", tags = {
			"Movies" }, responses = { @ApiResponse(description = "Success", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
	public ResponseEntity<Page<MovieVO>> findAllOrderTopRated(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "12") Integer size,
			@RequestParam(value = "orderBy", defaultValue = "asc") String orderBy) {
		var sortDirection = "desc".equalsIgnoreCase(orderBy) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "voteCount"));
		return ResponseEntity.ok(service.findAll(pageable));
	}

	@GetMapping("/director")
	@Operation(summary = "Finds movies by director`s name", description = "Finds movies by director`s name", tags = {
			"Movies" }, responses = { @ApiResponse(description = "Success", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
	public ResponseEntity<Page<MovieVO>> findAllByDirectorName(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "12") Integer size,
			@RequestParam(value = "query", defaultValue = "") String query) {
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity.ok(service.findAllByDirectorDirector(query, pageable));
	}

	@GetMapping("/search")
	@Operation(summary = "Finds movies by title", description = "Finds movies by title", tags = {
			"Movies" }, responses = { @ApiResponse(description = "Success", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
	public ResponseEntity<Page<MovieVO>> findAllByTitle(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "12") Integer size,
			@RequestParam(value = "query", defaultValue = "") String query) {
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity.ok(service.findAllByTitle(query, pageable));
	}

	@GetMapping("/genre")
	@Operation(summary = "Finds movies by genre", description = "Finds movies by genre", tags = {
			"Movies" }, responses = { @ApiResponse(description = "Success", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
	public ResponseEntity<Page<MovieVO>> findAllByGenre(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "12") Integer size,
			@RequestParam(value = "query", defaultValue = "") String query) {
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity.ok(service.findAllByGenre(query, pageable));
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

	@GetMapping("/{id}")
	@Operation(summary = "Finds a movie", description = "Finds a movie by id", tags = { "Movies" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
	public MovieVO findById(@PathVariable("id") Long id) {
		
		return service.findById(id);
	}

	@PostMapping
	@Operation(summary = "Create a movie", description = "Create a movie", tags = { "Movies" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
	public MovieVO create(@RequestBody MovieVO movie) {
		return service.create(movie);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update a movie", description = "Update a movie",
	tags = {"Movies"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
	})
	public MovieVO update(@PathVariable("id") Long id, @RequestBody MovieVO movie) {
		return service.update(id, movie);
	}

//	@DeleteMapping("/{id}")
//	@Operation(summary = "Delete a movie", description = "Deletea movie",
//	tags = {"Movies"},
//	responses = {
//			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
//			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//			@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
//			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
//	})
//	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
//		service.delete(id);
//		return ResponseEntity.ok().build();
//	}

}
