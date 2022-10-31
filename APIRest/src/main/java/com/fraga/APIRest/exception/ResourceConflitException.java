package com.fraga.APIRest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceConflitException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public ResourceConflitException(String message) {
		super(message);
	}
}
