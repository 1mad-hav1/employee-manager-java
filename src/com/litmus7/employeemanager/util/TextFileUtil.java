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

import com.litmus7.employeemanager.constant.MessageConstants;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.exception.EmployeeValidationException;

public class TextFileUtil {

	public static List<Employee> readEmployeeDataFromTxtFile(String inputFileName, String delimiter) throws FileNotFoundException, IOException {

		List<Employee> employees = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {

			int corruptedRowCount = 0;
			String id, emp, firstName, lastName, mobileNumber, email, joiningDate, status;
//			LocalDate joiningDate;
//			boolean status;
			Employee employee;
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
				
				try {
					employee = new Employee(Integer.parseInt(id), firstName, lastName, email, mobileNumber,
							LocalDate.parse(joiningDate), Boolean.parseBoolean(status));
				} catch (Exception e) {
					corruptedRowCount++;
					continue;
				}
				
				try {
					if (ValidationUtil.isValidEmployee(employee)) {
						employees.add(employee);
					} else
						corruptedRowCount++;
				} catch (EmployeeValidationException e) {
					corruptedRowCount++;
				}
			}
			
			if (corruptedRowCount > 0);
				
		} 

		return employees;

	}

	public static boolean writeEmployeeDataToCsv(String outputFilename, List<Employee> employees) throws IOException {
		
		if (employees == null || employees.isEmpty()) {
			throw new IllegalArgumentException(MessageConstants.EMPTY_EMPLOYEE_LIST_MESSAGE);
		}

		String employeeCSV;
		try (PrintWriter pw = new PrintWriter(new FileWriter(outputFilename, true))) {
			for (Employee employee : employees) {
				employeeCSV = String.format("%d,%s,%s,%s,%s,%s,%b", employee.getId(), employee.getFirstName(),
						employee.getLastName(), employee.getMobileNumber(), employee.getEmail(),
						employee.getJoiningDate(), employee.getStatus());
//				System.out.println(employeeCSV);
				pw.println(employeeCSV);
			}
			return true;
		} 
		
	}
}
