package com.capstone.accountManagementSystem.exception;

public class CustomException extends RuntimeException {
	
	public CustomException(String msg) {
		super(msg);
	}
	
	@Override
	public String toString() {
		return super.getMessage();
	}
}
