package com.litmus7.employeemanager.exception;

public class EmployeeValidationException extends Exception {

	private String errorCode;

	public EmployeeValidationException(String message) {
		super(message);
//		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
