package com.fresco.dbrestapi.exception;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fresco.dbrestapi.model.ErrorBean;

@RestControllerAdvice(annotations = RestController.class)
@SuppressWarnings("rawtypes")
public class GlobalExceptionHandler {
	
	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity handleResourceNotFoundException(ResourceNotFoundException e) {
		e.printStackTrace();
		ErrorBean error = new ErrorBean(LocalDateTime.now().toString(), HttpStatus.NOT_FOUND.toString(),
				e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler({ IOException.class, JsonParseException.class, JsonMappingException.class })
	public ResponseEntity handleException(IOException e) {
		ErrorBean error = new ErrorBean(LocalDateTime.now().toString(), HttpStatus.BAD_REQUEST.toString(),
				"missing/invalid info");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity handleException(Exception e) {
		ErrorBean error = new ErrorBean(LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR.toString(),
				e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
