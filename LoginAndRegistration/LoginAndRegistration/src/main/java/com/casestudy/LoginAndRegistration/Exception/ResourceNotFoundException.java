package com.casestudy.LoginAndRegistration.Exception;

 // Custom exception class for resource not found situations.
 
public class ResourceNotFoundException extends RuntimeException{
	
	String resourceName; // Name of the resource (e.g., User, Product)
	String fieldName;	// Name of the field causing the exception (e.g., "Id", "Username")
	long fieldValue;	// Value of the field causing the exception (e.g., specific ID)
	
	//Constructor for creating a resource not found exception with resource details.
    
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	//Default constructor for creating a generic resource not found exception.
	public ResourceNotFoundException() {
		super("Resource not found found on server !!");
	}
	
	//Constructor for creating a general resource not found exception with a custom message.
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
