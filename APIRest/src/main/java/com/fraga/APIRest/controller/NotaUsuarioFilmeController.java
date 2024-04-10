package com.fraga.APIRest.controller;

import com.fraga.APIRest.dto.DefaultResponseDTO;
import com.fraga.APIRest.service.NotaUsuarioFilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

public class NotaUsuarioFilmeController implements DefaultController {

    private final NotaUsuarioFilmeService notaUsuarioFilmeService;

    public NotaUsuarioFilmeController(NotaUsuarioFilmeService notaUsuarioFilmeService) {
        this.notaUsuarioFilmeService = notaUsuarioFilmeService;
    }

    /**
     * Atribui ou atualiza a nota de um usuário para um filme
     *
     * @param idUsuario com id do usuário que votou
     * @param idFilme   com id do filme votado
     * @param nota      com a nota atribuída
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
    public ResponseEntity<DefaultResponseDTO<String>> autalizaVotoUsuarioPorFilme(@RequestParam @NotNull Long idUsuario,
                                                                                  @RequestParam @NotNull Long idFilme,
                                                                                  @RequestParam @NotNull Integer nota) {
        notaUsuarioFilmeService.atualizarNotaFilme(idUsuario, idFilme, nota);
        return retornarSucesso("Nota atribuída com sucesso!");
    }
}
