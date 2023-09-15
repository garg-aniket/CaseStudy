package com.RewardPoints.Response;

public class ApiResponse {

	// A message describing the result of the API operation
	private String message;
	
	// A boolean flag indicating the success or failure of the operation
	private boolean success;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ApiResponse(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	public ApiResponse() {
		super();
	}
	
}
