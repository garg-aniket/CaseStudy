package com.partner.Response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponse {
	
	// A message describing the result of the API operation
	private String message;
	
	// A boolean flag indicating the success or failure of the operation
	private boolean success;
	
}
