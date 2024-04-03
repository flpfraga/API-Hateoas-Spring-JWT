package com.fraga.APIRest.service.impl;

import com.fraga.APIRest.exception.BadCredentialsException;
import com.fraga.APIRest.repository.UsuarioRepository;
import com.fraga.APIRest.security.TokenVO;
import com.fraga.APIRest.security.jwt.JwtTokenProvider;
import com.fraga.APIRest.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository repository;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticaionManager;

    public AuthServiceImpl(UsuarioRepository repository, JwtTokenProvider tokenProvider, AuthenticationManager authenticaionManager) {
        this.repository = repository;
        this.tokenProvider = tokenProvider;
        this.authenticaionManager = authenticaionManager;
    }


    public Object access(String username, String password) {
        authenticate(username, password);
        return createToken(username);
    }
    public void authenticate(String username, String password) {
        try {
            authenticaionManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }  catch (Exception e) {
            throw new BadCredentialsException("Invalid username password supplied!");
        }
    }
    
    public TokenVO createToken(String username) {
        var user = repository.loadUserByUsername(username);
        var token = new TokenVO();
        if (user != null) {
            token = tokenProvider.createAccessToken(username, user.getRoles());
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
        
        Map<Object, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("token", token);
        return token;
    }

}
