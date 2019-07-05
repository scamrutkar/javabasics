package com.sagar.javacase.exception;

class InvalidAgeException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public InvalidAgeException(String errorCode, String errorMsg) {
		super(errorMsg);
	}
}

public class CustomException {

	public static void main(String[] args) throws InvalidAgeException {
		
		int age = 17;
		
		if(age < 18)
			throw new InvalidAgeException("411", "Age is invalid");
	}

}
