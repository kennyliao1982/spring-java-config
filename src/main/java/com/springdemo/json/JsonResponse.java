package com.springdemo.json;

/**
 * json response base
 * 
 * @author kenny
 *
 */
public class JsonResponse {

	private boolean success = true;
	private String message = "ok";

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
