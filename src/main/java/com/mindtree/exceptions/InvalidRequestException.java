package com.mindtree.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends Exception {

	private static final long serialVersionUID = 1187145553035841578L;
	
	public InvalidRequestException(String exception) {
		super(exception);
	}

}
