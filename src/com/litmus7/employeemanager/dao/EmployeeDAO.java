package com.litmus7.employeemanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.employeemanager.constant.SqlConstants;
import com.litmus7.employeemanager.constant.MessageConstants;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.exception.EmployeeDaoException;
import com.litmus7.employeemanager.util.DatabaseUtil;

public class EmployeeDAO {

	public boolean createEmployee(Employee employee) throws EmployeeDaoException {
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement insertStatement = connection.prepareStatement(SqlConstants.INSERT_EMPLOYEE_QUERY)) {

			insertStatement.setInt(1, employee.getId());
			insertStatement.setString(2, employee.getFirstName());
			insertStatement.setString(3, employee.getLastName());
			insertStatement.setString(4, employee.getMobileNumber());
			insertStatement.setString(5, employee.getEmail());
			insertStatement.setDate(6, Date.valueOf(employee.getJoiningDate()));
			insertStatement.setBoolean(7, employee.getStatus());

			int rowsInserted = insertStatement.executeUpdate();

			return rowsInserted > 0;
		} catch (SQLException e) {
			throw new EmployeeDaoException(MessageConstants.ERROR_DAO_CREATE_EMPLOYEE_MESSAGE, e);
		}
	}

	public List<Employee> getAllEmployees() throws EmployeeDaoException {
		List<Employee> employees = new ArrayList<>();
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement selectStatement = connection.prepareStatement(SqlConstants.SELECT_EMPLOYEES_QUERY)) {

			ResultSet employeeRs = selectStatement.executeQuery();

			while (employeeRs.next()) {
				Employee employee = new Employee(employeeRs.getInt(SqlConstants.ID_COLUMN_NAME),
						employeeRs.getString(SqlConstants.FIRST_NAME_COLUMN_NAME),
						employeeRs.getString(SqlConstants.LAST_NAME_COLUMN_NAME),
						employeeRs.getString(SqlConstants.EMAIL_COLUMN_NAME),
						employeeRs.getString(SqlConstants.MOBILE_NUMBER_COLUMN_NAME),
						employeeRs.getDate(SqlConstants.JOINING_DATE_COLUMN_NAME).toLocalDate(),
						employeeRs.getBoolean(SqlConstants.ACTIVE_STATUS_COLUMN_NAME));
				employees.add(employee);
			}

			return employees;
		} catch (SQLException e) {
			throw new EmployeeDaoException(MessageConstants.ERROR_DAO_GET_ALL_EMPLOYEE_MESSAGE, e);
		}
	}

	public Employee getEmployeeById(int id) throws EmployeeDaoException {
		Employee employee = null;
		try (Connection connection = DatabaseUtil.getConnection();
			PreparedStatement selectStatement = connection.prepareStatement(SqlConstants.SELECT_EMPLOYEE_BY_ID_QUERY)) {
			
			selectStatement.setInt(1, id);

			ResultSet employeeRs = selectStatement.executeQuery();

			if (employeeRs.next()) {
				employee = new Employee(employeeRs.getInt(SqlConstants.ID_COLUMN_NAME),
						employeeRs.getString(SqlConstants.FIRST_NAME_COLUMN_NAME),
						employeeRs.getString(SqlConstants.LAST_NAME_COLUMN_NAME),
						employeeRs.getString(SqlConstants.EMAIL_COLUMN_NAME),
						employeeRs.getString(SqlConstants.MOBILE_NUMBER_COLUMN_NAME),
						employeeRs.getDate(SqlConstants.JOINING_DATE_COLUMN_NAME).toLocalDate(),
						employeeRs.getBoolean(SqlConstants.ACTIVE_STATUS_COLUMN_NAME));
			}
			return employee;
		} catch (SQLException e) {
			throw new EmployeeDaoException(MessageConstants.ERROR_DAO_GET_EMPLOYEE_BY_ID_MESSAGE, e);
		}
	}

	public boolean deleteEmployee(int id) throws EmployeeDaoException {

		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement deleteStatement = connection.prepareStatement(SqlConstants.DELETE_EMPLOYEE_BY_ID_QUERY)) {
			deleteStatement.setInt(1, id);

			int rowsDeleted = deleteStatement.executeUpdate();

			return rowsDeleted > 0;
		}  catch (SQLException e) {
			throw new EmployeeDaoException(MessageConstants.ERROR_DAO_DELETE_EMPLOYEE_MESSAGE, e);
		}
	}

	public boolean updateEmployee(Employee employee) throws EmployeeDaoException {
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement updateStatement = connection.prepareStatement(SqlConstants.UPDATE_EMPLOYEE_QUERY)) {

			updateStatement.setString(1, employee.getFirstName());
			updateStatement.setString(2, employee.getLastName());
			updateStatement.setString(3, employee.getMobileNumber());
			updateStatement.setString(4, employee.getEmail());
			updateStatement.setDate(5, Date.valueOf(employee.getJoiningDate()));
			updateStatement.setBoolean(6, employee.getStatus());
			updateStatement.setInt(7, employee.getId());

			return updateStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new EmployeeDaoException(MessageConstants.ERROR_DAO_UPDATE_EMPLOYEE_MESSAGE, e);
		}
	}
}
