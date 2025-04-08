package com.fraga.APIRest.service;


import com.fraga.APIRest.security.AccountCredentialsRequest;
import com.fraga.APIRest.security.AccountCredentialsResponse;
import com.fraga.APIRest.security.Token;
import org.springframework.stereotype.Service;

@Service
public interface AuthService  {
    /**
     * Faz a validação do usuário e retorna um token de autenticação
     * @param accountCredentialsRequest com dados de username e password.
     * @return Token
     */
    Token autenticar (AccountCredentialsRequest accountCredentialsRequest) ;

}
