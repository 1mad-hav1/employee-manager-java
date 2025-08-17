package com.litmus7.employeemanager.exception;

public class DatabaseException  extends Exception{
	
	private String errorCode;
	
	public DatabaseException (String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }
	
	public DatabaseException(String message, String errorCode) {
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
