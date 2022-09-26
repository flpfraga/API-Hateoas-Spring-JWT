package com.fraga.APIRest.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fraga.APIRest.data.error.ErrorMessage;
import com.fraga.APIRest.exception.IntegrityConstraintViolationException;
import com.fraga.APIRest.exception.InvalidCallForUserException;
import com.fraga.APIRest.exception.InvalidJwtAuthenticationException;
import com.fraga.APIRest.exception.ResourceNotFoundException;
import com.fraga.APIRest.exception.TokenTimeExpiredException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ErrorMessage> handleBadRequestExceptions(Exception ex) {
		ErrorMessage error = new ErrorMessage("Not Found!", HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidJwtAuthenticationException.class)
	public final ResponseEntity<ErrorMessage> handleInvalidJwtAuthenticationException(Exception ex) {
		ErrorMessage error = new ErrorMessage("Not Found!",HttpStatus.FORBIDDEN.value(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(IntegrityConstraintViolationException.class)
	public final ResponseEntity<ErrorMessage> handleIntegrityConstraintViolationException(Exception ex) {
		ErrorMessage error = new ErrorMessage("Was Exists!",HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(TokenTimeExpiredException.class)
	public final ResponseEntity<ErrorMessage> handleTokenTimeExpiredException(Exception ex) {
		ErrorMessage error = new ErrorMessage("Token Expired!",HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(InvalidCallForUserException.class)
	public final ResponseEntity<ErrorMessage> handleInvalidCallForUserException(Exception ex) {
		ErrorMessage error = new ErrorMessage("Not permited",HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}
	
}
