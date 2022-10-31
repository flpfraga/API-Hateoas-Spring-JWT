package com.fraga.APIRest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.auth0.jwt.exceptions.TokenExpiredException;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidParams extends TokenExpiredException{
	
	private static final long serialVersionUID = 1L;
	
	public InvalidParams( String message) {
		super(message);
	}
}
