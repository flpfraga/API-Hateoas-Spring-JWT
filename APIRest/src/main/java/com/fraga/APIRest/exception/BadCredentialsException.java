package com.fraga.APIRest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class BadCredentialsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public BadCredentialsException( String message) {
		super(message);
	}
}
