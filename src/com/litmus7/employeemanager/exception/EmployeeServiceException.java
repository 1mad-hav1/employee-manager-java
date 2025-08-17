package com.litmus7.employeemanager.exception;

public class EmployeeServiceException extends Exception {

	private String errorCode;

	public EmployeeServiceException(String message, Throwable cause, String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public EmployeeServiceException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}