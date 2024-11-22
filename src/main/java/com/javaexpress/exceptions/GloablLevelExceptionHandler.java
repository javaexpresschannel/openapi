package com.javaexpress.exceptions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GloablLevelExceptionHandler {

	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<ErrorResponse> handleCommonException(HttpMediaTypeNotSupportedException ex) {
		log.error("HttpMediaTypeNotSupportedException {} ",ex.getMessage());
		return new ResponseEntity<>(
				populateErrorResponse(ex, HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Unsupported Media Type"),
				HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handleCommonException(DataIntegrityViolationException ex) {
		return new ResponseEntity<>(
				populateErrorResponse(ex, HttpStatus.CONFLICT, "Already Exists"),
				HttpStatus.CONFLICT);
	}
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(ResourceNotFoundException ex) {
		return new ResponseEntity<>(
				populateErrorResponse(ex, HttpStatus.NOT_FOUND, "Client Error"),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception ex) {
		return new ResponseEntity<>(
				populateErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "Something Went Wrong"),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException ex) {
		var errors  = ex.getAllErrors().stream()
			.map(msg -> ErrorData.builder()
						.code(HttpStatus.BAD_REQUEST.name())
						.status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
						.title("Data Validation Failed")
						.detail(msg.getDefaultMessage())
						.build())
			.collect(Collectors.toList());
		var errorResponse = ErrorResponse.builder().errorData(errors).build();
		return new ResponseEntity<>(errorResponse,
				HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler({IllegalArgumentException.class,
		HttpMessageNotReadableException.class})
	public ResponseEntity<ErrorResponse> handleCommonException(Exception ex) {
		return new ResponseEntity<>(
				populateErrorResponse(ex, HttpStatus.BAD_REQUEST, "Client Error"),
				HttpStatus.BAD_REQUEST);
	}
	
	public ErrorResponse populateErrorResponse(Exception ex,HttpStatus status,String title) {
		ErrorData errorData = ErrorData.builder()
							.code(status.name())
							.status(String.valueOf(status.value()))
							.title(title)
							.detail(ex.getMessage())
							.localDateTime(LocalDateTime.now())
							.build();
		
		return ErrorResponse.builder()
				.errorData(List.of(errorData))
				.build();
	}
	
	
}
