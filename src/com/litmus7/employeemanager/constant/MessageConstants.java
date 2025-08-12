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
    public static final String FAILED_UPDATE_EMPLOYEE_MESSAGE = "Failed to update employee. No changes made.";
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

}
