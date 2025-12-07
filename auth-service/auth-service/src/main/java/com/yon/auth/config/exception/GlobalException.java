package com.yon.auth.config.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class GlobalException {
	@ExceptionHandler(AuthException.class)
	ResponseEntity<String> exception(AuthException e){
		
		return ResponseEntity.ok(e.getMessage());
	}
	

}
