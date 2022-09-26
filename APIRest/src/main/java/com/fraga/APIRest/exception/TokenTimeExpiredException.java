package com.fraga.APIRest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.auth0.jwt.exceptions.TokenExpiredException;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TokenTimeExpiredException extends TokenExpiredException{
	
	private static final long serialVersionUID = 1L;
	
	public TokenTimeExpiredException( String message) {
		super(message);
	}
}
