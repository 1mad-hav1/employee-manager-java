package com.litmus7.employeemanager.dto;

public class Response<T> {
	
	private boolean success;
	private T data;
	private String message;
	
	public Response(boolean success, T data, String message) {
		this.success = success;
		this.data = data;
		this.message = message;
	}

	public Response(boolean success, String message) {
	    this(success, null, message);
	}

	
	public Response() {
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean status) {
		this.success = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
