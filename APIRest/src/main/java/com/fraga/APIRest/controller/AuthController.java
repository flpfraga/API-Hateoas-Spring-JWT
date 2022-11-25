package com.fraga.APIRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fraga.APIRest.exception.BadCredentialsException;
import com.fraga.APIRest.security.AccountCredentialsVO;
import com.fraga.APIRest.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth/v1")
@Tag(name = "AuthenticationEndpoint", description = "Authentication for API")
public class AuthController {

    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticaionManager;
    
    
    /**
     * Verify if a user is registry and authentication him. Create a Bearer token for this user.
     * @param data
     * @return TokenVO
     */
    @Operation(summary = "Authenticates a user and returns a token", tags = {"AuthenticationEndpoint" })
    @PostMapping(value = "/signin", produces = { "application/json", "application/xml",
            "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> signin(@RequestBody AccountCredentialsVO data) {
        var username = data.getUsername();
        var password = data.getPassword();
        try {
            authenticaionManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }  catch (Exception e) {
            throw new BadCredentialsException("Invalid username password supplied!");
        }
        return ResponseEntity.ok(service.access(username, password));

    }
}
