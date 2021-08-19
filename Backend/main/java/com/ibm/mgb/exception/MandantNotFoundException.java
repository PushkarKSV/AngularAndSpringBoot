package com.ibm.mgb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MandantNotFoundException extends RuntimeException {

	public MandantNotFoundException(String message) {
		super(message);
	}
}


	