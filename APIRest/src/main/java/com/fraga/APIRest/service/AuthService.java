package com.fraga.APIRest.service;

import com.fraga.APIRest.security.TokenVO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService  {



    Object access(String username, String password) ;
    void authenticate(String username, String password) ;
    TokenVO createToken(String username) ;
}
