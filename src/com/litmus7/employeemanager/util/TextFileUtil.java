package com.litmus7.employeemanager.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.dto.Response;

public class TextFileUtil {

	public static Response<List<Employee>> readEmployeeDataFromTxtFile(String inputFileName, String delimiter) {

		Response<List<Employee>> response = new Response<List<Employee>>(false, null, "");
		List<Employee> employeeList = new ArrayList<>();
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(inputFileName));

			int corruptedRowCount = 0;
			String id, emp, firstName, lastName, mobileNumber, email, joiningDate, status;
//			LocalDate joiningDate;
//			boolean status;

			while ((emp = br.readLine()) != null) {

				StringTokenizer employeeTokens = new StringTokenizer(emp, delimiter);
				if (employeeTokens.countTokens() != 7) {
					corruptedRowCount++;
					continue;
				}

				id = employeeTokens.nextToken();
				firstName = employeeTokens.nextToken();
				lastName = employeeTokens.nextToken();
				mobileNumber = employeeTokens.nextToken();
				email = employeeTokens.nextToken();
				joiningDate = employeeTokens.nextToken();
				status = employeeTokens.nextToken();

				Response<Boolean> validationResponse = ValidationUtil.isValidEmployee(id, firstName, lastName, email, mobileNumber, joiningDate, status);
				if (validationResponse.isSuccess()) {
					Employee employee = new Employee(Integer.parseInt(id), firstName, lastName, email, mobileNumber,
							LocalDate.parse(joiningDate), Boolean.parseBoolean(status));

					employeeList.add(employee);
				} else
					corruptedRowCount++;
			}
			response.setSuccess(true);
			response.setData(employeeList);
			response.setMessage("Employee details fetched from " + inputFileName + " successfully.\n");
			if (corruptedRowCount > 0)
				response.setMessage(response.getMessage() + corruptedRowCount + " corrupted rows were skipped");
		} catch (FileNotFoundException e) {
			response.setMessage(inputFileName + "File not found");
		} catch (IOException e) {
			response.setMessage("Input error: Unable to read from input stream.");
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					response.setMessage(response.getMessage() + "\nWARNING : Error occured while closing the file");
				}
		}

		return response;

	}

	public static Response<Boolean> writeEmployeeDataToCsv(String outputFilename, List<Employee> employeeList) {
		PrintWriter pw = null;
		Response<Boolean> response = new Response<Boolean>(false, null, "");

		if (employeeList == null || employeeList.isEmpty()) {
			response.setMessage("No employee data to write.");
			return response;
		}

		String employeeCSV;
		try {
			pw = new PrintWriter(new FileWriter(outputFilename, true));
			for (Employee employee : employeeList) {
				employeeCSV = String.format("%d,%s,%s,%s,%s,%s,%b", employee.getId(), employee.getFirstName(),
						employee.getLastName(), employee.getMobileNumber(), employee.getEmail(),
						employee.getJoiningDate(), employee.getStatus());
//				System.out.println(employeeCSV);
				pw.println(employeeCSV);
			}
			response.setSuccess(true);
			response.setMessage("Employee details written to " + outputFilename + " successfully");
		} catch (IOException e) {
			response.setMessage("Failed to write employee data to " + outputFilename);
		} finally {
			if (pw != null)
				pw.close();
		}
		return response;

	}
}
