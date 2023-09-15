package com.RewardPoints.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.RewardPoints.Exception.ResourceNotFoundException;
import com.RewardPoints.Response.ApiResponse;

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
}
