package com.example.beexinchao.exception.response;

import com.example.beexinchao.exception.BaseExceptionResponse;
import com.example.beexinchao.exception.ErrorConst;

public class RegionNotFoundExceptionResponse extends BaseExceptionResponse {
	public RegionNotFoundExceptionResponse() {
		super(
				ErrorConst.REGION_NOT_FOUND_EXCEPTION_ERROR_CODE,
				ErrorConst.REGION_NOT_FOUND_EXCEPTION_ERROR_MESSAGE);
	}
}
