package com.litmus7.employeemanager.app;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.litmus7.employeemanager.controller.EmployeeController;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.dto.Response;

public class EmployeeManagerApp {

	public static void main(String[] args) {

		int choiceDb = 0, choiceFile = 0, choiceDbOrFile = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		EmployeeController employeeController = new EmployeeController();
		while (choiceDbOrFile != 3) {

			System.out.print("\nChoose where operation to be performed\n" + "(1. Files\n"
					+ " 2. Database - Employee table\n" + " 3. Exit):");
			try {
				choiceDbOrFile = Integer.parseInt(br.readLine());

				// File operations
				if (choiceDbOrFile == 1) {

					System.out.print("\nChoose operation to perform\n"
							+ "(1. Read and display employee details from Text file\n"
							+ " 2. Convert and store employee details from $ seperated format to csv format\n"
							+ " 3. Read and display employee details from CSV file\n"
							+ " 4. Input new employee details\n" + " 5. Exit):");
					choiceFile = Integer.parseInt(br.readLine());

					// Phase 1: read and print employees from the specified txt file ($ separated
					// content)
					if (choiceFile == 1) {
						System.out.println("Enter filename(txt): ");
						String employeeDataTxtFileName = br.readLine();

						Response<List<Employee>> response = employeeController
								.readEmployeeDataFromFile(employeeDataTxtFileName, ".txt", "$");
						System.out.println(response.getMessage());
						if (response.isSuccess()) {
							System.out.println("Employee Details from " + employeeDataTxtFileName);
							// print employee details
							System.out.printf("%-5s %-15s %-15s %-25s %-15s %-12s %-6s\n", "ID", "FIRST NAME",
									"LAST NAME", "EMAIL", "MOBILE", "JOINING DATE", "STATUS");
							for (Employee emp : response.getData()) {
								System.out.printf("%-5s %-15s %-15s %-25s %-15s %-12s %-6s\n", emp.getId(),
										emp.getFirstName(), emp.getLastName(), emp.getEmail(), emp.getMobileNumber(),
										emp.getJoiningDate(), emp.getStatus());
							}
						}

					}

					// Phase 2 : read employee details from txt file ($ separated values) and store
					// it as csv file
					else if (choiceFile == 2) {
						System.out.println("Enter input filename(.txt): ");
						String inputFileName = br.readLine();

						System.out.println("Enter output filename(.csv): ");
						String outputFileName = br.readLine();

						Response<Boolean> response = employeeController.convertEmployeeDataToCsv(inputFileName,
								outputFileName);
						System.out.println(response.getMessage());

					}

					// read employee details from the specified csv file
					else if (choiceFile == 3) {
						System.out.println("Enter filename(csv): ");
						String employeeDataCsvFileName = br.readLine();

						Response<List<Employee>> response = employeeController
								.readEmployeeDataFromFile(employeeDataCsvFileName, ".csv", ",");
						System.out.println(response.getMessage());
						if (response.isSuccess()) {
							System.out.println("Employee Details from " + employeeDataCsvFileName);
							// print employee details
							System.out.printf("%-5s %-15s %-15s %-25s %-15s %-12s %-6s\n", "ID", "FIRST NAME",
									"LAST NAME", "EMAIL", "MOBILE", "JOINING DATE", "STATUS");
							for (Employee emp : response.getData()) {
								System.out.printf("%-5s %-15s %-15s %-25s %-15s %-12s %-6s\n", emp.getId(),
										emp.getFirstName(), emp.getLastName(), emp.getEmail(), emp.getMobileNumber(),
										emp.getJoiningDate(), emp.getStatus());
							}
						}

					}

					// Phase 3 : append the employee details retrieved from the client to specified
					// csv file
					else if (choiceFile == 4) {
						String ch = "n";
						boolean askFileName = true;
						String outputFileName = "";

						do {
							int id;
							String firstName, lastName, email, mobileNumber;
							LocalDate joiningDate;
							boolean status;

							if (askFileName) {
								System.out.println("Enter filename(csv): ");
								outputFileName = br.readLine();
								askFileName = false;
							}
							System.out.print("Enter ID:");
							try {
								id = Integer.parseInt(br.readLine());
								System.out.print("Enter first name:");
								firstName = br.readLine();
								System.out.print("Enter last name:");
								lastName = br.readLine();
								System.out.print("Enter email:");
								email = br.readLine();
								System.out.print("Enter mobile number:");
								mobileNumber = br.readLine();
								System.out.print("Enter joining date (in yyyy-mm-dd format):");
								joiningDate = LocalDate.parse(br.readLine());
								System.out.print("Enter active status(true/false):");
								status = Boolean.parseBoolean(br.readLine());

								Employee employee = new Employee(id, firstName, lastName, email, mobileNumber,
										joiningDate, status);

								Response<Boolean> response = employeeController.appendEmployeeDataToCsv(outputFileName,
										employee);
								System.out.println(response.getMessage());
								if (!response.isSuccess())
									askFileName = true;
							} catch (NumberFormatException e) {
								System.out.println("Enter valid number.\n");
								askFileName = true;
							} catch (DateTimeParseException e) {
								System.out.println("Enter date in proper format(yyyy-mm-dd format)");
								askFileName = true;
							}
							System.out.println("Do you want to enter more (Y/N)? ");
							ch = br.readLine();
						} while ("y".equalsIgnoreCase(ch));
					}

					else if (choiceFile != 5)
						System.out.print("Enter a valid option.");

					// Database operations - JDBC Assignment
				} else if (choiceDbOrFile == 2) {

					System.out.print("\nChoose operation to perform\n" + "(1. Add employee\n"
							+ " 2. Read all employees\n" + " 3. Read an employee by id\n" + " 4. Update employee\n"
							+ " 5. Delete employee\n" + " 6. Exit):");

					choiceDb = Integer.parseInt(br.readLine());

					if (choiceDb == 1) {
						int id;
						String firstName, lastName, email, mobileNumber;
						LocalDate joiningDate;
						boolean status;
						System.out.print("Enter ID:");
						try {
							id = Integer.parseInt(br.readLine());
							System.out.print("Enter first name:");
							firstName = br.readLine();
							System.out.print("Enter last name:");
							lastName = br.readLine();
							System.out.print("Enter email:");
							email = br.readLine();
							System.out.print("Enter mobile number:");
							mobileNumber = br.readLine();
							System.out.print("Enter joining date (in yyyy-mm-dd format):");
							joiningDate = LocalDate.parse(br.readLine());
							System.out.print("Enter active status(true/false):");
							status = Boolean.parseBoolean(br.readLine());

							Employee employee = new Employee(id, firstName, lastName, email, mobileNumber, joiningDate,
									status);

							Response<Boolean> response = employeeController.createEmployee(employee);
							System.out.println(response.getMessage());
						} catch (NumberFormatException e) {
							System.out.println("Enter valid number.\n");
						} catch (DateTimeParseException e) {
							System.out.println("Enter date in proper format(yyyy-mm-dd format)");
						}

					} else if (choiceDb == 2) {
						Response<List<Employee>> response = employeeController.getAllEmployees();
						System.out.println(response.getMessage());
						if (response.isSuccess()) {
							System.out.println("Employee Details from Employee table");
							// print employee details
							System.out.printf("%-5s %-15s %-15s %-25s %-15s %-12s %-6s\n", "ID", "FIRST NAME",
									"LAST NAME", "EMAIL", "MOBILE", "JOINING DATE", "STATUS");
							for (Employee emp : response.getData()) {
								System.out.printf("%-5s %-15s %-15s %-25s %-15s %-12s %-6s\n", emp.getId(),
										emp.getFirstName(), emp.getLastName(), emp.getEmail(), emp.getMobileNumber(),
										emp.getJoiningDate(), emp.getStatus());
							}
						}
					} else if (choiceDb == 3) {
						System.out.println("Enter id:");
						int id = Integer.parseInt(br.readLine());
						Response<Employee> response = employeeController.getEmployeeById(id);
						System.out.println(response.getMessage());
						if (response.isSuccess()) {
							System.out.println("Employee Details of employee with id: " + id);
							// print employee details
							Employee employee = response.getData();
							System.out.println("First name:" + employee.getFirstName());
							System.out.println("Last Name: " + employee.getLastName());
							System.out.println("Mobile Number: " + employee.getMobileNumber());
							System.out.println("Email: " + employee.getEmail());
							System.out.println("Joining Date: " + employee.getJoiningDate());
							System.out.println("Active Status: " + employee.getStatus());

						}
					} else if (choiceDb == 4) {

						int id;
						String firstName, lastName, email, mobileNumber, joiningDate, status;

						System.out.print("Enter ID:");
						id = Integer.parseInt(br.readLine());

						Response<Employee> isExistingEmployeeResponse = employeeController.getEmployeeById(id);

						if (!isExistingEmployeeResponse.isSuccess())
							System.out.println(isExistingEmployeeResponse.getMessage());
						else {
							Employee employee = isExistingEmployeeResponse.getData();
							System.out.println("Press [Enter] to keep the current value");

							System.out.print("Enter first name (" + employee.getFirstName() + ") :");
							firstName = br.readLine();
							employee.setFirstName(firstName.trim().isEmpty() ? employee.getFirstName() : firstName);

							System.out.print("Enter last name (" + employee.getLastName() + "):");
							lastName = br.readLine();
							employee.setLastName(lastName.trim().isEmpty() ? employee.getLastName() : lastName);

							System.out.print("Enter email (" + employee.getEmail() + "):");
							email = br.readLine();
							employee.setEmail(email.trim().isEmpty() ? employee.getEmail() : email);

							System.out.print("Enter mobile number (" + employee.getMobileNumber() + "):");
							mobileNumber = br.readLine();
							employee.setMobileNumber(
									mobileNumber.trim().isEmpty() ? employee.getMobileNumber() : mobileNumber);

							System.out.print(
									"Enter joining date (in yyyy-mm-dd format) (" + employee.getJoiningDate() + "):");
							joiningDate = br.readLine();
							try {
								employee.setJoiningDate(joiningDate.trim().isEmpty() ? employee.getJoiningDate()
										: LocalDate.parse(joiningDate));

								System.out.print("Enter active status (true/false) (" + employee.getStatus() + "):");
								status = br.readLine();
								employee.setStatus(
										status.trim().isEmpty() ? employee.getStatus() : Boolean.parseBoolean(status));

								Response<Boolean> response = employeeController.updateEmployee(employee);
								System.out.println(response.getMessage());
							} catch (DateTimeParseException e) {
								System.out.println("Enter date in proper format(yyyy-mm-dd format)");
							}
						}
					} else if (choiceDb == 5) {
						System.out.println("Enter id:");
						int id = Integer.parseInt(br.readLine());
						Response<Boolean> response = employeeController.deleteEmployee(id);
						System.out.println(response.getMessage());
					} else if (choiceDb != 6)
						System.out.println("\nEnter a valid choice");

				} else if (choiceDbOrFile != 3)
					System.out.println("\nEnter a valid choice");

			} catch (NumberFormatException e) {
				System.out.println("Enter a valid number");
			} catch (IOException e) {
				System.out.println("Error reading input");
				e.printStackTrace();
			}

		}
	}
}
