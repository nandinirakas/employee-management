package com.employee.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.employee.model.Employee;

public class EmployeeDatabaseImpl {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection(); 
    
    public void addNewEmployee(Employee employee) {
        final String addQuery = "INSERT INTO employeedetails (id, name, salary, number, date, is_deleted) values (?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DATABASE_CONNECTION.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(addQuery);) {
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

    public void deleteEmployee(int employeeId) {
        final String deleteQuery = "UPDATE employeedetails set is_deleted = ? WHERE id = ?";
        
        try (final Connection connection = DATABASE_CONNECTION.getConnection(); 
                final PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);) {
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
        final String selectQuery = "SELECT * FROM employeedetails WHERE is_deleted = false";
        
        try (final Connection connection = DATABASE_CONNECTION.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);) {
            preparedStatement.execute();
            final ResultSet resultSet = preparedStatement.executeQuery();  
            
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
    
    public void updateAllEmployeeDetails(Employee employee) {
        final String updateQuery = "UPDATE employeedetails set name = ? and salary = ? and number = ? and date = ? WHERE id = ?";
        
        try (final Connection connection = DATABASE_CONNECTION.getConnection();
                final PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);) {
            preparedStatement.setString(1, employee.getEmployeeName());
            preparedStatement.setDouble(2, employee.getSalary());
            preparedStatement.setString(3, employee.getPhoneNumber());
            preparedStatement.setDate(4, employee.getDate());
            preparedStatement.setInt(5, employee.getEmployeeId());
            preparedStatement.executeUpdate();
            System.out.println("Data updated in database successfully");
        } catch (SQLException exception) {
            System.out.println("Data not updated in database");
        }
    }
    
    public void updateEmployee(Employee employee) {
        
        try (final Connection connection = DATABASE_CONNECTION.getConnection();) {
            String updateName = "UPDATE employeedetails set name = ? WHERE id = ?";
            String updateSalary = "UPDATE employeedetails set salary = ? WHERE id = ?";
            String updateNumber = "UPDATE employeedetails set number = ? WHERE id = ?";
            String updateDate = "UPDATE employeedetails set date = ? WHERE id = ?";
            
            if(employee.getEmployeeId() != 0) {
                
                if (employee.getEmployeeName() != null) {
                    final PreparedStatement preparedStatement = connection.prepareStatement(updateName);
                    
                    preparedStatement.setString(1, employee.getEmployeeName());
                    preparedStatement.setInt(2, employee.getEmployeeId());
                    preparedStatement.executeUpdate();
                } else if (employee.getSalary() != 0) {
                    final PreparedStatement preparedStatement = connection.prepareStatement(updateSalary);
                    
                    preparedStatement.setDouble(1, employee.getSalary());
                    preparedStatement.setInt(2, employee.getEmployeeId());
                    preparedStatement.executeUpdate();
                } else if (employee.getPhoneNumber() != null) {
                    final PreparedStatement  preparedStatement = connection.prepareStatement(updateNumber);
                    
                    preparedStatement.setString(1, employee.getPhoneNumber());
                    preparedStatement.setInt(2, employee.getEmployeeId());
                    preparedStatement.executeUpdate();
                } else if (employee.getDate() != null) {
                    final PreparedStatement preparedStatement = connection.prepareStatement(updateDate);
                    
                    preparedStatement.setDate(1, employee.getDate());
                    preparedStatement.setInt(2, employee.getEmployeeId());
                    preparedStatement.executeUpdate();
                }
            }
            System.out.println("Data updated in database successfully");
        } catch (SQLException exception) {
            System.out.println("Data not updated in database");
        }
    }
}