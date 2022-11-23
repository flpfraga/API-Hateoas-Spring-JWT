package com.fraga.APIRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    /**
     * Get a specific user by id number.
     * Path variable index the user to be returned!
     * 
     * @param Long id
     * @return UserVO
     */
    @GetMapping("id/{id}")
    @Operation(summary = "Finds a user", description = "Finds a user by id", tags = { "Users" }, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
    public ResponseEntity<UserVO> readById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.readById(id));
    }

    /**
     * Create a new user.
     * The user`s attributes must be passed by body params in json format!
     * 
     * @Param UserVO
     * @return UserVO
     */
    @PostMapping
    @Operation(summary = "Create a user", description = "Create a user", tags = { "Users" }, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
    public ResponseEntity<UserVO> create(@RequestBody UserVO userVO) {

        return ResponseEntity.ok(service.create(userVO));
    }

    /**
     * Update a save user.
     * The user`s attributes to be updated must be passed by body params in json
     * format!
     * 
     * @param Long id, UserVO
     * @param Long id, UserVO
     * @return UserVO
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a user", description = "Update a user", tags = { "Users" }, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
    public ResponseEntity<UserVO> update(@PathVariable("id") Long id, @RequestBody UserVO userVO) {

        return ResponseEntity.ok(service.update(id, userVO));
    }

    /**
     * Desactive a commom user.
     * 
     * @param Long
     * @return No_Content
     */
    @PatchMapping("/desable/{id}")
    @Operation(summary = "Desactivate a user", description = "Desactivate a user", tags = { "Users" }, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })

    public ResponseEntity<String> desactiveCommomUser(@PathVariable("id") Long id) {
        service.desactiveCommomUser(id);
        return new ResponseEntity<String>("Desctivate", HttpStatus.NO_CONTENT);
    }

    /**
     * Update or give a new vote for from a user for a movie.
     * 
     * @param id
     * @param movie_id
     * @param vote
     * @return
     */
    @PatchMapping("/vote/{id}")
    @Operation(summary = "Add a vote for a movie", description = "Add a vote for a movie", tags = {
            "Users" }, responses = { @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content), })
    public ResponseEntity<?> voteForMovie(
            @PathVariable("id") Long id,
            @RequestParam(value = "movie_id") Long movie_id,
            @RequestParam(value = "vote", required = true) Long vote) {

        return service.voteForMovie(id, movie_id, vote);

    }

}
