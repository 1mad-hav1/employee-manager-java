package com.litmus7.employeemanager.util;

import java.util.HashMap;
import java.util.Map;

import com.litmus7.employeemanager.constant.ErrorCodeConstants;
import com.litmus7.employeemanager.constant.MessageConstants;

public class ErrorCodeUtil {
	private static final Map<String, String> ERROR_MESSAGES = new HashMap<>();

	static {
		ERROR_MESSAGES.put(ErrorCodeConstants.UNEXPECTED_ERROR_CODE, MessageConstants.UNEXPECTED_ERROR_MESSAGE);
		
		// Database errors
		ERROR_MESSAGES.put(ErrorCodeConstants.DB_CONNECTION_ERROR_CODE, MessageConstants.DB_CONNECTION_ERROR_MESSAGE);
		ERROR_MESSAGES.put(ErrorCodeConstants.DB_INSERT_ERROR_CODE, MessageConstants.DB_INSERT_ERROR_MESSAGE);
		ERROR_MESSAGES.put(ErrorCodeConstants.DB_FETCH_ERROR_CODE, MessageConstants.DB_FETCH_ERROR_MESSAGE);
		ERROR_MESSAGES.put(ErrorCodeConstants.DB_FETCH_ALL_ERROR_CODE, MessageConstants.DB_FETCH_ALL_ERROR_MESSAGE);
		ERROR_MESSAGES.put(ErrorCodeConstants.DB_DELETE_ERROR_CODE, MessageConstants.DB_DELETE_ERROR_MESSAGE);
		ERROR_MESSAGES.put(ErrorCodeConstants.DB_UPDATE_ERROR_CODE, MessageConstants.DB_UPDATE_ERROR_MESSAGE);

		// Validation / Input errors
		ERROR_MESSAGES.put(ErrorCodeConstants.VAL_EMPTY_EMPLOYEE_OBJECT_ERROR_CODE,
				MessageConstants.VAL_EMPTY_EMPLOYEE_OBJECT_ERROR_MESSAGE);
		ERROR_MESSAGES.put(ErrorCodeConstants.VAL_INVALID_EMPLOYEE_ID_ERROR_CODE,
				MessageConstants.VAL_INVALID_EMPLOYEE_ID_ERROR_MESSAGE);
		ERROR_MESSAGES.put(ErrorCodeConstants.VAL_EXISTING_EMPLOYEE_ID_ERROR_CODE,
				MessageConstants.VAL_EXISTING_EMPLOYEE_ID_ERROR_MESSAGE);
		ERROR_MESSAGES.put(ErrorCodeConstants.VAL_INVALID_EMAIL_FORMAT_ERROR_CODE,
				MessageConstants.VAL_INVALID_EMAIL_FORMAT_ERROR_MESSAGE);
		ERROR_MESSAGES.put(ErrorCodeConstants.VAL_EXISTING_EMPLOYEE_EMAIL_ERROR_CODE,
				MessageConstants.VAL_EXISTING_EMPLOYEE_EMAIL_ERROR_MESSAGE);
		ERROR_MESSAGES.put(ErrorCodeConstants.VAL_INVALID_MOBILE_NUMBER_ERROR_CODE,
				MessageConstants.VAL_INVALID_MOBILE_NUMBER_ERROR_MESSAGE);
		ERROR_MESSAGES.put(ErrorCodeConstants.VAL_EXISTING_EMPLOYEE_MOBILE_NUMBER_ERROR_CODE,
				MessageConstants.VAL_EXISTING_EMPLOYEE_MOBILE_NUMBER_ERROR_MESSAGE);
		ERROR_MESSAGES.put(ErrorCodeConstants.VAL_INVALID_JOINING_DATE_ERROR_CODE,
				MessageConstants.VAL_INVALID_JOINING_DATE_ERROR_MESSAGE);
		ERROR_MESSAGES.put(ErrorCodeConstants.VAL_EMPTY_FIRST_NAME_ERROR_CODE,
				MessageConstants.VAL_EMPTY_FIRST_NAME_ERROR_MESSAGE);
		ERROR_MESSAGES.put(ErrorCodeConstants.VAL_EMPTY_LAST_NAME_ERROR_CODE,
				MessageConstants.VAL_EMPTY_LAST_NAME_ERROR_MESSAGE);
		ERROR_MESSAGES.put(ErrorCodeConstants.VAL_INVALID_ACTIVE_STATUS_ERROR_CODE,
				MessageConstants.VAL_INVALID_ACTIVE_STATUS_ERROR_MESSAGE);

		// Employee related errors
		ERROR_MESSAGES.put(ErrorCodeConstants.EMPLOYEE_NOT_FOUND_ERROR_CODE,
				MessageConstants.EMPLOYEE_NOT_FOUND_ERROR_MESSAGE);
		ERROR_MESSAGES.put(ErrorCodeConstants.EMPLOYEE_LIST_EMPTY_ERROR_CODE,
				MessageConstants.EMPLOYEE_LIST_EMPTY_ERROR_MESSAGE);
		ERROR_MESSAGES.put(ErrorCodeConstants.EMPLOYEE_ALREADY_EXISTS_ERROR_CODE,
				MessageConstants.EMPLOYEE_ALREADY_EXISTS_ERROR_MESSAGE);

	}

	public static String getErrorMessage(String errorCode) {
		return ERROR_MESSAGES.getOrDefault(errorCode, "An unexpected error occurred.");
	}
}
