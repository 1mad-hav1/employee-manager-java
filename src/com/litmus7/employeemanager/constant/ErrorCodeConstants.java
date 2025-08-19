package com.litmus7.employeemanager.constant;

public class ErrorCodeConstants {
	public static final String UNEXPECTED_ERROR_CODE = "ERR-UNEXPECTED";
	
	//Database
	public static final String DB_CONNECTION_ERROR_CODE = "DB-100";
    public static final String DB_INSERT_ERROR_CODE = "DB-200";
    public static final String DB_FETCH_ERROR_CODE = "DB-201";
    public static final String DB_FETCH_ALL_ERROR_CODE = "DB-201";
    public static final String DB_DELETE_ERROR_CODE = "DB-202";
    public static final String DB_UPDATE_ERROR_CODE = "DB-203";
    
    //Validation
    public static final String VAL_EMPTY_EMPLOYEE_OBJECT_ERROR_CODE = "VAL-100";
    public static final String VAL_INVALID_EMPLOYEE_ID_ERROR_CODE = "VAL-101";
    public static final String VAL_EXISTING_EMPLOYEE_ID_ERROR_CODE = "VAL-102";
    public static final String VAL_INVALID_EMAIL_FORMAT_ERROR_CODE = "VAL-103";
    public static final String VAL_EXISTING_EMPLOYEE_EMAIL_ERROR_CODE = "VAL-104";
    public static final String VAL_INVALID_MOBILE_NUMBER_ERROR_CODE = "VAL-105";
    public static final String VAL_EXISTING_EMPLOYEE_MOBILE_NUMBER_ERROR_CODE = "VAL-106";
    public static final String VAL_INVALID_JOINING_DATE_ERROR_CODE = "VAL-107";
    public static final String VAL_EMPTY_FIRST_NAME_ERROR_CODE = "VAL-108";
    public static final String VAL_EMPTY_LAST_NAME_ERROR_CODE = "VAL-109";
    public static final String VAL_INVALID_ACTIVE_STATUS_ERROR_CODE = "VAL-110";
    
    //Employee related
    public static final String EMPLOYEE_NOT_FOUND_ERROR_CODE = "EMP-100";
    public static final String EMPLOYEE_LIST_EMPTY_ERROR_CODE = "EMP-101";
    public static final String EMPLOYEE_ALREADY_EXISTS_ERROR_CODE = "EMP-102";
}
