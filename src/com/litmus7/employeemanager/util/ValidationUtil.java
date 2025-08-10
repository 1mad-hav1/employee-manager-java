package com.litmus7.employeemanager.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.litmus7.employeemanager.constant.MessageConstants;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.exception.EmployeeValidationException;
import com.litmus7.employeemanager.exception.InvalidFileFormatException;

public class ValidationUtil {

	public static boolean isValidIdFormat(String id) {
		int employeeId;
		try {
			employeeId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			return false;
		}
		if (employeeId > 0) {
			return true;
		}
		return false;
	}

	public static boolean isUniqueIdInEmployeeList(int id, List<Employee> employees) {
		if (employees != null) {
			for (Employee employee : employees)
				if (employee.getId() == id)
					return false;
			return true;
		}
		return true;
	}

	public static boolean isValidName(String name) {
		return (name != null) && (!name.trim().isEmpty()) && (name.matches("[a-zA-Z\\s'-]+"));
	}

	public static boolean isValidEmail(String email) {
		if (email == null || email.trim().isEmpty())
			return false;
		String emailRE = "^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.-]+$";
		return email.matches(emailRE);
	}

	public static boolean isValidMobileNumber(String mobileNumber) {
		if (mobileNumber == null || mobileNumber.trim().isEmpty())
			return false;
		String mobileNumRE = "^[0-9]{10}$";
		return mobileNumber.matches(mobileNumRE);
	}

	public static boolean isValidJoiningDate(String joiningDate) {
		if (joiningDate == null || joiningDate.trim().isEmpty())
			return false;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			LocalDate date = LocalDate.parse(joiningDate, formatter);
			LocalDate today = LocalDate.now();
			return !date.isAfter(today);
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	public static boolean isValidStatus(String status) {
		if (status == null || status.trim().isEmpty())
			return false;
		status = status.trim().toLowerCase();
		return status.equals("true") || status.equals("false");
	}

	public static boolean isValidEmployee(Employee employee) throws EmployeeValidationException {

	    StringBuilder errorMessage = new StringBuilder();

	    if (!ValidationUtil.isValidIdFormat(String.valueOf(employee.getId()))) {
	        errorMessage.append(MessageConstants.INVALID_ID_MESSAGE);
	    }

	    if (!ValidationUtil.isValidName(employee.getFirstName())) {
	        errorMessage.append(MessageConstants.INVALID_FIRST_NAME_MESSAGE);
	    }

	    if (!ValidationUtil.isValidName(employee.getLastName())) {
	        errorMessage.append(MessageConstants.INVALID_LAST_NAME_MESSAGE);
	    }

	    if (!ValidationUtil.isValidEmail(employee.getEmail())) {
	        errorMessage.append(MessageConstants.INVALID_EMAIL_MESSAGE);
	    }

	    if (!ValidationUtil.isValidMobileNumber(employee.getMobileNumber())) {
	        errorMessage.append(MessageConstants.INVALID_MOBILE_NUMBER_MESSAGE);
	    }

	    if (!ValidationUtil.isValidJoiningDate(employee.getJoiningDate().toString())) {
	        errorMessage.append(MessageConstants.INVALID_JOINING_DATE_MESSAGE);
	    }

	    if (!ValidationUtil.isValidStatus(String.valueOf(employee.getStatus()))) {
	        errorMessage.append(MessageConstants.INVALID_ACTIVE_STATUS_MESSAGE);
	    }

	    if (errorMessage.length() > 0) {
	        throw new EmployeeValidationException(errorMessage.toString());
	    }

	    return true;
	}


	public static boolean isValidFile(String fileName) throws FileNotFoundException, IllegalArgumentException, InvalidFileFormatException {
		
		if (fileName == null || fileName.trim().isEmpty()) {
		    throw new IllegalArgumentException(MessageConstants.EMPTY_FILE_NAME_MESSAGE);
		}

		File file = new File(fileName.trim());
		if (!file.exists() || !file.isFile()) {
		    throw new FileNotFoundException(MessageConstants.FILE_NOT_FOUND_MESSAGE);
		}
		
		return true;
	}
	
	public static boolean isCorrectFileFormat(String fileName, String expectedExtension) throws IllegalArgumentException, InvalidFileFormatException {
		
		if (fileName == null || expectedExtension == null) {
	        throw new IllegalArgumentException(MessageConstants.EMPTY_FILE_NAME_OR_EXTENSION_MESSAGE);
	    }
		
		if (!fileName.toLowerCase().endsWith(expectedExtension.toLowerCase())) {
		    throw new InvalidFileFormatException(MessageConstants.INVALID_FILE_FORMAT_MESSAGE);
		}
		
		return true;
	}
	
}
