package com.mindtree.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mindtree.controller.ResponseDetail;

@RestControllerAdvice
public class EmployeeManagementExceptionHandler extends
		ResponseEntityExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public final ResponseEntity<ResponseDetail> handleNotFoundException(
			EmployeeNotFoundException ex) {
		ResponseDetail exceptionDetail = new ResponseDetail(
				HttpStatus.NOT_FOUND.value(), "Failure", ex.getMessage(), null);
		return new ResponseEntity<ResponseDetail>(exceptionDetail,
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidRequestException.class)
	public final ResponseEntity<ResponseDetail> handleBadRequestException(
			InvalidRequestException ex) {
		ResponseDetail exceptionDetail = new ResponseDetail(
				HttpStatus.BAD_REQUEST.value(), "Failure", ex.getMessage(), null);
		return new ResponseEntity<ResponseDetail>(exceptionDetail,
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ResponseDetail> handleBadRequestException(
			Exception ex) {
		ResponseDetail exceptionDetail = new ResponseDetail(
				HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failure", ex.getMessage(), null);
		return new ResponseEntity<ResponseDetail>(exceptionDetail,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
