package com.main.Blogging.Payloads;

public class ApiResponse {

	
	private String message;
	private boolean succces;
	
	//Argument Constructor
	public ApiResponse(String message, boolean succces) {
		super();
		this.message = message;
		this.succces = succces;
	}
	
	//NO Argument Constructor
	public ApiResponse() {
		super();
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccces() {
		return succces;
	}
	public void setSuccces(boolean succces) {
		this.succces = succces;
	}
	
}
