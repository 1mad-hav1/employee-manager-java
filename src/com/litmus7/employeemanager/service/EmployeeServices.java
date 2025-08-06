package com.litmus7.employeemanager.service;

import java.time.LocalDate;
import java.util.List;

import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.dto.Response;
import com.litmus7.employeemanager.util.ValidationUtil;

public class EmployeeServices {

	EmployeeDAO employeeDao = new EmployeeDAO();

	public Response<Boolean> addEmployeeToDb(String id, String firstName, String lastName, String email,
			String mobileNumber, String joiningDate, String status) {
		Response<Boolean> response = new Response<Boolean>(false, null, "");

		Response<Boolean> validationResponse = ValidationUtil.isValidEmployee(id, firstName, lastName, email,
				mobileNumber, joiningDate, status);
		if (!validationResponse.isSuccess()) {
			response.setMessage(validationResponse.getMessage());
			return response;
		}

		// checking id uniqueness
		List<Employee> employees = employeeDao.getAllEmployee();
		if (!ValidationUtil.isUniqueIdInEmployeeList(id, employees)) {
			response.setMessage("\nId entered already exist\n");
			return response;
		}

		Employee employee = new Employee(Integer.parseInt(id), firstName, lastName, email, mobileNumber,
				LocalDate.parse(joiningDate), Boolean.parseBoolean(status));
		if (employeeDao.createEmployee(employee)) {
			response.setSuccess(true);
			response.setMessage("\nEmployee added successfully\n");
		}

		return response;

	}

	public Response<List<Employee>> getAllEmployeesFromDb() {

		List<Employee> employees = employeeDao.getAllEmployee();

		Response<List<Employee>> response = new Response<List<Employee>>(false, employees, "");

		if (!employees.isEmpty()) {
			response.setSuccess(true);
			response.setMessage("\nSuccessfully fetched employees\n");
		} else
			response.setMessage("\nNo employee records found\n");

		return response;

	}

	public Response<Employee> getEmployeesByIdFromDb(String id) {

		Response<Employee> response = new Response<Employee>(false, null, "");

		if (!ValidationUtil.isValidIdFormat(id)) {
			response.setMessage("\nInvalid id format.\n");
		}

		Employee employee = employeeDao.getEmployeeById(Integer.parseInt(id));
		if (employee != null) {
			response.setSuccess(true);
			response.setData(employee);
			response.setMessage("\nEmployee with id " + id + " retrieved\n");
		} else
			response.setMessage("\nEmployee with id " + id + " does not exist\n");

		return response;
	}

	public Response<Boolean> deleteEmployeeFromDb(String id) {

		Response<Boolean> response = new Response<Boolean>(false, null, "");

		if (!ValidationUtil.isValidIdFormat(id)) {
			response.setMessage("\nInvalid id format.\n");
		}

		Employee employee = employeeDao.getEmployeeById(Integer.parseInt(id));
		if (employee == null)
			response.setMessage("Employee with id " + id + " does not exist\n");
		else if (employeeDao.deleteEmployee(Integer.parseInt(id))) {
			response.setSuccess(true);
			response.setMessage("\nEmployee with id " + id + " has been deleted\n");
		} else
			response.setMessage("\nFailed to delete employee\n");

		return response;
	}

	public Response<Boolean> updateEmployeeInDb(String id, String firstName, String lastName, String email,
			String mobileNumber, String joiningDate, String status) {
		Response<Boolean> response = new Response<Boolean>(false, null, "");

		if (employeeDao.getEmployeeById(Integer.parseInt(id)) == null) {
			response.setMessage("\nEmployee with id " + id + " does not exist\n");
			return response;
		}

		Response<Boolean> validationResponse = ValidationUtil.isValidEmployee(id, firstName, lastName, email,
				mobileNumber, joiningDate, status);
		if (!ValidationUtil.isValidEmployee(id, firstName, lastName, email, mobileNumber, joiningDate, status)
				.isSuccess()) {
			response.setMessage(validationResponse.getMessage());
			return response;
		}

		Employee employee = new Employee(Integer.parseInt(id), firstName, lastName, email, mobileNumber,
				LocalDate.parse(joiningDate), Boolean.parseBoolean(status));
		if (employeeDao.updateEmployee(employee)) {
			response.setSuccess(true);
			response.setMessage("\nEmployee data updated successfully\n");
		} else
			response.setMessage("\nFailed to update Employee details\n");

		return response;

	}

}
