package com.litmus7.employeemanager.constant;

public class MessageConstants {
	
	//DAO Operations success messages
	public static final String SUCCESS_CREATE_EMPLOYEE_MESSAGE = "Employee details inserted successfully.";
    public static final String SUCCESS_DELETE_EMPLOYEE_BY_ID_MESSAGE = "Employee details deleted successfully.";
    public static final String SUCCESS_UPDATE_EMPLOYEE_MESSAGE =  "Employee details updated successfully.";
    public static final String SUCCESS_FETCH_ALL_EMPLOYEES_MESSAGE =  "Employee details fetched successfully.";
    public static final String SUCCESS_FETCH_EMPLOYEE_MESSAGE =  "Employee details fetched successfully.";
    
    //DAO Operations failure messages
    public static final String ERROR_DAO_CREATE_EMPLOYEE_MESSAGE = "Database error occurred while creating employee record.";
    public static final String ERROR_DAO_GET_ALL_EMPLOYEE_MESSAGE = "Database error occurred while retrieving all employee records.";
    public static final String ERROR_DAO_GET_EMPLOYEE_BY_ID_MESSAGE = "Database error occurred while retrieving employee record by ID.";
    public static final String ERROR_DAO_UPDATE_EMPLOYEE_MESSAGE = "Database error occurred while updating employee record.";
    public static final String ERROR_DAO_DELETE_EMPLOYEE_MESSAGE = "Database error occurred while deleting employee record.";
    
    //Service Layer failure messages
    public static final String ERROR_SERVICE_CREATE_EMPLOYEE_MESSAGE = "Unable to create employee at the moment. Please try again.";
    public static final String ERROR_SERVICE_FETCH_ALL_EMPLOYEES_MESSAGE = "Unable to retrieve employees at the moment. Please try again.";
    public static final String ERROR_SERVICE_FETCH_EMPLOYEE_BY_ID_MESSAGE = "Unable to retrieve employee details at the moment. Please try again.";
    public static final String ERROR_SERVICE_UPDATE_EMPLOYEE_MESSAGE = "Unable to update employee details at the moment. Please try again.";
    public static final String ERROR_SERVICE_DELETE_EMPLOYEE_MESSAGE = "Unable to delete employee at the moment. Please try again.";
    
    //Control Layer failure messages
    public static final String FAILED_CREATE_EMPLOYEE_MESSAGE = "Failed to create employee. No rows were inserted.";
    public static final String FAILED_FETCH_EMPLOYEE_BY_ID_MESSAGE = "Failed to fetch employee.";
    public static final String FAILED_FETCH_ALL_EMPLOYEES_MESSAGE = "Failed to fetch employees.";
    public static final String FAILED_UPDATE_EMPLOYEE_MESSAGE = "Failed to update employee.";
    public static final String FAILED_DELETE_EMPLOYEE_MESSAGE = "Failed to delete employee.";

    //Controller Prefix Messages
    public static final String ERROR_CREATE_EMPLOYEE_PREFIX_MESSAGE = "Error creating employee: ";
    public static final String ERROR_UPDATE_EMPLOYEE_PREFIX_MESSAGE = "Error updating employee: ";
    public static final String ERROR_DELETE_EMPLOYEE_PREFIX_MESSAGE = "Error deleting employee: ";
    public static final String ERROR_FETCH_EMPLOYEE_BY_ID_PREFIX_MESSAGE = "Error fetching employee: ";
    public static final String ERROR_FETCH_ALL_EMPLOYEE_PREFIX_MESSAGE = "Error fetching employee: ";
    
    //Exception messages
    public static final String ERROR_SQLEXCEPTION_MESSAGE = "Unable to save employee data, please try again later.";
    public static final String ERROR_IOEXCEPTION_FILE_MESSAGE = "An error occurred while processing the file. Please check the file and try again.";

    //Employee messages
    public static final String EMPTY_EMPLOYEES_MESSAGE = "No employee data found in the database.";
    public static final String EMPTY_EMPLOYEE_MESSAGE = "Enter valid employee details.";
    public static final String EMPTY_EMPLOYEE_LIST_MESSAGE = "Empty employee list passed.";
    
    //ID messages
    public static final String EMPTY_ID_MESSAGE = "Enter a valid Employee ID.";
    public static final String INVALID_ID_MESSAGE = "Invalid Id entered.";
    public static final String EXISTING_ID_MESSAGE = "Id entered already exists.";
    public static final String ID_NOT_EXIST_MESSAGE = "Employee with the entered ID does not exist.";
    
    public static final String INVALID_FIRST_NAME_MESSAGE = "Invalid First Name entered.";
    
