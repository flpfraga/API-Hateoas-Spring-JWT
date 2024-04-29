package com.fraga.APIRest.service.impl;

import com.fraga.APIRest.data.model.Permission;
import com.fraga.APIRest.data.model.Usuario;
import com.fraga.APIRest.exception.BadCredentialsException;
import com.fraga.APIRest.exception.InvalidParams;
import com.fraga.APIRest.repository.UsuarioRepository;
import com.fraga.APIRest.security.AccountCredentialsRequest;
import com.fraga.APIRest.security.Token;
import com.fraga.APIRest.security.jwt.JwtTokenProvider;
import com.fraga.APIRest.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticaionManager;

    public AuthServiceImpl(UsuarioRepository usuarioRepository, JwtTokenProvider tokenProvider, AuthenticationManager authenticaionManager) {
        this.usuarioRepository = usuarioRepository;
        this.tokenProvider = tokenProvider;
        this.authenticaionManager = authenticaionManager;
    }

    @Override
    public Token autenticar(AccountCredentialsRequest accountCredentialsRequest) {
        String username = accountCredentialsRequest.getUsername();
        String password = accountCredentialsRequest.getPassword();

        validarUsuario(username, password);

        return gerarToken(username);
    }
    private void validarUsuario(String username, String password) {
        try {
            authenticaionManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }  catch (Exception e) {
            throw new InvalidParams("Não é possível autenticar. Verifique o nome do usuário e a senha.");
        }
    }
    
    private Token gerarToken(String username) {
        Optional<Usuario> usuario = usuarioRepository.buscarPorNomeUsuario(username);

        if(usuario.isPresent()){
            Token token;
            token = tokenProvider.createAccessToken(username, usuario.get().getRoles());
            token.setPermissoes(usuario.get().getPermissions().stream()
                    .map(Permission::getDescription).toList());
            return token;
        } else {
            throw new InvalidParams("Nome do usuário "+username+ " não encontrado!");
        }

    }

}
