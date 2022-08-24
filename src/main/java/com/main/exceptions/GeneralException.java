package com.main.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class GeneralException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	private HttpStatus httpStatus;
	private LocalDateTime timestamp;
	
	public GeneralException() {
		super("Something went wrong!");
		message = "Something went wrong!";
		httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		timestamp = LocalDateTime.now();
	}
}
