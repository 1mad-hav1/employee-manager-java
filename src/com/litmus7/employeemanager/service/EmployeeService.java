package com.litmus7.employeemanager.service;

import java.util.List;
import java.util.Objects;

import com.litmus7.employeemanager.constant.MessageConstants;
import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.exception.EmployeeDaoException;
import com.litmus7.employeemanager.exception.EmployeeNotFoundException;
import com.litmus7.employeemanager.exception.EmployeeServiceException;
import com.litmus7.employeemanager.util.ValidationUtil;

public class EmployeeService {

	private EmployeeDAO employeeDao = new EmployeeDAO();

	public boolean createEmployee(Employee employee) throws EmployeeServiceException {

		try {
			List<Employee> employees = employeeDao.getAllEmployees();
			
			if (!ValidationUtil.isUniqueId(employee.getId(), employees))
				throw new EmployeeServiceException(MessageConstants.EXISTING_ID_MESSAGE);
			
			if (!ValidationUtil.isUniqueEmail(employee.getEmail(), employees))
				throw new EmployeeServiceException(MessageConstants.EXISTING_EMAIL_MESSAGE);
			
			if (!ValidationUtil.isUniqueMobileNumber(employee.getMobileNumber(), employees))
				throw new EmployeeServiceException(MessageConstants.EXISTING_MOBILE_NUMBER_MESSAGE);
			
			return employeeDao.createEmployee(employee);

		} catch (EmployeeDaoException e) {
			throw new EmployeeServiceException(MessageConstants.ERROR_SERVICE_CREATE_EMPLOYEE_MESSAGE, e);
		}

	}

	public List<Employee> getAllEmployees() throws EmployeeServiceException, EmployeeNotFoundException {

		try {
			List<Employee> employees = employeeDao.getAllEmployees();
			if (employees.isEmpty())
				throw new EmployeeNotFoundException(MessageConstants.EMPTY_EMPLOYEES_MESSAGE);
			return employees;
		} catch (EmployeeDaoException e) {
			throw new EmployeeServiceException(MessageConstants.ERROR_SERVICE_FETCH_ALL_EMPLOYEES_MESSAGE, e);
		}

	}

	public Employee getEmployeeById(int id) throws EmployeeServiceException, EmployeeNotFoundException {

		try {
			Employee employee = employeeDao.getEmployeeById(id);
			if (employee == null)
				throw new EmployeeNotFoundException(MessageConstants.EMPTY_EMPLOYEES_MESSAGE);
			return employee;
		} catch (EmployeeDaoException e) {
			throw new EmployeeServiceException(MessageConstants.ERROR_SERVICE_FETCH_EMPLOYEE_BY_ID_MESSAGE, e);
		}

	}

	public boolean deleteEmployeeById(int id) throws EmployeeServiceException {

		try {
			if (employeeDao.getEmployeeById(id) == null)
				throw new EmployeeServiceException(MessageConstants.ID_NOT_EXIST_MESSAGE);

			return employeeDao.deleteEmployee(id);
		} catch (EmployeeDaoException e) {
			throw new EmployeeServiceException(MessageConstants.ERROR_SERVICE_DELETE_EMPLOYEE_MESSAGE, e);
		}

	}

	public boolean updateEmployee(Employee employeeNew) throws EmployeeServiceException {
		try {
			Employee employeeCurrent = employeeDao.getEmployeeById(employeeNew.getId());
			if (employeeCurrent != null) {
				
				if(!Objects.equals(employeeNew.getEmail(), employeeCurrent.getEmail()))
					if (!ValidationUtil.isUniqueEmail(employeeNew.getEmail(), employeeDao.getAllEmployees()))
						throw new EmployeeServiceException(MessageConstants.EXISTING_EMAIL_MESSAGE);
				
				if(!Objects.equals(employeeCurrent.getMobileNumber(), employeeNew.getMobileNumber()))
					if (!ValidationUtil.isUniqueMobileNumber(employeeNew.getMobileNumber(), employeeDao.getAllEmployees()))
						throw new EmployeeServiceException(MessageConstants.EXISTING_MOBILE_NUMBER_MESSAGE);
				
				return employeeDao.updateEmployee(employeeNew);
				
			}
			throw new EmployeeServiceException(MessageConstants.ID_NOT_EXIST_MESSAGE);
			
		} catch (EmployeeDaoException e) {
			throw new EmployeeServiceException(MessageConstants.ERROR_SERVICE_UPDATE_EMPLOYEE_MESSAGE, e);
		}
	}

}
