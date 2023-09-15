package com.casestudy.LoginAndRegistration.Exception;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.casestudy.LoginAndRegistration.Response.ApiResponse;

//GlobalExceptionHandler is a class responsible for handling exceptions globally in the application.

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	/**
     * Handle exceptions of type ResourceNotFoundException.
     * 
     * @param ex The exception of type ResourceNotFoundException.
     * @return A response entity containing an error message and HTTP status code 404 (Not Found).
     */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionalHandler(ResourceNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	/**
     * Handle exceptions of type MethodArgumentNotValidException.
     * 
     * @param ex The exception of type MethodArgumentNotValidException.
     * @return A response entity containing a map of field errors and HTTP status code 400 (Bad Request).
     */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
}
