package com.fraga.APIRest.controller;

import com.fraga.APIRest.security.AccountCredentialsRequest;
import com.fraga.APIRest.security.Token;
import com.fraga.APIRest.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private AccountCredentialsRequest credentials;
    private Token token;

    @BeforeEach
    void setUp() {
        credentials = new AccountCredentialsRequest();
        credentials.setUsername("testuser");
        credentials.setPassword("testpass");

        token = new Token();
        token.setUsername("testuser");
        token.setAunthenticated(true);
        token.setAccessToken("jwt-token");
        token.setCreated(new Date());
        token.setExpiration(new Date(System.currentTimeMillis() + 3600000));
    }

    @Test
    void whenAutenticar_thenReturnToken() {
        // Arrange
        when(authService.autenticar(credentials)).thenReturn(token);

        // Act
        ResponseEntity<Token> response = authController.autenticar(credentials);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(token.getUsername(), response.getBody().getUsername());
        assertEquals(token.getAccessToken(), response.getBody().getAccessToken());
        assertTrue(response.getBody().isAunthenticated());
        
        verify(authService, times(1)).autenticar(credentials);
    }

    @Test
    void whenAutenticarWithInvalidCredentials_thenReturnToken() {
        // Arrange
        credentials.setUsername("invalid");
        credentials.setPassword("invalid");
        Token invalidToken = new Token();
        invalidToken.setAunthenticated(false);
        when(authService.autenticar(credentials)).thenReturn(invalidToken);

        // Act
        ResponseEntity<Token> response = authController.autenticar(credentials);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isAunthenticated());
        
        verify(authService, times(1)).autenticar(credentials);
    }
} 