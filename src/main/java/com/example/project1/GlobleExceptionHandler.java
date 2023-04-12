package com.example.project1;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@ControllerAdvice
public class GlobleExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request)
	{
		ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException exception, WebRequest request)
	{
//		ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), request.getDescription(false));
//		System.out.println(exception.getMessage());			//	null
//		System.out.println(request.getDescription(false));	//	uri=/student/login
		return new ResponseEntity("User Not Found", HttpStatus.NOT_FOUND);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex)
	{
		Map<String, String> errorMap = new HashMap();
	     ex.getBindingResult().getFieldErrors().forEach(error->{
		 errorMap.put(error.getField(), error.getDefaultMessage());
	 });		
		return errorMap;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobleException(Exception exception, WebRequest request)
	{
		ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
	}
}
