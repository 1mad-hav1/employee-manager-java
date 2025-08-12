package com.litmus7.employeemanager.controller;

import com.litmus7.employeemanager.constant.MessageConstants;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.dto.Response;
import com.litmus7.employeemanager.exception.EmployeeNotFoundException;
import com.litmus7.employeemanager.exception.EmployeeServiceException;
import com.litmus7.employeemanager.exception.EmployeeValidationException;
import com.litmus7.employeemanager.exception.InvalidFileFormatException;
import com.litmus7.employeemanager.service.EmployeeService;
import com.litmus7.employeemanager.util.TextFileUtil;
import com.litmus7.employeemanager.util.ValidationUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController {

	private EmployeeService employeeService = new EmployeeService();

	public Response<List<Employee>> readEmployeeDataFromFile(String inputFileName, String expectedExtension,
			String delimiter) {

		Response<List<Employee>> response = new Response<List<Employee>>(false, null, "");
		try {
			try {
				if (ValidationUtil.isValidFile(inputFileName)
						&& ValidationUtil.isCorrectFileFormat(inputFileName, expectedExtension)) {
					response.setData(TextFileUtil.readEmployeeDataFromTxtFile(inputFileName, delimiter));
					response.setSuccess(true);
					response.setMessage(MessageConstants.SUCCESS_FETCH_EMPLOYEES_FILE_MESSAGE);
				}
			} catch (IllegalArgumentException | InvalidFileFormatException | FileNotFoundException e) {
				response.setMessage(e.getMessage());
			}
		} catch (IOException e) {
			response.setMessage(MessageConstants.ERROR_IOEXCEPTION_FILE_MESSAGE);
		}

		return response;

	}

	public Response<Boolean> convertEmployeeDataToCsv(String inputFileName, String outputFileName) {

		Response<Boolean> response = new Response<Boolean>(false, null, "");
		EmployeeController employeeController = new EmployeeController();

		try {
			if (ValidationUtil.isValidFile(inputFileName) && ValidationUtil.isCorrectFileFormat(inputFileName, ".txt")
					&& ValidationUtil.isCorrectFileFormat(outputFileName, ".csv")) {
				Response<List<Employee>> readResponse = employeeController.readEmployeeDataFromFile(inputFileName,
						".txt", "$");
				if (!readResponse.isSuccess()) {
					response.setMessage(readResponse.getMessage());
					return response;
				}

				if (TextFileUtil.writeEmployeeDataToCsv(outputFileName, readResponse.getData())) {
					response.setMessage(MessageConstants.SUCCESS_WRITE_EMPLOYEES_CSV_MESSAGE);
					response.setSuccess(true);
				}
			}
		} catch (IllegalArgumentException | InvalidFileFormatException | FileNotFoundException e) {
			response.setMessage(e.getMessage());
		} catch (IOException e) {
			response.setMessage(MessageConstants.ERROR_IOEXCEPTION_FILE_MESSAGE);
		}

		return response;

	}

	public Response<Boolean> appendEmployeeDataToCsv(String outputFileName, Employee employee) {

		Response<Boolean> response = new Response<Boolean>(false, null, "");

		try {
			if (ValidationUtil.isValidFile(outputFileName)
					&& ValidationUtil.isCorrectFileFormat(outputFileName, ".csv")) {

				if (ValidationUtil.isValidEmployee(employee)) {

					if (!ValidationUtil.isUniqueId(employee.getId(),
							TextFileUtil.readEmployeeDataFromTxtFile(outputFileName, ","))) {
						response.setMessage(MessageConstants.EXISTING_ID_MESSAGE);
						return response;
					}

					List<Employee> employeeAsList = new ArrayList<Employee>();
					employeeAsList.add(employee);

					if (TextFileUtil.writeEmployeeDataToCsv(outputFileName, employeeAsList)) {

						response.setMessage(MessageConstants.SUCCESS_WRITE_EMPLOYEE_CSV_MESSAGE);
						response.setSuccess(true);
					}
				}

			}
		} catch (IllegalArgumentException | InvalidFileFormatException | EmployeeValidationException
				| FileNotFoundException e) {
			response.setMessage(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			response.setMessage(MessageConstants.ERROR_IOEXCEPTION_FILE_MESSAGE);
		}

		return response;

	}

//-----------------------  DB  ----------------------------------

	public Response<Boolean> createEmployee(Employee employee) {

		Response<Boolean> response = new Response<Boolean>(false, null, "");

		if (employee == null) {
			response.setMessage(MessageConstants.EMPTY_EMPLOYEE_MESSAGE);
			return response;
		}

		try {
			if (ValidationUtil.isValidEmployee(employee)) {

				if (employeeService.createEmployee(employee)) {

					response.setSuccess(true);
					response.setMessage(MessageConstants.SUCCESS_CREATE_EMPLOYEE_MESSAGE);

				} else
					response.setMessage(MessageConstants.FAILED_CREATE_EMPLOYEE_MESSAGE);
			}
		} catch (EmployeeValidationException e) {
			response.setMessage(e.getMessage());
		} catch (EmployeeServiceException e) {
			response.setMessage(MessageConstants.ERROR_CREATE_EMPLOYEE_PREFIX_MESSAGE + e.getMessage());
		}

		return response;

	}

	public Response<List<Employee>> getAllEmployees() {

		Response<List<Employee>> response = new Response<List<Employee>>(false, null, "");

		try {

			response.setData(employeeService.getAllEmployees());
			response.setSuccess(true);

		} catch (EmployeeServiceException e) {
			response.setMessage(MessageConstants.ERROR_FETCH_ALL_EMPLOYEE_PREFIX_MESSAGE + e.getMessage());
		} catch (EmployeeNotFoundException e) {
			response.setMessage(e.getMessage());
		}

		return response;
	}

	public Response<Employee> getEmployeeById(int id) {

		Response<Employee> response = new Response<Employee>(false, null, "");

		if (id <= 0) {
			response.setMessage(MessageConstants.INVALID_ID_MESSAGE);
			return response;
		}

		try {

			response.setData(employeeService.getEmployeeById(id));
			response.setSuccess(true);

		} catch (EmployeeServiceException e) {
			response.setMessage(MessageConstants.ERROR_FETCH_EMPLOYEE_BY_ID_PREFIX_MESSAGE + e.getMessage());
		} catch (EmployeeNotFoundException e) {
			response.setMessage(e.getMessage());
		}

		return response;
	}

	public Response<Boolean> deleteEmployee(int id) {

		Response<Boolean> response = new Response<Boolean>(false, null, "");

		if (id <= 0) {
			response.setMessage(MessageConstants.INVALID_ID_MESSAGE);
			return response;
		}

		try {
			if (employeeService.deleteEmployeeById(id)) {

				response.setSuccess(true);
				response.setMessage(MessageConstants.SUCCESS_DELETE_EMPLOYEE_BY_ID_MESSAGE);
			} else
				response.setMessage(MessageConstants.FAILED_DELETE_EMPLOYEE_MESSAGE);
		} catch (EmployeeServiceException e) {
			response.setMessage(MessageConstants.ERROR_DELETE_EMPLOYEE_PREFIX_MESSAGE + e.getMessage());
		}
		return response;
	}

	public Response<Boolean> updateEmployee(Employee employee) {

		Response<Boolean> response = new Response<Boolean>(false, null, "");

		if (employee == null) {
			response.setMessage(MessageConstants.EMPTY_EMPLOYEE_MESSAGE);
			return response;
		}

		try {
			if (ValidationUtil.isValidEmployee(employee)) {

				if (employeeService.updateEmployee(employee)) {

					response.setSuccess(true);
					response.setMessage(MessageConstants.SUCCESS_UPDATE_EMPLOYEE_MESSAGE);

				} else
					response.setMessage(MessageConstants.FAILED_UPDATE_EMPLOYEE_MESSAGE);
			}
		} catch (EmployeeValidationException e) {
			response.setMessage(e.getMessage());
		} catch (EmployeeServiceException e) {
			response.setMessage(MessageConstants.ERROR_UPDATE_EMPLOYEE_PREFIX_MESSAGE + e.getMessage());
		}

		return response;

	}
}
