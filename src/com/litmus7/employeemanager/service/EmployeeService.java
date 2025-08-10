package com.litmus7.employeemanager.service;

import java.sql.SQLException;
import java.util.List;

import com.litmus7.employeemanager.constant.MessageConstants;
import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.exception.BusinessValidationException;
import com.litmus7.employeemanager.exception.EmployeeValidationException;
import com.litmus7.employeemanager.util.ValidationUtil;

public class EmployeeService {

	private EmployeeDAO employeeDao = new EmployeeDAO();

	public boolean createEmployee(Employee employee)
			throws SQLException, BusinessValidationException, EmployeeValidationException {

		if (ValidationUtil.isValidEmployee(employee)) {
			if (!ValidationUtil.isUniqueIdInEmployeeList(employee.getId(), employeeDao.getAllEmployee())) {
				throw new BusinessValidationException(MessageConstants.EXISTING_ID_MESSAGE);
			}

			return employeeDao.createEmployee(employee);
		}
		return false;

	}

	public List<Employee> getAllEmployees() throws SQLException {

		return employeeDao.getAllEmployee();

	}

	public Employee getEmployeeById(int id) throws SQLException {

		return employeeDao.getEmployeeById(id);

	}

	public boolean deleteEmployeeById(int id) throws SQLException, BusinessValidationException {

		if (employeeDao.getEmployeeById(id) == null)
			throw new BusinessValidationException(MessageConstants.ID_NOT_EXIST_MESSAGE);

		return employeeDao.deleteEmployee(id);

	}

	public boolean updateEmployee(Employee employee) throws SQLException, BusinessValidationException, EmployeeValidationException {
		if (ValidationUtil.isValidEmployee(employee)) {
			if (employeeDao.getEmployeeById(employee.getId()) == null)
				throw new BusinessValidationException(MessageConstants.ID_NOT_EXIST_MESSAGE);

			return employeeDao.updateEmployee(employee);
		}
		return false;
	}

}
