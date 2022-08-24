package com.main.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ExceptionResponse {
	private Status status;
	List<Error> errors = new ArrayList<Error>();
	
	public void setStatus(boolean success, HttpStatus httpStatus) {
		status = new Status();
		status.success = success;
		status.httpStatus = httpStatus;
	}
	
	public void addError(String errCode, String message) {
		Error error = new Error();
		error.errCode = errCode;
		error.message = message;
		errors.add(error);
	}
	
	@Data
	class Status {
		private boolean success;
		private HttpStatus httpStatus;
	}
	
	@Data
	class Error {
		private String errCode;
		private String message;
	}
}
