package com.mogalasadbaig.kafka.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerClass {

	@ExceptionHandler(GenericException.class)
	public ResponseEntity<String> genericHandler(GenericException e, WebRequest request)
	{
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
	}
	
//	@ExceptionHandler(RestaurantNotFoundException.class)
//	public ResponseEntity<String> menuAddExceptionHandler(RestaurantNotFoundException e)
//	{
//		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//	}
//	
//	
//	@ExceptionHandler(ItemNotFoundException.class)
//	public ResponseEntity<String> ItemNotFoundException(ItemNotFoundException e)
//	{
//		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//	}
	
}
