package com.fraga.APIRest.controller;

import com.fraga.APIRest.security.AccountCredentialsRequest;
import com.fraga.APIRest.security.Token;
import com.fraga.APIRest.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth/v1")
@Tag(name = "AuthenticationEndpoint", description = "Authentication for API")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Faz a validação do usuário e retorna um token de autenticação
     * @param accountCredentialsRequest com valores de username e password
     * @return Token com dados da autenticação do usuário
     */
    @Operation(summary = "Autentica o usuário retornando um token", tags = {"AuthenticationEndpoint" })

    @PostMapping(value = "/signin", produces = { "application/json", "application/xml",
            "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<Token> autenticar(@RequestBody @Valid AccountCredentialsRequest accountCredentialsRequest) {
        return ResponseEntity.ok(authService.autenticar(accountCredentialsRequest));

    }
}
