package com.employee.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.employee.exception.CustomException.DataNotAddedException;
import com.employee.exception.CustomException.IdNotFoundException;
import com.employee.model.Employee;

/**
 * Enabling insert, update, select and delete in the database using SQL queries. 
 */
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
        } catch (SQLException exception) {
            throw new DataNotAddedException("Data not added");
        }
    }

    public boolean deleteEmployee(final int employeeId) {
        final String deleteQuery = "UPDATE employeedetails set is_deleted = ? WHERE id = ?";
        
        try (Connection connection = DATABASE_CONNECTION.getConnection(); 
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, employeeId);
            
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException exception) {
            throw new IdNotFoundException("Id not present!");
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
            System.out.println(exception);
        } 
        return employees;    
    }
    
    public boolean updateEmployeeDetails(final Employee employee) {
        
        try (Connection connection = DATABASE_CONNECTION.getConnection();
                Statement statement = connection.createStatement();) {
            StringBuffer stringBuffer = new StringBuffer(); 
            String update = stringBuffer.append("UPDATE employeedetails set").toString();
            
            if(employee.getEmployeeId() != 0) { 
                
                if (employee.getEmployeeName() != null) {
                    update = stringBuffer.append(" name = '").append(employee.getEmployeeName()).append("'").toString();
                    update = stringBuffer.append(",").toString();
                }  
                
                if (employee.getSalary() != 0) {
                    update = stringBuffer.append(" salary = ").append(employee.getSalary()).toString();
                    update = stringBuffer.append(",").toString();
                }
                
                if (employee.getPhoneNumber() != null) {
                    update = stringBuffer.append(" number = ").append(employee.getPhoneNumber()).toString();
                    update = stringBuffer.append(",").toString();
                }
                
                if (employee.getDate() != null) {
                    update = stringBuffer.append(" date = '").append(employee.getDate()).append("'").toString();
                update = stringBuffer.append(" WHERE id = ").append(employee.getEmployeeId()).toString();
                statement.executeUpdate(update);
                }
            }
            return true;
        } catch (SQLException exception) {
            throw new IdNotFoundException("Id not present!");
        }
    }
}