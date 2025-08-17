package com.litmus7.employeemanager.service;

import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.litmus7.employeemanager.constant.MessageConstants;
import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.exception.EmployeeDaoException;
import com.litmus7.employeemanager.exception.EmployeeNotFoundException;
import com.litmus7.employeemanager.exception.EmployeeServiceException;
import com.litmus7.employeemanager.util.ValidationUtil;

public class EmployeeService {

	private static final EmployeeDAO employeeDao = new EmployeeDAO();
	private static final Logger logger = LogManager.getLogger(EmployeeService.class);

	public boolean createEmployee(Employee employee) throws EmployeeServiceException {
		logger.trace("Entering createEmployee() for creating employee with id: {} - In Service layer", employee.getId());
		try {
			List<Employee> employees = employeeDao.getAllEmployees();

			if (!ValidationUtil.isUniqueId(employee.getId(), employees)) {
				logger.error("Error creating employee with id {}:  Id already exist", employee.getId());
				throw new EmployeeServiceException(MessageConstants.EXISTING_ID_MESSAGE);
			}

			if (!ValidationUtil.isUniqueEmail(employee.getEmail(), employees)) {
				logger.error("Error creating employee wiith id {}: Email already exist", employee.getId());
				throw new EmployeeServiceException(MessageConstants.EXISTING_EMAIL_MESSAGE);
			}

			if (!ValidationUtil.isUniqueMobileNumber(employee.getMobileNumber(), employees)) {
				logger.error("Error creating employee with id {}: Mobile number already exist", employee.getId());
				throw new EmployeeServiceException(MessageConstants.EXISTING_MOBILE_NUMBER_MESSAGE);
			}

			if (employeeDao.createEmployee(employee)) {
				logger.info("Employee with id {} created successfully", employee.getId());
				return true;
			}
			logger.warn("Failed to create employee with id {}", employee.getId());
			return false;
		} catch (EmployeeDaoException e) {
			logger.error("Error creating employee with id {}: ", employee.getId(), e);
			throw new EmployeeServiceException(MessageConstants.ERROR_SERVICE_CREATE_EMPLOYEE_MESSAGE, e);
		} finally {
			logger.trace("Exiting from createEmployee() for employee with id {} - In Service layer", employee.getId());
		}
	}

	public List<Employee> getAllEmployees() throws EmployeeServiceException, EmployeeNotFoundException {
		logger.trace("Entering getAllEmployees() - In Service layer");
		List<Employee> employees;
		try {
			employees = employeeDao.getAllEmployees();
			if (employees.isEmpty()) {
				logger.info(MessageConstants.EMPTY_EMPLOYEES_MESSAGE);
				throw new EmployeeNotFoundException(MessageConstants.EMPTY_EMPLOYEES_MESSAGE);
			}
			logger.info("Employee details fetched successfully");
		} catch (EmployeeDaoException e) {
			logger.error("Error fetching employees: " + e);
			throw new EmployeeServiceException(MessageConstants.ERROR_SERVICE_FETCH_ALL_EMPLOYEES_MESSAGE, e);
		}

		logger.trace("Exiting getAllEmployees() with {} employees fetched - In Service layer", employees.size());
		return employees;
	}

	public Employee getEmployeeById(int id) throws EmployeeServiceException, EmployeeNotFoundException {
		logger.trace("Entering getEmployeeById({}) - In Service layer", id);
		Employee employee = null;
		try {
			employee = employeeDao.getEmployeeById(id);
			if (employee == null) {
				logger.info(MessageConstants.EMPTY_EMPLOYEES_MESSAGE);
				throw new EmployeeNotFoundException(MessageConstants.EMPTY_EMPLOYEES_MESSAGE);
			}
			logger.info("Successfully fetched employee with id: {}", id);

		} catch (EmployeeDaoException e) {
			logger.error("Failed to fetch employee with id {}:", id, e);
			throw new EmployeeServiceException(MessageConstants.ERROR_SERVICE_FETCH_EMPLOYEE_BY_ID_MESSAGE, e);
		}

		logger.trace("Exiting getEmployeeById({}) - In Service layer", id);
		return employee;
	}

	public boolean deleteEmployeeById(int id) throws EmployeeServiceException {
		logger.trace("Entering deleteEmployeeById({}) - In Service layer", id);
		try {
			if (employeeDao.getEmployeeById(id) == null) {
				logger.error("Error deleting employee with id {}: ", MessageConstants.ID_NOT_EXIST_MESSAGE);
				throw new EmployeeServiceException(MessageConstants.ID_NOT_EXIST_MESSAGE);
			}
			
			if (employeeDao.deleteEmployee(id)) {
				logger.info("Employee with id {} deleted successfully", id);
				return true;
			}
			logger.info("Failed to delete employee with id {}", id);
			return false;

		} catch (EmployeeDaoException e) {
			logger.error("Error deleting employee with id {}: ", id, e);
			throw new EmployeeServiceException(MessageConstants.ERROR_SERVICE_DELETE_EMPLOYEE_MESSAGE, e);
		} finally {
			logger.trace("Exiting from deleteEmployeeById({}) - In Service layer", id);
		}
	}

	public boolean updateEmployee(Employee employeeNew) throws EmployeeServiceException {
		logger.trace("Entering updateEmployee() for employee with id {} - In Service layer", employeeNew.getId());
		try {
			Employee employeeCurrent = employeeDao.getEmployeeById(employeeNew.getId());
			if (employeeCurrent != null) {

				if (!Objects.equals(employeeNew.getEmail(), employeeCurrent.getEmail()))

					if (!ValidationUtil.isUniqueEmail(employeeNew.getEmail(), employeeDao.getAllEmployees())) {
						logger.error("Failed to update employee with id {}: " + MessageConstants.EXISTING_EMAIL_MESSAGE,
								employeeNew.getId());
						throw new EmployeeServiceException(MessageConstants.EXISTING_EMAIL_MESSAGE);
					}
				if (!Objects.equals(employeeCurrent.getMobileNumber(), employeeNew.getMobileNumber()))
					if (!ValidationUtil.isUniqueMobileNumber(employeeNew.getMobileNumber(),
							employeeDao.getAllEmployees())) {
						logger.error("Failed to update employee with id {}: "
								+ MessageConstants.EXISTING_MOBILE_NUMBER_MESSAGE, employeeNew.getId());
						throw new EmployeeServiceException(MessageConstants.EXISTING_MOBILE_NUMBER_MESSAGE);
					}
				if (employeeDao.updateEmployee(employeeNew)) {
					logger.info("Updated Employee successfully with id {}: " + employeeNew.getId());
					return true;
				}
				logger.info("Failed to update employee with id {}: " + employeeNew.getId());
				return false;

			} else {
				logger.error("Failed to update employee with id {}: " + MessageConstants.ID_NOT_EXIST_MESSAGE,
						employeeNew.getId());
				throw new EmployeeServiceException(MessageConstants.ID_NOT_EXIST_MESSAGE);
			}

		} catch (EmployeeDaoException e) {
			logger.error("Failed to update employee with id {}: " + employeeNew.getId(), e);
			throw new EmployeeServiceException(MessageConstants.ERROR_SERVICE_UPDATE_EMPLOYEE_MESSAGE, e);
		} finally {
			logger.trace("Exiting from updateEmployee() for employee with id {} - In Service layer", employeeNew.getId());
		}
	}

}
