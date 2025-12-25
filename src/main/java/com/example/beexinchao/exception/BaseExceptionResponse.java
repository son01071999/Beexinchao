package com.example.beexinchao.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseExceptionResponse {
	private final long errorCode;
	private final String errorMessage;
}
