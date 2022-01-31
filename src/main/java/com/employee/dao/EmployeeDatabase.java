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
    private static final String JDBC_URL = "jdbc:postgresql://localhost:2020/employee";
    private static final String DATABASE_NAME = "postgres";
    private static final String DATABASE_PASSWORD = "root123";
    
    private Connection getConnection() {
        Connection connection = null;

        try {
          connection = DriverManager.getConnection(JDBC_URL, DATABASE_NAME, DATABASE_PASSWORD);
        } catch (SQLException exception) {
          System.out.println("Exception");
        }
        return connection;
    }

    public void addNewEmployee(Employee employee) throws SQLException {
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement;
            String addQuery = "INSERT INTO employeedetails (id, name, salary, number, date, is_deleted) values (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(addQuery);
            
            preparedStatement.setInt(1, employee.getEmployeeId());
            preparedStatement.setString(2, employee.getEmployeeName());
            preparedStatement.setDouble(3, employee.getSalary());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setDate(5, employee.getDate());
            preparedStatement.setBoolean(6, false);
            
            preparedStatement.executeUpdate();
            System.out.println("Data entered in database successfully");
        } catch (SQLException exception) {
            System.out.println("Data not entered in database");
        } finally {
            connection.close();
        }
    }

    public void deleteEmployee(int employeeId) throws SQLException {
        Connection connection = getConnection();
        
        try {
            PreparedStatement preparedStatement;
            String deleteQuery = "UPDATE employeedetails set is_deleted = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(deleteQuery);
            
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, employeeId);
            preparedStatement.execute();
            System.out.println("Data deleted in database successfully");
        } catch (SQLException exception) {
            System.out.println("Data not deleted in database");
        } finally {
            connection.close();
        }
    }
    
    public Map<Integer, Employee> getEmployees() throws SQLException {
        final Map<Integer, Employee> employees = new HashMap<>(); 
        Connection connection = getConnection();
        
        try {
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            String selectQuery = "SELECT * FROM employeedetails WHERE is_deleted = false";
            preparedStatement = connection.prepareStatement(selectQuery);
            
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
            System.out.println("Cannot view datas in database");
        } finally {
            connection.close();
        }
        return employees;    
    }
    
    public void updateAllEmployeeDetails(Employee employee) throws SQLException {
        Connection connection = getConnection();
        
        try {
            String updateQuery = "UPDATE employeedetails set name = ? and salary = ? and number = ? and date = ? WHERE id = ?";
            
            if(employee.getEmployeeId() != 0) {
                PreparedStatement preparedStatement;
                
                if (employee.getEmployeeName() != null && employee.getSalary() != 0 && employee.getPhoneNumber() != null && employee.getDate() != null) {
                    preparedStatement = connection.prepareStatement(updateQuery);
                    
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
            System.out.println("Data not updated in database");
        } finally {
            connection.close();
        }
    }
    
    public void updateEmployee(Employee employee) throws SQLException {
        Connection connection = getConnection();
        
        try {
            String updateName = "UPDATE employeedetails set name = ? WHERE id = ?";
            String updateSalary = "UPDATE employeedetails set salary = ? WHERE id = ?";
            String updateNumber = "UPDATE employeedetails set number = ? WHERE id = ?";
            String updateDate = "UPDATE employeedetails set date = ? WHERE id = ?";
            
            if(employee.getEmployeeId() != 0) {
                PreparedStatement preparedStatement;
                
                if (employee.getEmployeeName() != null) {
                    preparedStatement = connection.prepareStatement(updateName);
                    
                    preparedStatement.setString(1, employee.getEmployeeName());
                    preparedStatement.setInt(2, employee.getEmployeeId());
                    preparedStatement.executeUpdate();
                } else if (employee.getSalary() != 0) {
                    preparedStatement = connection.prepareStatement(updateSalary);
                    
                    preparedStatement.setDouble(1, employee.getSalary());
                    preparedStatement.setInt(2, employee.getEmployeeId());
                    preparedStatement.executeUpdate();
                } else if (employee.getPhoneNumber() != null) {
                    preparedStatement = connection.prepareStatement(updateNumber);
                    
                    preparedStatement.setString(1, employee.getPhoneNumber());
                    preparedStatement.setInt(2, employee.getEmployeeId());
                    preparedStatement.executeUpdate();
                } else if (employee.getDate() != null) {
                    preparedStatement = connection.prepareStatement(updateDate);
                    
                    preparedStatement.setDate(1, employee.getDate());
                    preparedStatement.setInt(2, employee.getEmployeeId());
                    preparedStatement.executeUpdate();
                }
            }
            System.out.println("Data updated in database successfully");
        } catch (SQLException exception) {
            System.out.println("Data not updated in database");
        } finally {
            connection.close();
        }
    }
}