package com.litmus7.employeemanager.constant;

public class MessageConstants {
	
	//DAO Operations success messages
	public static final String SUCCESS_CREATE_EMPLOYEE_MESSAGE = "Employee details inserted successfully.\n";
    public static final String SUCCESS_DELETE_EMPLOYEE_BY_ID_MESSAGE = "Employee details deleted successfully.\n";
    public static final String SUCCESS_UPDATE_EMPLOYEE_MESSAGE =  "Employee details updated successfully.\n";
    public static final String SUCCESS_FETCH_ALL_EMPLOYEES_MESSAGE =  "Employee details fetched successfully.\n";
    public static final String SUCCESS_FETCH_EMPLOYEE_MESSAGE =  "Employee details fetched successfully.\n";
    
    //DAO Operations failure messages
    public static final String ERROR_CREATE_EMPLOYEE_MESSAGE = "Failed to insert Employee details.\n";
    public static final String ERROR_GET_ALL_EMPLOYEE_MESSAGE = "Failed to fetch Employee details.\n";
    public static final String ERROR_GET_EMPLOYEE_BY_ID_MESSAGE = "Failed to fetch Employee details.\n";
    public static final String ERROR_DELETE_EMPLOYEE_BY_ID_MESSAGE = "Failed to delete Employee details.\n";
    public static final String ERROR_UPDATE_EMPLOYEE_MESSAGE = "Failed to update Employee details.\n";
    
    //Exception messages
    public static final String ERROR_SQLEXCEPTION_MESSAGE = "Unable to save employee data, please try again later.\n";
    public static final String ERROR_IOEXCEPTION_FILE_MESSAGE = "An error occurred while processing the file. Please check the file and try again.\n";

    //Employee messages
    public static final String EMPTY_EMPLOYEES_MESSAGE = "No employee data found in the database.\n";
    public static final String EMPTY_EMPLOYEE_MESSAGE = "Enter valid employee details.\n";
    public static final String EMPTY_EMPLOYEE_LIST_MESSAGE = "Empty employee list passed.\n";
    
    //ID messages
    public static final String EMPTY_ID_MESSAGE = "Enter valid Employee ID.\n";
    public static final String INVALID_ID_MESSAGE = "Invalid Id entered.\n";
    public static final String EXISTING_ID_MESSAGE = "Id entered already exists.\n";
    public static final String ID_NOT_EXIST_MESSAGE = "Employee with the entered ID does not exist.\n";
    
    public static final String INVALID_FIRST_NAME_MESSAGE = "Invalid First Name entered.\n";
    
    public static final String INVALID_LAST_NAME_MESSAGE = "Invalid Last Name entered.\n";
    
    public static final String INVALID_EMAIL_MESSAGE = "Invalid Email entered.\n";
    
    public static final String INVALID_MOBILE_NUMBER_MESSAGE = "Invalid Mobile Number entered.\n";
    
    public static final String INVALID_JOINING_DATE_MESSAGE = "Invalid Joining Date entered (must be YYYY-MM-DD and not a future date).\n";
  	
    public static final String INVALID_ACTIVE_STATUS_MESSAGE = "Invalid Active Status entered (must be true or false).\n";
    
    //File messages				
    public static final String FILE_NOT_FOUND_MESSAGE = "File does not exist or is not a file.\n";
    public static final String INVALID_FILE_FORMAT_MESSAGE = "Invalid file format. Please specify a file with the correct extension.\n ";
    public static final String EMPTY_FILE_NAME_MESSAGE = "File name must not be empty.\n";
    public static final String EMPTY_FILE_NAME_OR_EXTENSION_MESSAGE = "File name or expected extension cannot be null.\n";
    
    //File Operation Success messages
    public static final String SUCCESS_FETCH_EMPLOYEES_FILE_MESSAGE = "Successfully retrieved Employee details.\n";
    public static final String SUCCESS_WRITE_EMPLOYEES_CSV_MESSAGE = "Successfully written Employee details to csv file.\n";
    public static final String SUCCESS_WRITE_EMPLOYEE_CSV_MESSAGE = "Successfully written Employee details to csv file.\n";

}
