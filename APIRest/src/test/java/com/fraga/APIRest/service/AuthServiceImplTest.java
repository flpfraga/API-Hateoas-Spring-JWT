package com.fraga.APIRest.service;

import com.fraga.APIRest.data.model.Usuario;
import com.fraga.APIRest.exception.InvalidParams;
import com.fraga.APIRest.mocks.UsuarioMock;
import com.fraga.APIRest.repository.UsuarioRepository;
import com.fraga.APIRest.security.AccountCredentialsRequest;
import com.fraga.APIRest.security.Token;
import com.fraga.APIRest.security.jwt.JwtTokenProvider;
import com.fraga.APIRest.service.impl.AuthServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private JwtTokenProvider tokenProvider;
    @Mock
    private AuthenticationManager authenticaionManager;
    @Spy
    private UsuarioMock usuarioMock;

    @Before
    public void setUp(){
        usuarioMock = new UsuarioMock();
    }

    @Test
    public void testarAutenticarComSucesso(){
        when(authenticaionManager.authenticate(any())).thenReturn(null);

        Usuario usuario = usuarioMock.getUsuario();
        when(usuarioRepository.buscarPorNomeUsuario(anyString())).thenReturn(Optional.of(usuario));

        Token token = new Token("Usuario", true, Date.valueOf(LocalDate.now()),
                Date.valueOf(LocalDate.now()), "AcessoToken", "RefreshToken");
        when(tokenProvider.createAccessToken(anyString(), anyList())).thenReturn(token);

        AccountCredentialsRequest accountCredentialsRequest = new AccountCredentialsRequest();
        accountCredentialsRequest.setUsername("username");
        accountCredentialsRequest.setPassword("password");

        Token retorno = authService.autenticar(accountCredentialsRequest);

        assertEquals("Usuario", retorno.getUsername());
    }

    @Test(expected = InvalidParams.class)
    public void testarAutenticarUsuarioInvalido(){
        when(authenticaionManager.authenticate(any())).thenThrow(InvalidParams.class);

        authService.autenticar(new AccountCredentialsRequest());
    }

    @Test(expected = InvalidParams.class)
    public void testarAutenticarExcessaoUsuarioNaoEncontrado(){
        when(authenticaionManager.authenticate(any())).thenReturn(null);
        when(usuarioRepository.buscarPorNomeUsuario(anyString())).thenReturn(Optional.empty());

        authService.autenticar(new AccountCredentialsRequest());
    }
}
