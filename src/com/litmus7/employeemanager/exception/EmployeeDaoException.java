package com.litmus7.employeemanager.exception;

public class EmployeeDaoException extends Exception {

	private String errorCode;

	public EmployeeDaoException(String message, Throwable cause, String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
