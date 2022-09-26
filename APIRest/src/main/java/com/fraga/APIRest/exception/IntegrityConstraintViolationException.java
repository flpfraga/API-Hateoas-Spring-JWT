package com.fraga.APIRest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class IntegrityConstraintViolationException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public IntegrityConstraintViolationException(String message) {
		super(message);
	}
}
