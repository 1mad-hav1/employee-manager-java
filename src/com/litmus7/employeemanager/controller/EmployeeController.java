package com.litmus7.employeemanager.controller;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.dto.Response;
import com.litmus7.employeemanager.service.EmployeeServices;
import com.litmus7.employeemanager.util.TextFileUtil;
import com.litmus7.employeemanager.util.ValidationUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController {

	EmployeeServices employeeServices = new EmployeeServices();

	public Response<List<Employee>> readEmployeeDataFromFile(String inputFileName, String expectedExtension,
			String delimiter) {
		Response<List<Employee>> response = new Response<List<Employee>>(false, null, "");
		Response<Boolean> fileValidationResponse = ValidationUtil.isValidFile(inputFileName, expectedExtension);
		if (!fileValidationResponse.isSuccess()) {
			response.setMessage(fileValidationResponse.getMessage());
			return response;
		}

		response = TextFileUtil.readEmployeeDataFromTxtFile(inputFileName, delimiter);
		return response;

	}


	public Response<Boolean> convertEmployeeDataToCsv(String inputFileName, String outputFileName) {

		Response<Boolean> response = new Response<Boolean>(false, null, "");
		EmployeeController ec = new EmployeeController();

		Response<Boolean> fileValidationResponse = ValidationUtil.isValidFile(inputFileName, ".txt");
		if (!fileValidationResponse.isSuccess()) {
			response.setMessage(fileValidationResponse.getMessage());
			return response;
		}

		Response<List<Employee>> readResponse = ec.readEmployeeDataFromFile(inputFileName, ".txt", "$");
		if (!readResponse.isSuccess()) {
			response.setMessage("Reading failed: " + readResponse.getMessage());
			return response;
		}

		response = TextFileUtil.writeEmployeeDataToCsv(outputFileName, readResponse.getData());
		if (!response.isSuccess()) {
			response.setMessage("Writing failed: " + response.getMessage());
			return response;
		}

		response.setMessage(readResponse.getMessage() + "\n" + response.getMessage());
		return response;

	}


	public Response<Boolean> appendEmployeeDataToCsv(String outputFileName, String id, String firstName,
			String lastName, String email, String mobileNumber, String joiningDate, String status) {
		Response<Boolean> response = new Response<Boolean>(false, null, "");

		Response<Boolean> fileValidationResponse = ValidationUtil.isValidFile(outputFileName, ".csv");
		if (!fileValidationResponse.isSuccess()) {
			response.setMessage(fileValidationResponse.getMessage());
			return response;
		}

		Response<Boolean> validationResponse = ValidationUtil.isValidEmployee(id, firstName, lastName, email,
				mobileNumber, joiningDate, status);

		if (!validationResponse.isSuccess()) {
			response.setMessage(validationResponse.getMessage());
			return response;
		}

		Response<List<Employee>> readResponse = TextFileUtil.readEmployeeDataFromTxtFile(outputFileName, ",");
		if (response.isSuccess()) {
			if (!ValidationUtil.isUniqueIdInEmployeeList(id, readResponse.getData())) {
				response.setMessage("ID already exists.\n");
				return response;
			}
		}

		Employee employee = new Employee(Integer.parseInt(id), firstName, lastName, email, mobileNumber,
				LocalDate.parse(joiningDate), Boolean.parseBoolean(status));

		List<Employee> employeeAsList = new ArrayList<Employee>();
		employeeAsList.add(employee);

		response = TextFileUtil.writeEmployeeDataToCsv(outputFileName, employeeAsList);
		if (!response.isSuccess()) {
			response.setMessage("Writing failed: " + response.getMessage());
			return response;
		}

		return response;

	}

//-----------------------  DB  ----------------------------------

	public Response<Boolean> addEmployeeToEmployeeTable(String id, String firstName, String lastName, String email,
			String mobileNumber, String joiningDate, String status) {

		return employeeServices.addEmployeeToDb(id, firstName, lastName, email, mobileNumber, joiningDate, status);

	}


	public Response<List<Employee>> readAllEmployeeDataFromDb() {

		return employeeServices.getAllEmployeesFromDb();

	}

	public Response<Employee> readEmployeeDataByIdFromDb(String id) {

		return employeeServices.getEmployeesByIdFromDb(id);

	}

	public Response<Boolean> deleteEmployeeFromDb(String id) {

		return employeeServices.deleteEmployeeFromDb(id);

	}

	public Response<Boolean> updateEmployeeInDb(String id, String firstName, String lastName, String email,
			String mobileNumber, String joiningDate, String status) {

		return employeeServices.updateEmployeeInDb(id, firstName, lastName, email, mobileNumber, joiningDate, status);

	}
}
