package com.idfcbank.savingaccount.advise;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.idfcbank.savingaccount.exceptions.AccountCloseException;
import com.idfcbank.savingaccount.exceptions.AccountMappingException;
import com.idfcbank.savingaccount.exceptions.BalanceNotSufficientException;
import com.idfcbank.savingaccount.exceptions.IdNotFoundException;

/*
* @ControllerAdvice Annotation: This annotation indicates that the class provides global advice for all controllers within the application.*/
@ControllerAdvice
public class AppAdvice {

	/*This method is a helper function that creates a Map object containing the exception message and HTTP status code. It's used by the other exception handler methods to construct the response.
	 * */
	public ResponseEntity<?> createMessage(Exception e, HttpStatus status) {
		Map<String, String> map = new HashMap<>();
		map.put("message", e.getMessage());
		map.put("HttpStatus", status + "");

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
	}

	//Handles cases where an ID is not found.
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> idNotFoundExceptionHandler(IdNotFoundException e) {
		return createMessage(e, HttpStatus.NOT_FOUND);
	}

	//Handles cases where the balance is insufficient.
	@ExceptionHandler(BalanceNotSufficientException.class)
	public ResponseEntity<?> balanceNotSufficientExceptionHandler(BalanceNotSufficientException e) {
		return createMessage(e, HttpStatus.NOT_FOUND);
	}

	//Handles cases where there's an issue with account mapping.
	@ExceptionHandler(AccountMappingException.class)
	public ResponseEntity<?> accountMappingExceptionHandler(AccountMappingException e) {
		return createMessage(e, HttpStatus.NOT_FOUND);
	}

	//Handles cases where an account is closed.
	@ExceptionHandler(AccountCloseException.class)
	public ResponseEntity<?> accountCloseExceptionHandler(AccountCloseException e) {
		return createMessage(e, HttpStatus.NOT_FOUND);
	}

