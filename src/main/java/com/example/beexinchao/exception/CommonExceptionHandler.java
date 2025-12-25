package com.example.beexinchao.exception;

import com.example.beexinchao.exception.response.ContactNotFoundExceptionResponse;
import com.example.beexinchao.exception.response.RegionNotFoundExceptionResponse;
import com.example.beexinchao.exception.runtime.ContactNotFoundException;
import com.example.beexinchao.exception.runtime.RegionNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order
@Slf4j
public class CommonExceptionHandler {
	@ExceptionHandler(RegionNotFoundException.class)
	public ResponseEntity<RegionNotFoundExceptionResponse> handleRegionNotFoundExceptionResponse(
			final RegionNotFoundException exception) {
		log.error("RegionNotFoundException is occurred: {}.", exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RegionNotFoundExceptionResponse());
	}

	@ExceptionHandler(ContactNotFoundException.class)
	public ResponseEntity<ContactNotFoundExceptionResponse> handleContactNotFoundExceptionResponse(
			final ContactNotFoundException exception) {
		log.error("ContactNotFoundException is occurred: {}.", exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ContactNotFoundExceptionResponse());
	}
}
