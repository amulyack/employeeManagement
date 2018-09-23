package com.mindtree.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 8419173357050217696L;

	public EmployeeNotFoundException(String exception) {
		super(exception);
	}

}
