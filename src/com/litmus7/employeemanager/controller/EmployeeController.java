package com.litmus7.employeemanager.controller;

import com.litmus7.employeemanager.constant.ErrorCodeConstants;
import com.litmus7.employeemanager.constant.MessageConstants;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.dto.Response;
import com.litmus7.employeemanager.exception.EmployeeServiceException;
import com.litmus7.employeemanager.exception.EmployeeValidationException;
import com.litmus7.employeemanager.exception.InvalidFileFormatException;
import com.litmus7.employeemanager.service.EmployeeService;
import com.litmus7.employeemanager.util.ErrorCodeUtil;
import com.litmus7.employeemanager.util.TextFileUtil;
import com.litmus7.employeemanager.util.ValidationUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeController {

	private static final Logger logger = LogManager.getLogger(EmployeeController.class);
	private static final EmployeeService employeeService = new EmployeeService();

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

	public Response<Void> createEmployee(Employee employee) {

		Response<Void> response = new Response<>(false, "");

		if (employee == null) {
			logger.warn("Employee object received is null");
			response.setMessage(MessageConstants.EMPTY_EMPLOYEE_MESSAGE);
			return response;
		}

		logger.trace("Entering createEmployee() for employee with id {} - In Control layer", employee.getId());
		try {
			if (ValidationUtil.isValidEmployee(employee)) {

				if (employeeService.createEmployee(employee)) {
					logger.info("Employee with id {} created successfully", employee.getId());
					response.setSuccess(true);
					response.setMessage(MessageConstants.SUCCESS_CREATE_EMPLOYEE_MESSAGE);

				} else {
					logger.info("Failed to create employee with id {}", employee.getId());
					response.setMessage(MessageConstants.FAILED_CREATE_EMPLOYEE_MESSAGE);
				}
			}
		} catch (EmployeeValidationException e) {
			response.setMessage(e.getMessage());
			logger.error("Failed to create employee with id {} due to validation error: {}", employee.getId(),
					e.getMessage());
		} catch (EmployeeServiceException e) {
			response.setMessage(MessageConstants.ERROR_CREATE_EMPLOYEE_PREFIX_MESSAGE
					+ ErrorCodeUtil.getErrorMessage(e.getErrorCode()));
			logger.error("Failed to create employee with id {}  Error code: {}  Message: {}", employee.getId(),
					e.getErrorCode(), ErrorCodeUtil.getErrorMessage(e.getErrorCode()));
		} catch (Exception e) {
			response.setMessage(MessageConstants.ERROR_CREATE_EMPLOYEE_PREFIX_MESSAGE
					+ ErrorCodeUtil.getErrorMessage(ErrorCodeConstants.UNEXPECTED_ERROR_CODE));
			logger.error("Failed to create employee with id {}: Error Code: {}", employee.getId(),
					 ErrorCodeConstants.UNEXPECTED_ERROR_CODE, e);
			
		} finally {
			logger.trace("Exiting createEmployee() - In Control layer");
		}

		return response;

	}

	public Response<List<Employee>> getAllEmployees() {

		Response<List<Employee>> response = new Response<List<Employee>>(false, null, "");
		logger.trace("Entering getAllEmployees() - In Control layer");

		try {
			response.setData(employeeService.getAllEmployees());
			logger.info("Successfully fetched employee data");
			response.setMessage(MessageConstants.SUCCESS_FETCH_ALL_EMPLOYEES_MESSAGE);
			response.setSuccess(true);

		} catch (EmployeeServiceException e) {
			response.setMessage(MessageConstants.ERROR_FETCH_ALL_EMPLOYEE_PREFIX_MESSAGE
					+ ErrorCodeUtil.getErrorMessage(e.getErrorCode()));
			logger.error("Failed to fetch employees: {}", ErrorCodeUtil.getErrorMessage(e.getErrorCode()));
		} catch (Exception e) {
			response.setMessage(MessageConstants.ERROR_CREATE_EMPLOYEE_PREFIX_MESSAGE
					+ ErrorCodeUtil.getErrorMessage(ErrorCodeConstants.UNEXPECTED_ERROR_CODE));
			logger.error("Failed to fetch employees: Error code: {} ", ErrorCodeConstants.UNEXPECTED_ERROR_CODE, e);

		} finally {
			logger.trace("Exiting getAllEmployees() - In Control layer");
		}

		return response;
	}

	public Response<Employee> getEmployeeById(int id) {

		Response<Employee> response = new Response<Employee>(false, null, "");
		logger.trace("Entering getEmployeeById({}) - In Control layer", id);

		if (id <= 0) {
			logger.warn("Invalid id value entered");
			response.setMessage(MessageConstants.INVALID_ID_MESSAGE);
			return response;
		}

		try {
			response.setData(employeeService.getEmployeeById(id));
			logger.info("Successfully fetched details of employee with id {}", id);
			response.setMessage(MessageConstants.SUCCESS_FETCH_EMPLOYEE_MESSAGE);
			response.setSuccess(true);

		} catch (EmployeeServiceException e) {
			response.setMessage(MessageConstants.ERROR_FETCH_EMPLOYEE_BY_ID_PREFIX_MESSAGE
					+ ErrorCodeUtil.getErrorMessage(e.getErrorCode()));
			logger.error("Failed to fetch employee with id {}:  Error Code: {}  Message: {}", id, e.getErrorCode(),
					ErrorCodeUtil.getErrorMessage(e.getErrorCode()));
		} catch (Exception e) {
			response.setMessage(MessageConstants.ERROR_FETCH_EMPLOYEE_BY_ID_PREFIX_MESSAGE
					+ ErrorCodeUtil.getErrorMessage(ErrorCodeConstants.UNEXPECTED_ERROR_CODE));
			logger.error("Failed to fetch employee with id {}: Error Code: {}", 
		             id, ErrorCodeConstants.UNEXPECTED_ERROR_CODE, e);
		} finally {
			logger.trace("Exiting getEmployeeById({}) - In Control layer", id);
		}
		return response;
	}

	public Response<Void> deleteEmployee(int id) {

		Response<Void> response = new Response<>(false, "");
		logger.trace("Entering deleteEmployee({}) - In Control layer", id);

		if (id <= 0) {
			logger.warn("Invalid id value entered");
			response.setMessage(MessageConstants.INVALID_ID_MESSAGE);
			return response;
		}

		try {
			if (employeeService.deleteEmployeeById(id)) {
				logger.info("Successfully deleted employee with id {}", id);
				response.setSuccess(true);
				response.setMessage(MessageConstants.SUCCESS_DELETE_EMPLOYEE_BY_ID_MESSAGE);
			} else {
				logger.info("Failed to delete employee with id {}", id);
				response.setMessage(MessageConstants.FAILED_DELETE_EMPLOYEE_MESSAGE);
			}
		} catch (EmployeeServiceException e) {
			response.setMessage(MessageConstants.ERROR_DELETE_EMPLOYEE_PREFIX_MESSAGE
					+ ErrorCodeUtil.getErrorMessage(e.getErrorCode()));
			logger.error("Failed to delete employee with id {}:  Error code: {}  Message: {}", id, e.getErrorCode(),
					ErrorCodeUtil.getErrorMessage(e.getErrorCode()));
		} catch (Exception e) {
			response.setMessage(MessageConstants.ERROR_DELETE_EMPLOYEE_PREFIX_MESSAGE
					+ ErrorCodeUtil.getErrorMessage(ErrorCodeConstants.UNEXPECTED_ERROR_CODE));
			logger.error("Failed to delete employee with id {}: Error Code: {}", 
		             id, ErrorCodeConstants.UNEXPECTED_ERROR_CODE, e);
		} finally {
			logger.trace("Exiting deleteEmployee({}) - In Control layer", id);
		}
		return response;
	}

	public Response<Void> updateEmployee(Employee employee) {

		Response<Void> response = new Response<>(false, "");

		if (employee == null) {
			logger.warn("Employee object received is null");
			response.setMessage(MessageConstants.EMPTY_EMPLOYEE_MESSAGE);
			return response;
		}
		logger.trace("Entering updateEmployee() for employee with id {} - In Control layer", employee.getId());
		try {
			if (ValidationUtil.isValidEmployee(employee)) {

				if (employeeService.updateEmployee(employee)) {
					logger.info("Successfully updated employee with id {})", employee.getId());
					response.setSuccess(true);
					response.setMessage(MessageConstants.SUCCESS_UPDATE_EMPLOYEE_MESSAGE);

				} else {
					logger.info("Failed to update employee with id {})", employee.getId());
					response.setMessage(MessageConstants.FAILED_UPDATE_EMPLOYEE_MESSAGE);
				}
			}
		} catch (EmployeeValidationException e) {
			response.setMessage(e.getMessage());
			logger.error("Failed to update employee with id {} due to validation error: {}", employee.getId(),
					e.getMessage());
		} catch (EmployeeServiceException e) {
			response.setMessage(MessageConstants.ERROR_UPDATE_EMPLOYEE_PREFIX_MESSAGE + e.getMessage());
			logger.error("Failed to update employee with id {}:  Error Code: {}  Message: {}", employee.getId(),
					e.getErrorCode(), ErrorCodeUtil.getErrorMessage(e.getErrorCode()));
		} catch (Exception e) {
			response.setMessage(MessageConstants.ERROR_UPDATE_EMPLOYEE_PREFIX_MESSAGE
					+ ErrorCodeUtil.getErrorMessage(ErrorCodeConstants.UNEXPECTED_ERROR_CODE));
			logger.error("Failed to update employee with id {}: Error Code: {}", 
		             employee.getId(), ErrorCodeConstants.UNEXPECTED_ERROR_CODE, e);
		} finally {
			logger.trace("Exiting updateEmployee() for employee with id {} - In Control layer", employee.getId());
		}

		return response;

	}
}
