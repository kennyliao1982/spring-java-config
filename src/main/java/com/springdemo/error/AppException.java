package com.springdemo.error;

public class AppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -852244215876397781L;

	private String errorCode;
	private String errorMessage;
	
	public AppException(String errorCode, String errorMessage){
	
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	
}
