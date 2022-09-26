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

import com.fraga.APIRest.data.vo.UserVO;
import com.fraga.APIRest.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/user/v1")
@Tag(name = "Users", description = "EndPoints for Managing Users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/search/all")
	@Operation(summary = "Finds all user", description = "Finds all users", tags = { "Users" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
	public List<UserVO> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "12") Integer limit) {
		return service.findAll();
	}

	@GetMapping("/search/active_users")
	@Operation(summary = "Finds all user not admin, actives", description = "Finds all users not admin actives and ordened by name", tags = {
			"Users" }, responses = { @ApiResponse(description = "Success", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
	public ResponseEntity<Page<UserVO>> findAllActives(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "12") Integer size,
			@RequestParam(value = "orderBy", defaultValue = "asc") String orderBy) {
		var sortDirection = "desc".equalsIgnoreCase(orderBy) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "userName"));
		return ResponseEntity.ok(service.findAllActives(pageable));
	}

	@GetMapping("id/{id}")
	@Operation(summary = "Finds a user", description = "Finds a user by id", tags = { "Users" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
	public UserVO findById(@PathVariable("id") Long id) {
		System.out.println("id get by id " + id);
		return service.findById(id);
	}

	@PostMapping
	@Operation(summary = "Create a user", description = "Create a user", tags = { "Users" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
	public UserVO create(@RequestBody UserVO user) {
		System.out.println(user);
		return service.create(user);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update a user", description = "Update a user", tags = { "Users" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
	public UserVO update(@PathVariable("id") Long id, @RequestBody UserVO user) {
		return service.update(id, user);
	}

	@PutMapping("/desactive/{id}")
	@Operation(summary = "Desactivate a user", description = "Desactivate a user", tags = { "Users" }, responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = @Content),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })

	public ResponseEntity<?> desactive(@PathVariable("id") Long id) {
		service.desactive(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/vote/{id}")
	@Operation(summary = "Add a vote for a movie", description = "Add a vote for a movie", tags = {
			"Users" }, responses = { @ApiResponse(description = "Success", responseCode = "200", content = @Content),
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
	public ResponseEntity<?> movieVoted(
			@PathVariable("id") Long id,
			@RequestParam(value = "movie_id", defaultValue = "0") Long movie_id,
			@RequestParam(value = "query", defaultValue = "0", required = true) Long query) {
		return service.movieVoted(id, movie_id, query);

	}

}
