package com.main;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.main.exceptions.ExceptionResponse;
import com.main.exceptions.ValidationFailureException;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		ExceptionResponse res = new ExceptionResponse();
		res.setStatus(false, HttpStatus.BAD_REQUEST);
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String errCode = error.getCode();
			String message = error.getDefaultMessage();
			res.addError(errCode, message);
		});
		return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ValidationFailureException.class)
	public ResponseEntity<Object> handleValidationFailureException(ValidationFailureException e) {
		ExceptionResponse res = new ExceptionResponse();
		
		res.addError("400", "Malformed request body!");
		res.setStatus(false, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(res, HttpStatus.BAD_REQUEST);
	}

//	@ExceptionHandler(GeneralException.class)
//	public ResponseEntity<ExceptionResponse> handleGeneralExceptions(GeneralException e) {
//		ExceptionResponse res = new ExceptionResponse();
//		res.setMessage(e.getMessage());
//		res.setHttpStatus(e.getHttpStatus());
//		res.setTimestamp(e.getTimestamp());
//		return new ResponseEntity<ExceptionResponse>(res, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}
