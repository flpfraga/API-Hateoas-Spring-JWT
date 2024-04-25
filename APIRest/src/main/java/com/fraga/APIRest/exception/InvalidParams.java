package com.fraga.APIRest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.auth0.jwt.exceptions.TokenExpiredException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidParams extends ServiceException{
	
	private static final long serialVersionUID = 1L;
	
	public InvalidParams( String message) {
		super(message);
	}
}
