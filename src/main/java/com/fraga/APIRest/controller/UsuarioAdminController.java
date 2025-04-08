package com.fraga.APIRest.controller;

import com.fraga.APIRest.dto.DefaultResponseDTO;
import com.fraga.APIRest.dto.UsuarioResponseDTO;
import com.fraga.APIRest.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users/v1")
@Tag(name = "Usuario Admin", description = "Endpoints para gerenciar a parte administrativa dos usuários")

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

    /**
     * Atribuir permissões de admin para um usuário
     *
     * @Param id com id do usuário a receber as permissões
     */
    @PatchMapping("/common-to-admin/{id}")
    @Operation(summary = "Create a admin user", description = "Create a user", tags = {"Admin"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),})
    public ResponseEntity<DefaultResponseDTO<String>> atualizarUsuarioAdmin(@PathVariable ("id")  Long id) {

        usuarioService.atualizarUsuarioAdmin(id);
        return retornarSucesso("Adicionado credencial de admin com sucesso!");
    }

    /**
     * Atribuir permissões de admin para um usuário
     *
     * @Param id com id do usuário a remover as permissões
     */
    @PatchMapping("/admin-to-common/{id}")
    @Operation(summary = "Create a admin user", description = "Create a user", tags = {"Admin"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),})
    public ResponseEntity<DefaultResponseDTO<String>> atualizarUsuarioCommon(@PathVariable ("id")  Long id) {

        usuarioService.atualizarUsuarioCommon(id);
        return retornarSucesso("Removida credencial de admin com sucesso!");
    }



}