	/*
	 * helper method to print a not supported method exception 
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String, String>> handleException(HttpRequestMethodNotSupportedException e)
			throws IOException {

		String provided = e.getMethod();
		List<String> supported = List.of(e.getSupportedMethods());

		String error = provided + " is not one of the supported Http Methods (" + String.join(",", supported) + ")";

		Map<String, String> errorResponse = Map.of("error", error, "message", e.getLocalizedMessage(), "status",
				HttpStatus.METHOD_NOT_ALLOWED.toString());

		return new ResponseEntity<Map<String, String>>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);

	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<Map<String, String>> handleMediaException(HttpMediaTypeNotSupportedException e)
			throws IOException {

		MediaType provided = e.getContentType();
		List<MediaType> supported = e.getSupportedMediaTypes();

		String error = provided + " is not one of the supported Http Methods (" + supported + ")";

		Map<String, String> errorResponse = Map.of("error", error, "message", e.getMessage(), "status",
				HttpStatus.METHOD_NOT_ALLOWED.toString());

		return new ResponseEntity<Map<String, String>>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);

	}
}

/*
 * This AppAdvice class 
	centralized exception handling for your Spring Boot application
	appears to be related to a banking service dealing with savings accounts. 
	The class is annotated with @ControllerAdvice, 
		making it a global handler for all exceptions thrown in your controllers. 
		Each method in the class handles a specific exception, 
			providing a consistent and standardized way to respond to errors across the application.

Key Components
1. @ControllerAdvice:
	@ControllerAdvice 
	Spring annotation 
		define global exception handling for all controllers. 
		This means that any controller in the application can throw exceptions, and they will be handled by this class.
2. createMessage(Exception e, HttpStatus status):
	This is a helper method 
		creates a standardized response body for exceptions. 
		It generates a Map<String, String> containing:
	message: 
		The exception's message, retrieved from e.getMessage().
	HttpStatus: 
		The HTTP status code as a string.
	This method returns a ResponseEntity with a body containing the map and an HTTP status code of NOT_FOUND (404).
	While HttpStatus.NOT_FOUND is used as the default status, the status passed in can be customized.
	Exception Handlers
	Each exception handler method catches a specific type of exception and uses the createMessage helper method to generate a response.

3. idNotFoundExceptionHandler(IdNotFoundException e):
	Handles cases where an IdNotFoundException is thrown, which likely occurs when a requested ID (such as for an account) is not found in the system.
	It returns a 404 NOT_FOUND response with the exception message.

4. balanceNotSufficientExceptionHandler(BalanceNotSufficientException e):
	Handles cases where the balance in an account is insufficient (likely during withdrawal operations).
	Returns a 404 NOT_FOUND response with the relevant exception message.

5. accountMappingExceptionHandler(AccountMappingException e):
	Handles errors related to account mapping, perhaps when trying to link accounts.
	It also returns a 404 NOT_FOUND response.

6. accountCloseExceptionHandler(AccountCloseException e):
	Handles cases where an account is closed, potentially in situations where transactions are attempted on a closed account.
	Returns a 404 NOT_FOUND response.
	Handling Built-in HTTP Errors
	The class also provides handlers for common HTTP errors such as unsupported request methods or unsupported media types.

7. handleException(HttpRequestMethodNotSupportedException e):
	Handles HTTP 405 (Method Not Allowed): This exception occurs when the client makes a request using an HTTP method (like POST or PUT) that is not allowed by the server.
	The method extracts the provided HTTP method and the supported methods from the exception and constructs an error message indicating that the requested method is not supported.
	A ResponseEntity is returned with an HTTP status of 405 (METHOD_NOT_ALLOWED) and a response body containing the error message.

8. handleMediaException(HttpMediaTypeNotSupportedException e):
	Handles HTTP 415 (Unsupported Media Type): This exception is thrown when a client sends a request with an unsupported media type (for example, sending application/xml when the server only accepts application/json).
	The method extracts the provided media type and the supported media types from the exception and constructs an error message.
	A ResponseEntity is returned with an HTTP status of 415 (UNSUPPORTED_MEDIA_TYPE) and a response body containing the error message.
Example Error Responses
ID Not Found Exception: If an IdNotFoundException is thrown, the response might look like:
{
    "message": "Account with ID not found",
    "HttpStatus": "404"
}
HTTP 405 Method Not Allowed: If a client tries to use an unsupported HTTP method, the response could be:


{
    "error": "POST is not one of the supported Http Methods (GET, PUT)",
    "message": "Request method 'POST' not supported",
    "status": "405 METHOD_NOT_ALLOWED"
}
HTTP 415 Unsupported Media Type: If a client sends a request with an unsupported media type, the response might be:

{
    "error": "application/xml is not one of the supported Http Methods ([application/json])",
    "message": "Content type 'application/xml' not supported",
    "status": "415 METHOD_NOT_ALLOWED"
}
Key Benefits of This Class
Centralized Exception Handling:

All exceptions are handled in one place, which makes the code more maintainable and ensures that all errors are processed consistently.
Clear and Consistent Error Responses:

Each handler method uses the createMessage helper method to produce responses in a consistent format. This makes it easier for API consumers to parse and understand error messages.
Granular Control:

By defining specific exception handlers for each type of error (e.g., IdNotFoundException, BalanceNotSufficientException), you have granular control over the error handling process and can return more meaningful responses to the user.
Handles Common HTTP Errors:

The class provides handlers for standard HTTP errors like 405 Method Not Allowed and 415 Unsupported Media Type, ensuring that clients receive appropriate responses for common mistakes.
Conclusion
This AppAdvice class serves as a robust global exception handler for a Spring Boot application, handling both custom exceptions related to banking operations and standard HTTP errors. By centralizing error handling, the class simplifies error management, improves code maintainability, and provides consistent, informative error messages to API consumers.
 */