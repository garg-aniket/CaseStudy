package com.partner.exception;

//Custom exception class for resource not found situations.
public class ResourceNotFoundException extends RuntimeException{
	
	String resourceName;
	String fieldName;
	int fieldValue;
	
	//Constructor for creating a resource not found exception with resource details.
	public ResourceNotFoundException(String resourceName, String fieldName, int fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	public ResourceNotFoundException() {
		super("Resource not found found on server !!");
	}
	public ResourceNotFoundException(String message) {
		super(message);
	}
}