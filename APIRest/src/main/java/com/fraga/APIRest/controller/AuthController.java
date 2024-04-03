package com.fraga.APIRest.controller;

import com.fraga.APIRest.security.AccountCredentialsResponse;
import com.fraga.APIRest.security.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fraga.APIRest.exception.BadCredentialsException;
import com.fraga.APIRest.security.AccountCredentialsRequest;
import com.fraga.APIRest.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth/v1")
@Tag(name = "AuthenticationEndpoint", description = "Authentication for API")
public class AuthController {


    private final AuthService authService;

    private AuthenticationManager authenticaionManager;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    /**
     * Verify if a user is registry and authentication him. Create a Bearer token for this user.
     * @param accountCredentialsRequest com valores de username e password
     * @return Token com dados da autenticação do usuário
     */
    @Operation(summary = "Autentica o usuário retornando um token", tags = {"AuthenticationEndpoint" })

    @PostMapping(value = "/signin", produces = { "application/json", "application/xml",
            "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<Token> autenticar(@RequestBody @Valid AccountCredentialsRequest accountCredentialsRequest) {
        authService.autenticar(accountCredentialsRequest);
        return ResponseEntity.ok(authService.autenticar(accountCredentialsRequest));

    }
}
