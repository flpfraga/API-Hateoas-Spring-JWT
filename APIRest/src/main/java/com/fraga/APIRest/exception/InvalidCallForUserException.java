package com.fraga.APIRest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidCallForUserException extends AuthenticationException{
	
	private static final long serialVersionUID = 1L;
	
	public InvalidCallForUserException(String message) {
		super(message);
	}
}
