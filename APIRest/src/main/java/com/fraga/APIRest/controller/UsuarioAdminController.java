package com.fraga.APIRest.controller;

import com.fraga.APIRest.dto.DefaultResponseDTO;
import com.fraga.APIRest.dto.UsuarioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fraga.APIRest.service.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/admin/v1")
@Tag(name = "Admin", description = "EndPoints for Managing Admin Actions")

public class UsuarioAdminController implements DefaultController {

    private final UsuarioService usuarioService;

    public UsuarioAdminController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    /**
     * Listar todos usuário ativos de forma paginada
     *
     * @param pagina  com o número da página a ser retornada
     * @param tamanho com o tamanho da página a ser retornado
     */
    @GetMapping("/usuarios-ativos")

    @Operation(summary = "Finds all user not admin, actives", description = "Finds all users not admin actives and ordened by name", tags = {
            "Admin"}, responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),})

    public ResponseEntity<DefaultResponseDTO<Page<UsuarioResponseDTO>>> buscarTodosUsuariosAtivos(@RequestParam Integer pagina,
                                                                                                  @RequestParam Integer tamanho) {

        return retornarSucesso(usuarioService.buscarTodosUsuariosAtivos(pagina, tamanho));

    }
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
