package com.litmus7.employeemanager.util;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.dto.Response;

public class ValidationUtil {

	public static boolean isValidIdFormat(String id) {
		int eid;
		// change the reading to call later if needed.
//		List<Employee> employees = EmployeeController.readEmployeesCSV(EmployeeManagerApp.CSV_FILE_NAME);
		try {
			eid = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			return false;
		}
		if (eid > 0) {
//			for (Employee emp : employees)
//				if (eid == emp.getId())
//					return false;
			return true;
		}
		return false;
	}

	public static boolean isUniqueIdInEmployeeList(String id, List<Employee> employeeList) {
		if (ValidationUtil.isValidIdFormat(id)) {
			if (employeeList != null) {
				for (Employee employee : employeeList)
					if (employee.getId() == Integer.parseInt(id))
						return false;
				return true;
			}
		}
		return false;
	}

	public static boolean isValidName(String name) {
		return (name != null) && (!name.trim().isEmpty());
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

	public static Response<Boolean> isValidEmployee(String id, String firstName, String lastName, String email,
			String mobileNumber, String joiningDate, String status) {
		Response<Boolean> response = new Response<Boolean>(true, null, "");
		if (!ValidationUtil.isValidIdFormat(id)) {
		    response.setMessage(response.getMessage() + "Invalid ID entered\n");
		    response.setSuccess(false);
		}

		if (!ValidationUtil.isValidName(firstName)) {
		    response.setMessage(response.getMessage() + "Invalid First Name entered\n");
		    response.setSuccess(false);
		}

		if (!ValidationUtil.isValidName(lastName)) {
		    response.setMessage(response.getMessage() + "Invalid Last Name entered\n");
		    response.setSuccess(false);
		}

		if (!ValidationUtil.isValidEmail(email)) {
		    response.setMessage(response.getMessage() + "Invalid Email entered\n");
		    response.setSuccess(false);
		}

		if (!ValidationUtil.isValidMobileNumber(mobileNumber)) {
		    response.setMessage(response.getMessage() + "Invalid Mobile Number entered\n");
		    response.setSuccess(false);
		}

		if (!ValidationUtil.isValidJoiningDate(joiningDate)) {
		    response.setMessage(response.getMessage() + "Invalid Joining Date entered (must be YYYY-MM-DD and not a future date)\n");
		    response.setSuccess(false);
		}

		if (!ValidationUtil.isValidStatus(status)) {
		    response.setMessage(response.getMessage() + "Invalid Active Status entered (must be true or false)\n");
		    response.setSuccess(false);
		}
		
		return response;
		
	}

//	public static boolean isValidFile(String fileName, String expectedExtension) {
//		if (fileName == null || fileName.trim().isEmpty())
//			return false;
//		
//		 if (!fileName.toLowerCase().endsWith(expectedExtension.toLowerCase()))
//		        return false;
//		 
//		File file = new File(fileName.trim());
//		return file.exists() && file.isFile();
//	}
	
	public static Response<Boolean> isValidFile(String fileName, String expectedExtension) {
	    Response<Boolean> response = new Response<>(false, null, "");

	    if (fileName == null || fileName.trim().isEmpty()) {
	        response.setMessage("Empty filename.\n");
	        return response;
	    }

	    File file = new File(fileName.trim());
	    if (!file.exists() || !file.isFile()) {
	        response.setMessage(fileName + " does not exist.\n");
	        return response;
	    }
	    
	    if (!fileName.toLowerCase().endsWith(expectedExtension.toLowerCase())) {
	        response.setMessage("Invalid file format.\n");
	        return response;
	    }
	    
	    response.setSuccess(true);
	    return response;
	}
}
