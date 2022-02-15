package com.employee.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.employee.model.Employee;
import com.employee.exception.CustomException.AccessFailedException;

/**
 * Enabling insert, update, select and delete in the database using SQL queries. 
 */
public class EmployeeDaoImpl implements EmployeeDao {
    
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection(); 
    
    public boolean addNewEmployee(final Employee employee) {
        final String addQuery = "INSERT INTO employeedetails (id, name, salary, number, date, is_deleted) values (?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DATABASE_CONNECTION.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(addQuery);) {
            preparedStatement.setInt(1, employee.getEmployeeId());
            preparedStatement.setString(2, employee.getEmployeeName());
            preparedStatement.setDouble(3, employee.getSalary());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setDate(5, employee.getDate());
            preparedStatement.setBoolean(6, false);
            
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            throw new AccessFailedException("Database access failed!!");
        }
    }

    public boolean deleteEmployee(final int employeeId) {
        final String deleteQuery = "UPDATE employeedetails set is_deleted = ? WHERE id = ? and is_deleted = ?";
        
        try (Connection connection = DATABASE_CONNECTION.getConnection(); 
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, employeeId);
            preparedStatement.setBoolean(3, false);
            
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            throw new AccessFailedException("Database access failed!!");
        }
    }
    
    public Map<Integer, Employee> getEmployees() {
        final Map<Integer, Employee> employees = new HashMap<>(); 
        String selectQuery = "SELECT id, name, salary, number, date FROM employeedetails WHERE is_deleted = false";

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
            throw new AccessFailedException("Database access failed!!");
        } 
        return employees;    
    }
    
    public boolean updateEmployeeDetails(final Employee employee) {
        
        try (Connection connection = DATABASE_CONNECTION.getConnection();) {
            final StringBuffer updateQueryBuffer = new StringBuffer(); 
            updateQueryBuffer.append("UPDATE employeedetails set");
            boolean hasNextValue = false;
            int name = 1, salary = 1, number = 1, date = 1, id = 1, count = 0;
            
            if(employee.getEmployeeId() != 0) { 
                
                if (employee.getEmployeeName() != null) {
                    updateQueryBuffer.append(" name = ?");
                    hasNextValue = true;
                    count += 1;
                }  
                
                if (employee.getSalary() != 0) {
                    
                    if (hasNextValue) {
                        updateQueryBuffer.append(",");
                    }
                    updateQueryBuffer.append(" salary = ?");
                    hasNextValue = true;
                    salary = count + 1;
                    count += 1;
                }
                
                if (employee.getPhoneNumber() != null) {
                    
                    if (hasNextValue) {
                        updateQueryBuffer.append(",");
                    }
                    updateQueryBuffer.append(" number = ?");
                    hasNextValue = true;
                    number = count + 1;
                    count += 1;
                }
                
                if (employee.getDate() != null) {
                    
                    if (hasNextValue) {
                        updateQueryBuffer.append(",");
                    }
                    updateQueryBuffer.append(" date = ?");
                    date = count + 1;
                    count += 1;
                }
            }
            updateQueryBuffer.append(" WHERE id = ?");
            id = count + 1;
            PreparedStatement preparedStatement = connection.prepareStatement(updateQueryBuffer.toString());
            
            if (employee.getEmployeeName() != null) {
                preparedStatement.setString(name, employee.getEmployeeName());
            }  
            
            if (employee.getSalary() != 0) {
                preparedStatement.setDouble(salary, employee.getSalary());
            }
            
            if (employee.getPhoneNumber() != null) {
                preparedStatement.setString(number, employee.getPhoneNumber());
            }
            
            if (employee.getDate() != null) {
                preparedStatement.setDate(date, employee.getDate());
            } 
            preparedStatement.setInt(id, employee.getEmployeeId());
            return preparedStatement.executeUpdate() > 0 ;
        } catch (SQLException exception) {
            throw new AccessFailedException("Database access failed!!");
        }
    }
}