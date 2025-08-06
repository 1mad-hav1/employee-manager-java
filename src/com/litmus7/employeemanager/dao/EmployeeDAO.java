package com.litmus7.employeemanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.util.DatabaseUtil;

public class EmployeeDAO {

	public boolean createEmployee(Employee employee) {
		try (Connection connection = DatabaseUtil.getConnection()) {
			String insertQuery = "insert into employee(id, first_name, last_name, mobile_number, email, joining_date, active_status) "
					+ "values(?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

			insertStatement.setInt(1, employee.getId());
			insertStatement.setString(2, employee.getFirstName());
			insertStatement.setString(3, employee.getLastName());
			insertStatement.setString(4, employee.getMobileNumber());
			insertStatement.setString(5, employee.getEmail());
			insertStatement.setDate(6, Date.valueOf(employee.getJoiningDate()));
			insertStatement.setBoolean(7, employee.getStatus());

			int rowsInserted = insertStatement.executeUpdate();
			if (rowsInserted > 0)
				return true;
			return false;

		} catch (SQLException e) {
			return false;
		}

	}

	public List<Employee> getAllEmployee() {
		List<Employee> employees = new ArrayList<>();
		try (Connection connection = DatabaseUtil.getConnection()) {
//			System.out.println("Connection successful");
			String selectQuery = "select id, first_name, last_name, mobile_number, email, joining_date, active_status from employee;";
			PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
			ResultSet employeeRs = selectStatement.executeQuery();

			while (employeeRs.next()) {
				Employee employee = new Employee(employeeRs.getInt("id"), employeeRs.getString("first_name"),
						employeeRs.getString("last_name"), employeeRs.getString("email"),
						employeeRs.getString("mobile_number"), employeeRs.getDate("joining_date").toLocalDate(),
						employeeRs.getBoolean("active_status"));
				employees.add(employee);
			}

		} catch (SQLException e) {
//			System.out.println("[ERROR] Failed to get employees data.");
		}

		return employees;
	}

	public Employee getEmployeeById(int emp_id) {
		Employee employee = null;
		try (Connection connection = DatabaseUtil.getConnection()) {
			String selectQuery = "select id, first_name, last_name, mobile_number, email, joining_date, active_status from employee where id = ?;";
			PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
			selectStatement.setInt(1, emp_id);

			ResultSet employeeRs = selectStatement.executeQuery();

			if (employeeRs.next()) {
				employee = new Employee(employeeRs.getInt("id"), employeeRs.getString("first_name"),
						employeeRs.getString("last_name"), employeeRs.getString("email"),
						employeeRs.getString("mobile_number"), employeeRs.getDate("joining_date").toLocalDate(),
						employeeRs.getBoolean("active_status"));
			}

		} catch (SQLException e) {
//			System.out.println("[ERROR] Failed to get employee details.");
		}

		return employee;
	}

	public boolean deleteEmployee(int emp_id) {

		try (Connection connection = DatabaseUtil.getConnection()) {
			String deleteQuery = "delete from employee where id  = ?;";
			PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
			deleteStatement.setInt(1, emp_id);

			int rowsDeleted = deleteStatement.executeUpdate();

			if (rowsDeleted > 0)
				return true;

		} catch (SQLException e) {
//			System.out.println("[ERROR] Failed to delete employee details.");
		}
		return false;

	}

	public boolean updateEmployee(Employee employee) {
		try (Connection connection = DatabaseUtil.getConnection()) {
			String updateQuery = "update employee set first_name = ?, last_name = ?, mobile_number = ?, email = ?, joining_date = ?, active_status = ? where id = ?;";
			PreparedStatement updateStatement = connection.prepareStatement(updateQuery);

			updateStatement.setString(1, employee.getFirstName());
			updateStatement.setString(2, employee.getLastName());
			updateStatement.setString(3, employee.getMobileNumber());
			updateStatement.setString(4, employee.getEmail());
			updateStatement.setDate(5, Date.valueOf(employee.getJoiningDate()));
			updateStatement.setBoolean(6, employee.getStatus());
			updateStatement.setInt(7, employee.getId());

			if (updateStatement.executeUpdate() > 0)
				return true;

		} catch (SQLException e) {
//			System.out.println("[ERROR] Failed to update employee details.");
		}
		return false;
	}

}
