package com.litmus7.employeemanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.litmus7.employeemanager.constant.SqlConstants;
import com.litmus7.employeemanager.constant.MessageConstants;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.exception.DatabaseException;
import com.litmus7.employeemanager.exception.EmployeeDaoException;
import com.litmus7.employeemanager.util.DatabaseUtil;

public class EmployeeDAO {

	private static final Logger logger = LogManager.getLogger(EmployeeDAO.class);

	public boolean createEmployee(Employee employee) throws EmployeeDaoException {
		logger.trace("Entering createEmployee() for creating employee with id: {} - In DAO layer", employee.getId());
		boolean success = false;
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement insertStatement = connection.prepareStatement(SqlConstants.INSERT_EMPLOYEE_QUERY)) {

			insertStatement.setInt(1, employee.getId());
			insertStatement.setString(2, employee.getFirstName());
			insertStatement.setString(3, employee.getLastName());
			insertStatement.setString(4, employee.getMobileNumber());
			insertStatement.setString(5, employee.getEmail());
			insertStatement.setDate(6, Date.valueOf(employee.getJoiningDate()));
			insertStatement.setBoolean(7, employee.getStatus());

			success = insertStatement.executeUpdate() > 0;

		} catch (DatabaseException databaseException) {
			logger.error("Error creating employee with id {}: ", employee.getId(),
					MessageConstants.ERROR_DATABASE_CONNECTION_MESSAGE, databaseException);
			throw new EmployeeDaoException(MessageConstants.ERROR_DATABASE_CONNECTION_MESSAGE, databaseException);
		} catch (SQLException sqlException) {
			logger.error("Error creating employee with id {}: {}", employee.getId(),
					MessageConstants.ERROR_DAO_CREATE_EMPLOYEE_MESSAGE, sqlException);
			throw new EmployeeDaoException(MessageConstants.ERROR_DAO_CREATE_EMPLOYEE_MESSAGE, sqlException);
		}
		logger.trace("Exiting from createEmployee() for employee {} with success = {}", employee.getId(), success);
		return success;
	}

	public List<Employee> getAllEmployees() throws EmployeeDaoException {
		logger.trace("Entering getAllEmployees() - In DAO layer");
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

		} catch (DatabaseException databaseException) {
			logger.error("Error fetching all employees: {}", MessageConstants.ERROR_DATABASE_CONNECTION_MESSAGE, databaseException);
			throw new EmployeeDaoException(MessageConstants.ERROR_DATABASE_CONNECTION_MESSAGE, databaseException);
		} catch (SQLException sqlException) {
			logger.error("Error fetching employees: ", sqlException);
			throw new EmployeeDaoException(MessageConstants.ERROR_DAO_GET_ALL_EMPLOYEE_MESSAGE, sqlException);
		}

		logger.trace("Exiting getAllEmployees() - In DAO layer");
		return employees;
	}

	public Employee getEmployeeById(int id) throws EmployeeDaoException {
		logger.trace("Entering getEmployeeById({}) - In DAO layer", id);
		Employee employee = null;
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement selectStatement = connection
						.prepareStatement(SqlConstants.SELECT_EMPLOYEE_BY_ID_QUERY)) {

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
		} catch (DatabaseException databaseException) {
			logger.error("Error fetching employee with id {}: {}", id,
					MessageConstants.ERROR_DATABASE_CONNECTION_MESSAGE, databaseException);
			throw new EmployeeDaoException(MessageConstants.ERROR_DATABASE_CONNECTION_MESSAGE, databaseException);
		} catch (SQLException sqlException) {
			logger.error("Error fetching employee with id {}:", id, sqlException);
			throw new EmployeeDaoException(MessageConstants.ERROR_DAO_GET_EMPLOYEE_BY_ID_MESSAGE, sqlException);
		}

		logger.trace("Exiting getEmployeeById({}) - In DAO layer", id);
		return employee;
	}

	public boolean deleteEmployee(int id) throws EmployeeDaoException {
		logger.trace("Entering deleteEmployee({}) - In DAO layer", id);
		boolean success = false;
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement deleteStatement = connection
						.prepareStatement(SqlConstants.DELETE_EMPLOYEE_BY_ID_QUERY)) {
			deleteStatement.setInt(1, id);

			success = deleteStatement.executeUpdate() > 0;

		} catch (DatabaseException databaseException) {
			logger.error("Error deleting employee with id {}: {}", id,
					MessageConstants.ERROR_DATABASE_CONNECTION_MESSAGE, databaseException);
			throw new EmployeeDaoException(MessageConstants.ERROR_DATABASE_CONNECTION_MESSAGE, databaseException);
		} catch (SQLException sqlException) {
			logger.error("Error deleting employee with id {}: ", id, sqlException);
			throw new EmployeeDaoException(MessageConstants.ERROR_DAO_DELETE_EMPLOYEE_MESSAGE, sqlException);
		}
		logger.trace("Exiting deleteEmployee({}) from DAO layer with success = {}", id, success);
		return success;
	}

	public boolean updateEmployee(Employee employee) throws EmployeeDaoException {
		logger.trace("Entering updateEmployee() for employee with id {} - In DAO layer", employee.getId());
		boolean success = false;
		try (Connection connection = DatabaseUtil.getConnection();
				PreparedStatement updateStatement = connection.prepareStatement(SqlConstants.UPDATE_EMPLOYEE_QUERY)) {

			updateStatement.setString(1, employee.getFirstName());
			updateStatement.setString(2, employee.getLastName());
			updateStatement.setString(3, employee.getMobileNumber());
			updateStatement.setString(4, employee.getEmail());
			updateStatement.setDate(5, Date.valueOf(employee.getJoiningDate()));
			updateStatement.setBoolean(6, employee.getStatus());
			updateStatement.setInt(7, employee.getId());

			success = updateStatement.executeUpdate() > 0;
		} catch (DatabaseException databaseException) {
			logger.error("Error updating employee with id {}: {}", employee.getId(),
					MessageConstants.ERROR_DATABASE_CONNECTION_MESSAGE, databaseException);
			throw new EmployeeDaoException(MessageConstants.ERROR_DATABASE_CONNECTION_MESSAGE, databaseException);
		} catch (SQLException sqlException) {
			logger.error("Failed to update employee with id {}: " + employee.getId(), sqlException);
			throw new EmployeeDaoException(MessageConstants.ERROR_DAO_UPDATE_EMPLOYEE_MESSAGE, sqlException);
		}
		logger.trace("Exiting updateEmployee() for employee with id {} from DAO layer with success = {}",
				employee.getId(), success);
		return success;
	}
}
