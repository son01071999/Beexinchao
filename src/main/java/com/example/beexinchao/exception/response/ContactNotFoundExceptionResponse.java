package com.example.beexinchao.exception.response;

import com.example.beexinchao.exception.BaseExceptionResponse;
import com.example.beexinchao.exception.ErrorConst;

public class ContactNotFoundExceptionResponse extends BaseExceptionResponse {
	public ContactNotFoundExceptionResponse() {
		super(
				ErrorConst.CONTACT_NOT_FOUND_EXCEPTION_ERROR_CODE,
				ErrorConst.CONTACT_NOT_FOUND_EXCEPTION_ERROR_MESSAGE);
	}
}
