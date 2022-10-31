package com.fraga.APIRest.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fraga.APIRest.exception.BadCredentialsException;
import com.fraga.APIRest.repository.UserRepository;
import com.fraga.APIRest.security.TokenVO;
import com.fraga.APIRest.security.jwt.JwtTokenProvider;

@Service
public class AuthService  {

    @Autowired
    UserRepository repository;

    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    private AuthenticationManager authenticaionManager;


    public Object access(String username, String password) {
        authenticate(username, password);
        return createToken(username);
    }
    public void authenticate(String username, String password) {
        try {
            System.out.println("password invalido username " + username + " pass " + password);
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
