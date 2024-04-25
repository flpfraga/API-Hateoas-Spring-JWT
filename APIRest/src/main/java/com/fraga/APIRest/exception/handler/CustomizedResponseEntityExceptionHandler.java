package com.fraga.APIRest.exception.handler;

import com.fraga.APIRest.data.error.ErrorMessage;
import com.fraga.APIRest.exception.ValidationErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {
	@ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});
		ErrorMessage errorResponse = new ErrorMessage("Parâmetros Inválidos", HttpStatus.BAD_REQUEST.value(), ex.getMessage(),errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ValidationErrorException.class)
	public ResponseEntity<Map<String, String>> handleValidationErrorException(ValidationErrorException ex) {
		return ResponseEntity.badRequest().body(ex.getErros());
	}
}
