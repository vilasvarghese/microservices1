package com.idfcbank.savingaccount.advise;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		Map<String, Object> responseBody = new LinkedHashMap();

		responseBody.put("timestamp", LocalDate.now());
		responseBody.put("status", status.value());

		Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

		responseBody.put("errors", errors);

		return new ResponseEntity<Object>(responseBody, headers, status);
	}
}

/*
@ControllerAdvice Annotation:

	Defines global exception handler 
		can handle exceptions across the whole Spring Boot application, 
		not just in a single controller.
	It intercepts exceptions thrown 
		by any controller method and 
		provides a way to handle them globally.

extends ResponseEntityExceptionHandler:

	The class extends ResponseEntityExceptionHandler, 
		convenient base class for handling various 
			Spring exceptions, 
			particularly when dealing with REST APIs. 
			provides default methods for handling exceptions, 
			but they can be overridden for customization.
handleMethodArgumentNotValid Method:

	overrides 
		default exception handler 
			for validation errors caused by 
				MethodArgumentNotValidException.
	MethodArgumentNotValidException occurs 
		when an object annotated with @Valid fails validation 
			(e.g., a field doesn't meet constraints like @NotNull, @Size, etc.).
	Breakdown of the handleMethodArgumentNotValid Method:
Parameters:
	MethodArgumentNotValidException ex: 
		This is the exception that is thrown when validation on an object fails.
			HttpHeaders headers: 
				Headers from the HTTP request.
			HttpStatusCode status: 
				The HTTP status code that should be returned.
			WebRequest request: 
				The original web request that triggered the exception.
*/