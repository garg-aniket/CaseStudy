package com.casestudy.LoginAndRegistration.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * ApiResponse represents a standardized response format for API operations.
 * It includes a message and a success indicator.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
	
	// A message describing the result of the API operation
	private String message;
	
	// A boolean flag indicating the success or failure of the operation
	private boolean success;
	
}