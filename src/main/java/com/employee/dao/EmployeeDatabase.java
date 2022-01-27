package com.employee.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.employee.model.Employee;

public class EmployeeDatabase {
	private String jdbcUrl = "jdbc:postgresql://localhost:2020/employee";
	private String databaseName = "postgres";
	private String databasePassword = "root123";
	private String databaseDriver = "org.postgresql.Driver";

	public void loadDriver(String databaseDriver) {

		try {
			Class.forName(databaseDriver);
		} catch (ClassNotFoundException exception) {
			System.out.println("Exception");
		}
	}

	public Connection getConnection() {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(jdbcUrl, databaseName, databasePassword);
		} catch (SQLException exception) {
			System.out.println("Exception");
		}
		return connection;
	}

	public void addNewEmployee(Employee employee) {

		try {
			loadDriver(databaseDriver);
			Connection connection = getConnection();
			PreparedStatement preparedStatement;
			String sql = "INSERT INTO employeedetails (id, name, salary, number, date) values (?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, employee.getEmployeeId());
			preparedStatement.setString(2, employee.getEmployeeName());
			preparedStatement.setDouble(3, employee.getSalary());
			preparedStatement.setString(4, employee.getPhoneNumber());
			preparedStatement.setDate(5, employee.getDate());
			preparedStatement.executeUpdate();
			System.out.println("Data entered in database successfully");
		} catch (SQLException exception) {
			System.out.println("Data not entered in database");
		}
	}

	public void deleteEmployee(int employeeId) {
		
		try {
			loadDriver(databaseDriver);
			Connection connection = getConnection();
			PreparedStatement preparedStatement;
			String sql = "DELETE FROM employeedetails WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, employeeId);
			preparedStatement.execute();
			System.out.println("Data deleted in database successfully");
		} catch (SQLException exception) {
			System.out.println("Data not deleted");
		}
	}
	
	public void viewEmployees() {
		
		try {
			loadDriver(databaseDriver);
			PreparedStatement preparedStatement;
			ResultSet resultSet;
			Connection connection = getConnection();
			String sql = "SELECT * FROM employeedetails";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.execute();
			resultSet = preparedStatement.executeQuery();  
			
			while (resultSet.next()) {      
				 int id = resultSet.getInt("id");
				 String name = resultSet.getString("name");        
				 double salary = resultSet.getDouble("salary");
				 String number = resultSet.getString("number");
				 Date date = resultSet.getDate("date");
				 
				 System.out.println(String.format("%s %s %s %s %s", id, name, salary, number, date)); 
				}
		} catch (SQLException exception) {
			System.out.println("Cannot view employee details");
		}
	}
	
	public void updateEmployee(Employee employee) {
		
		try {
			loadDriver(databaseDriver);
			Connection connection = getConnection();
			String name = "UPDATE employeedetails set name = ? WHERE id = ?";
			String salary = "UPDATE employeedetails set salary = ? WHERE id = ?";
			String number = "UPDATE employeedetails set number = ? WHERE id = ?";
			String date = "UPDATE employeedetails set date = ? WHERE id = ?";
			
			if(employee.getEmployeeId() != 0) {
				PreparedStatement preparedStatement;
				
				if (employee.getEmployeeName() != null) {
					preparedStatement = connection.prepareStatement(name);
					
					preparedStatement.setString(1, employee.getEmployeeName());
					preparedStatement.setInt(2, employee.getEmployeeId());
					preparedStatement.executeUpdate();
				} else if (employee.getSalary() != 0) {
					preparedStatement = connection.prepareStatement(salary);
					
					preparedStatement.setDouble(1, employee.getSalary());
					preparedStatement.setInt(2, employee.getEmployeeId());
					preparedStatement.executeUpdate();
				} else if (employee.getPhoneNumber() != null) {
					preparedStatement = connection.prepareStatement(number);
					
					preparedStatement.setString(1, employee.getPhoneNumber());
					preparedStatement.setInt(2, employee.getEmployeeId());
					preparedStatement.executeUpdate();
				} else if (employee.getDate() != null) {
					preparedStatement = connection.prepareStatement(date);
					
					preparedStatement.setDate(1, employee.getDate());
					preparedStatement.setInt(2, employee.getEmployeeId());
					preparedStatement.executeUpdate();
				}
			}
			System.out.println("Data updated in database successfully");
		} catch (SQLException exception) {
			System.out.println("Data not updated");
		}
	}
}
