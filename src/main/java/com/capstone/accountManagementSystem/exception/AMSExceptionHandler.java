package com.capstone.accountManagementSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class AMSExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<String> customException(CustomException exception) {
		String exceptionMsg = exception.getMessage();
		return new ResponseEntity<String>(exceptionMsg, HttpStatus.NOT_FOUND);

	}
}
