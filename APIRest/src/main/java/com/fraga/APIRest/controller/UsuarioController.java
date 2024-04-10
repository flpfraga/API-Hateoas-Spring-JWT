package com.fraga.APIRest.controller;

import com.fraga.APIRest.dto.DefaultResponseDTO;
import com.fraga.APIRest.dto.UsuarioAtualizarDTO;
import com.fraga.APIRest.dto.UsuarioRequestDTO;
import com.fraga.APIRest.dto.UsuarioResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fraga.APIRest.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users/v1")
@Tag(name = "Users", description = "EndPoints for Managing Users")
public class UsuarioController implements DefaultController {

    private final UsuarioService usuarioService;
    private final ModelMapper mapper;

    public UsuarioController(UsuarioService usuarioService, ModelMapper mapper) {
        this.usuarioService = usuarioService;
        this.mapper = mapper;
    }

    /**
     * Buscar todos usuários paginados
     *
     * @param pagina  com número da página a ser buscada
     * @param tamanho com número de objetos em cada página
     * @return Page<UsuarioResponseDTO>
     */
    @GetMapping("/usuarios")
    @Operation(summary = "Buscar usuário pelo ID", description = "Buscar usuário pelo ID", tags = {"Users"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),})
    public ResponseEntity<DefaultResponseDTO<Page<UsuarioResponseDTO>>> buscarTodosUsuarios(@RequestParam("page") Integer pagina,
                                                                                           @RequestParam("size") Integer tamanho) {

        return retornarSucesso(usuarioService.buscarTodosUsuarios(pagina, tamanho));
    }

    /**
     * Buscar um usuário pelo número do ID
     *
     * @param id com identificador de um usuário que se deseja buscar
     * @return UsuarioResponseDTO
     */
    @GetMapping("/usuario/{id}")
    @Operation(summary = "Buscar usuário pelo ID", description = "Buscar usuário pelo ID", tags = {"Users"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),})
    public ResponseEntity<DefaultResponseDTO<UsuarioResponseDTO>> buscarUsuarioPorId(@PathVariable("id") Long id) {
        return retornarSucesso(mapper.map(usuarioService.buscarUsuarioPorId(id), UsuarioResponseDTO.class));
    }

    /**
     * Buscar um usuário pelo nome do usuário
     *
     * @param nomeUsuario com nome do usuário a ser buscado
     * @return UsuarioResponseDTO
     */
    @GetMapping("/usuario-por-nome")
    @Operation(summary = "Buscar usuário pelo ID", description = "Buscar usuário pelo ID", tags = {"Users"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),})
    public ResponseEntity<DefaultResponseDTO<UsuarioResponseDTO>> buscarUsuarioPorNome(@RequestBody String nomeUsuario) {
        return retornarSucesso(usuarioService.buscarUsuarioPorNome(nomeUsuario));
    }

    /**
     * Criar um novo usuário através dos dados informados.
     *
     * @return UsuarioResponseDTO
     * @Param usuarioRequestDTO com os dados de nome de usuário, nome completo e senha.
     */
    @PostMapping("/usuario")
    @Operation(summary = "Criar um usuário", description = "Criar um usuário", tags = {"Users"}, responses =
            {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            })

    public ResponseEntity<DefaultResponseDTO<UsuarioResponseDTO>> criarNovoUsuario(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {
        return retornarSucesso(usuarioService.criarNovoUsuario(usuarioRequestDTO));
    }

    /**
     * Atualizar informações de um usuário.
     *
     * @param id com identificador do usuário a ser modificado
     * @param usuarioAtualizarDTO com dados a serem atualizados do usuário
     * @return UserVO
     */
    @PutMapping("/usuario/{id}")
    @Operation(summary = "Update a user", description = "Update a user", tags = {"Users"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),})
    public ResponseEntity<DefaultResponseDTO<UsuarioResponseDTO>> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioAtualizarDTO usuarioAtualizarDTO) {

        return retornarSucesso(usuarioService.atualizarUsuario(id, usuarioAtualizarDTO));
    }

    /**
     * Atualizar informações de um usuário.
     *
     * @param id com identificador do usuário a ser desativado
     * @return UserVO
     */
    @PutMapping("/desativar-usuario/{id}")
    @Operation(summary = "Update a user", description = "Update a user", tags = {"Users"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthoried", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),})
    public ResponseEntity<DefaultResponseDTO<String>> desativarUsuario(@PathVariable Long id) {
        usuarioService.desativarUsuario(id);
        return retornarSucesso("Usuário desatiado com sucesso.");
    }



}
