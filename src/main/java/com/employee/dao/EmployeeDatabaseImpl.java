package com.employee.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.employee.model.Employee;

public class EmployeeDatabaseImpl implements EmployeeDatabase {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection(); 
    
    public void addNewEmployee(final Employee employee) {
        final String addQuery = "INSERT INTO employeedetails (id, name, salary, number, date, is_deleted) values (?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DATABASE_CONNECTION.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(addQuery);) {
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
        }
    }

    public void deleteEmployee(final int employeeId) {
        final String deleteQuery = "UPDATE employeedetails set is_deleted = ? WHERE id = ?";
        
        try (Connection connection = DATABASE_CONNECTION.getConnection(); 
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, employeeId);
            preparedStatement.execute();
            System.out.println("Data deleted in database successfully");
        } catch (SQLException exception) {
            System.out.println("Data not deleted in database");
        }
    }
    
    public Map<Integer, Employee> getEmployees() {
        final Map<Integer, Employee> employees = new HashMap<>(); 
        String selectQuery = "SELECT * FROM employeedetails WHERE is_deleted = false";

        try (Connection connection = DATABASE_CONNECTION.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                ResultSet resultSet = preparedStatement.executeQuery();){
            
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
            System.out.println("Cannot view data in database");
        } 
        return employees;    
    }
    
    public void updateEmployeeDetails(final Employee employee) {
        
        try (Connection connection = DATABASE_CONNECTION.getConnection();
                Statement statement = connection.createStatement();) {
            String update = "UPDATE employeedetails set";
            
            if(employee.getEmployeeId() != 0) { 
                
                if (employee.getEmployeeName() != null) {
                    update = update + " name = '" + employee.getEmployeeName() + "'";
                    update = update + ",";
                }  
                
                if (employee.getSalary() != 0) {
                    update = update + " salary = " + employee.getSalary();
                    update = update + ",";
                }
                
                if (employee.getPhoneNumber() != null) {
                    update = update + " number = " + employee.getPhoneNumber();
                    update = update + ",";
                }
                
                if (employee.getDate() != null) {
                    update = update + " date = '" + employee.getDate() + "'";
                }
                update = update + " WHERE id = " + employee.getEmployeeId();
                statement.executeUpdate(update);
            }
            System.out.println("Data updated in database successfully");
        } catch (SQLException exception) {
            System.out.println("Data not updated in database");
        }
    }
}