package com.litmus7.employeemanager.constant;

public class SqlConstants {
	
	public static final String ID_COLUMN_NAME = "id";
    public static final String FIRST_NAME_COLUMN_NAME = "first_name";
    public static final String LAST_NAME_COLUMN_NAME = "last_name";
    public static final String MOBILE_NUMBER_COLUMN_NAME = "mobile_number";
    public static final String EMAIL_COLUMN_NAME = "email";
    public static final String JOINING_DATE_COLUMN_NAME = "joining_date";
    public static final String ACTIVE_STATUS_COLUMN_NAME = "active_status";

    public static final String INSERT_EMPLOYEE_QUERY = "INSERT INTO employee(id, first_name, last_name, mobile_number, email, joining_date, active_status) VALUES (?,?,?,?,?,?,?);";
    public static final String SELECT_EMPLOYEES_QUERY  = "SELECT id, first_name, last_name, mobile_number, email, joining_date, active_status FROM employee;";
    public static final String SELECT_EMPLOYEE_BY_ID_QUERY = "SELECT id, first_name, last_name, mobile_number, email, joining_date, active_status FROM employee WHERE id = ?;";
    public static final String DELETE_EMPLOYEE_BY_ID_QUERY = "DELETE FROM employee WHERE id = ?;";
    public static final String UPDATE_EMPLOYEE_QUERY = "UPDATE employee SET first_name = ?, last_name = ?, mobile_number = ?, email = ?, joining_date = ?, active_status = ? WHERE id = ?;";

}
