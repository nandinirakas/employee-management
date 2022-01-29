package com.employee.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.employee.model.Employee;

public class EmployeeDatabase {
    private final String JDBCURL = "jdbc:postgresql://localhost:2020/employee";
    private final String DATABASE_NAME = "postgres";
    private final String DATABASE_PASSWORD = "root123";
	
    private Connection getConnection() {
        Connection connection = null;

        try {
          connection = DriverManager.getConnection(JDBCURL, DATABASE_NAME, DATABASE_PASSWORD);
        } catch (SQLException exception) {
          System.out.println("Exception");
        }
        return connection;
    }

    public void addNewEmployee(Employee employee) {

        try {
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
            System.out.println("Employee id already present, please enter new id");
        }
    }

    public void deleteEmployee(int employeeId) {
		
        try {
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
	
    public Map<Integer, Employee> getEmployees() {
        final Map<Integer, Employee> employees = new HashMap<>(); 
		
        try {
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
				 
                 Employee employee = new Employee(id, name, salary, number, date);         
                 employees.put(id, employee);
                 }
        } catch (SQLException exception) {
            System.out.println("Cannot view employee details");
        }
        return employees;    
    }
    
    public void updateAllEmployeeDetails(Employee employee) throws SQLException {
        Connection connection = getConnection();
        
        try {
            String update = "UPDATE employeedetails set name = ? and salary = ? and number = ? and date = ? WHERE id = ?";
            
            if(employee.getEmployeeId() != 0) {
                PreparedStatement preparedStatement;
                
                if (employee.getEmployeeName() != null && employee.getSalary() != 0 && employee.getPhoneNumber() != null && employee.getDate() != null) {
                    preparedStatement = connection.prepareStatement(update);
                    
                    preparedStatement.setString(1, employee.getEmployeeName());
                    preparedStatement.setDouble(2, employee.getSalary());
                    preparedStatement.setString(3, employee.getPhoneNumber());
                    preparedStatement.setDate(4, employee.getDate());
                    preparedStatement.setInt(5, employee.getEmployeeId());
                    preparedStatement.executeUpdate();
                }
            }
            System.out.println("Data updated in database successfully");
        } catch (SQLException exception) {
            System.out.println("Data not updated");
        } finally {
            connection.close();
        }
    }
	
    public void updateEmployee(Employee employee) {
		
        try {
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