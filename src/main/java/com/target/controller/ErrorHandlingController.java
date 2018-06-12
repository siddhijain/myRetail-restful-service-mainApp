package com.target.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.target.model.ExceptionResponse;

@ControllerAdvice
public class ErrorHandlingController{
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ExceptionResponse> argsMismatchException(MethodArgumentTypeMismatchException e) throws MethodArgumentTypeMismatchException{
		ExceptionResponse eR = new ExceptionResponse();
		eR.setCode(HttpStatus.BAD_REQUEST.value());
		eR.setMessage("Invalid product id input");
		System.out.println(e);
		return new ResponseEntity<ExceptionResponse>(eR,HttpStatus.BAD_REQUEST);
	} 
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ExceptionResponse> nullPointerException(NullPointerException e) throws NullPointerException{
		ExceptionResponse eR = new ExceptionResponse();
		eR.setCode(HttpStatus.NOT_FOUND.value());
		eR.setMessage("Product details not found");
		System.out.println(e);
		return new ResponseEntity<ExceptionResponse>(eR,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> generalException(Exception e) throws Exception{
		ExceptionResponse eR = new ExceptionResponse();
		eR.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		eR.setMessage(e.getMessage());
		System.out.println(e);
		return new ResponseEntity<ExceptionResponse>(eR,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