    public static final String INVALID_LAST_NAME_MESSAGE = "Invalid Last Name entered.";
    
    public static final String INVALID_EMAIL_MESSAGE = "Invalid Email entered.";
    public static final String EXISTING_EMAIL_MESSAGE = "Email entered already exists.";
    
    public static final String INVALID_MOBILE_NUMBER_MESSAGE = "Invalid Mobile Number entered.";
    public static final String EXISTING_MOBILE_NUMBER_MESSAGE = "Mobile Number entered already exists.";
    
    public static final String INVALID_JOINING_DATE_MESSAGE = "Invalid Joining Date entered (must be YYYY-MM-DD and not a future date).";
  	
    public static final String INVALID_ACTIVE_STATUS_MESSAGE = "Invalid Active Status entered (must be true or false).";
    
    //File messages				
    public static final String FILE_NOT_FOUND_MESSAGE = "File does not exist or is not a file.";
    public static final String INVALID_FILE_FORMAT_MESSAGE = "Invalid file format. Please specify a file with the correct extension. ";
    public static final String EMPTY_FILE_NAME_MESSAGE = "File name must not be empty.";
    public static final String EMPTY_FILE_NAME_OR_EXTENSION_MESSAGE = "File name or expected extension cannot be null.";
    
    //File Operation Success messages
    public static final String SUCCESS_FETCH_EMPLOYEES_FILE_MESSAGE = "Successfully retrieved Employee details.";
    public static final String SUCCESS_WRITE_EMPLOYEES_CSV_MESSAGE = "Successfully written Employee details to csv file.";
    public static final String SUCCESS_WRITE_EMPLOYEE_CSV_MESSAGE = "Successfully written Employee details to csv file.";
    
    //Database Error Messages
    public static final String ERROR_DATABASE_CONNECTION_MESSAGE = "Failed to establish connection with database";
    
    //Error codes messages
    
    public static final String UNEXPECTED_ERROR_MESSAGE = "An Unexpected error occured. Please try again.";
    //DB errors
    
    public static final String DB_CONNECTION_ERROR_MESSAGE = "Unable to connect to the server. Please try again later.";
    
    public static final String DB_INSERT_ERROR_MESSAGE = "Failed to save employee data. Please try again.";
    
    public static final String DB_FETCH_ERROR_MESSAGE = "Failed to retrieve employee details. Please try again.";
    
    public static final String DB_FETCH_ALL_ERROR_MESSAGE = "Failed to retrieve employee details. Please try again.";
    
    public static final String DB_DELETE_ERROR_MESSAGE = "Failed to remove employee record. Please try again.";
    
    public static final String DB_UPDATE_ERROR_MESSAGE = "Failed to update employee details. Please try again.";

    
    //Validation / Input errors
    
    public static final String VAL_EMPTY_EMPLOYEE_OBJECT_ERROR_MESSAGE = "Employee object cannot be null.";

    public static final String VAL_INVALID_EMPLOYEE_ID_ERROR_MESSAGE = "Employee ID is invalid or negative.";
    
    public static final String VAL_EXISTING_EMPLOYEE_ID_ERROR_MESSAGE = "Employee ID already exists.";
    
    public static final String VAL_INVALID_EMAIL_FORMAT_ERROR_MESSAGE = "Employee email format is invalid.";
    
    public static final String VAL_EXISTING_EMPLOYEE_EMAIL_ERROR_MESSAGE = "Employee email already exists.";
    
    public static final String VAL_INVALID_MOBILE_NUMBER_ERROR_MESSAGE = "Employee mobile number format is invalid.";
    
    public static final String VAL_EXISTING_EMPLOYEE_MOBILE_NUMBER_ERROR_MESSAGE = "Employee mobile number already exists.";

    public static final String VAL_INVALID_JOINING_DATE_ERROR_MESSAGE = "Employee joining date entered is invalid.";
    
    public static final String VAL_EMPTY_FIRST_NAME_ERROR_MESSAGE = "Employee first name cannot be empty.";
    
    public static final String VAL_EMPTY_LAST_NAME_ERROR_MESSAGE = "Employee last name cannot be empty.";
    
    public static final String VAL_INVALID_ACTIVE_STATUS_ERROR_MESSAGE = "Employee active status is invalid.";
    
    //Employee related error codes
    
    public static final String EMPLOYEE_NOT_FOUND_ERROR_MESSAGE = "Employee does not exist.";
    
    public static final String EMPLOYEE_LIST_EMPTY_ERROR_MESSAGE = "No employee data found in the system.";
    
    public static final String EMPLOYEE_ALREADY_EXISTS_ERROR_MESSAGE = "Employee already exists.";

}
