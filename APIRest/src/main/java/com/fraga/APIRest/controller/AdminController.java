package com.fraga.APIRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fraga.APIRest.data.model.Usuario;
import com.fraga.APIRest.data.vo.MovieVO;
import com.fraga.APIRest.data.vo.UserVO;
import com.fraga.APIRest.exception.InvalidParams;
import com.fraga.APIRest.service.impl.FilmeServiceImpl;
import com.fraga.APIRest.service.UserService;
import com.fraga.APIRest.util.queryManager.QueryParams;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/admin/v1")
@Tag(name = "Admin", description = "EndPoints for Managing Admin Actions")

public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private FilmeServiceImpl movieService;

    @Autowired
    private QueryParams<Usuario> queryParams;

    /**
     * Return all user, not admin, actives. Default, order asc. Pagable option.
     *
     * @param page
     * @param size
     * @param orderBy
     * @return
     */
//    @GetMapping("/users_active")
//
//    @Operation(summary = "Finds all user not admin, actives", description = "Finds all users not admin actives and ordened by name", tags = {
//            "Admin"}, responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content),
//            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
//            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),})
//
//    public ResponseEntity<?> readAllUserActives(@RequestParam(value = "page", required = false) Integer page,
//                                                @RequestParam(value = "size", defaultValue = "12") Integer size,
//                                                @RequestParam(value = "orderBy", defaultValue = "asc") String orderBy) {
//
//        Usuario user = new Usuario();
//        user.setActive(true);
//        queryParams.setEntity(user);
//        queryParams.setPage(page);
//        queryParams.setSize(size);
//
//
//        // Option no pagination
//        if (page == null) {
//            return ResponseEntity.ok(userService.findAllWithFilterAndPagination(queryParams));
//        } else if (!queryParams.pageIsValid()) {
//            throw new InvalidParams("Invalids values for pagination!");
//        }
//
//        //
//        return ResponseEntity.ok(userService.findAllWithFilterAndPagination(queryParams));
//
//    }
//
//    /**
//     * Create a new Admin user.
//     * The user`s attributes must be passed by body params in json format!
//     *
//     * @return UserVO
//     * @Param UserVO
//     */
//    @PostMapping
//    @Operation(summary = "Create a admin user", description = "Create a user", tags = {"Admin"}, responses = {
//            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
//            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
//            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
//            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),})
//    public ResponseEntity<UserVO> create(@RequestBody UserVO userVO) {
//
//        //Check valid not nullable params
//        return ResponseEntity.ok(userService.createAdminUser(userVO));
//    }
//
//    /**
//     * Update a admin user.
//     * The user`s attributes to be updated must be passed by body params in json
//     * format!
//     *
//     * @param Long id, UserVO
//     * @param Long id, UserVO
//     * @return UserVO
//     */
//    @PutMapping("/{id}")
//    @Operation(summary = "Update a admin user", description = "Update a user", tags = {"Admin"}, responses = {
//            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
//            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
//            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
//            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),})
//    public ResponseEntity<UserVO> update(@PathVariable("id") Long id, @RequestBody UserVO userVO) {
//        return ResponseEntity.ok(userService.update(id, userVO));
//    }
//
//    /**
//     * Desactive a admin user.
//     *
//     * @param Long
//     * @return No_Content
//     */
//    @PatchMapping("/desable/{id}")
//    @Operation(summary = "Desactivate a user", description = "Desactivate a user", tags = {"Admin"}, responses = {
//            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
//            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
//            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
//            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),})
//
//    public ResponseEntity<String> desactiveCommomUser(@PathVariable("id") Long id) {
//        userService.desactiveCommomUser(id);
//        return new ResponseEntity<String>("Desctivate", HttpStatus.NO_CONTENT);
//    }
//
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    // MOVIES
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//    /**
//     * Create a new movie.
//     * The user`s attributes must be passed by body params in json format!
//     *
//     * @return MovieVO
//     * @Param MovieVO
//     */
//    @PostMapping("/movie")
//    @Operation(summary = "Create a movie", description = "Create a movie", tags = {"Admin"}, responses = {
//            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
//            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
//            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
//            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),})
//    public ResponseEntity<MovieVO> createMovie(@RequestBody MovieVO movieVO) {
//
//
////        return ResponseEntity.ok(movieService.create(movieVO));
//        return null;
//    }
//
//    /**
//     * Update a movie.
//     */
//    @PutMapping("/movie/id/{id}")
//    @Operation(summary = "Update a movie", description = "Update a movie", tags = {"Admin"}, responses = {
//            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
//            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
//            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
//            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
//    })
//    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody MovieVO movieVO) {
//
//
//        return ResponseEntity.ok(movieService.update(id, movieVO));
//    }
//
//    /**
//     * Delete a specific movie by id number.
//     * Path variable index the user to be deleted!
//     *
//     * @param Long id
//     * @return HttpStatus No_Content
//     */
//    @DeleteMapping("id/{id}")
//    @Operation(summary = "Delete a movie", description = "Delete a movie", tags = {"Admin"}, responses = {
//            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
//            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
//            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
//    })
//    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
////        movieService.delete(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

}
