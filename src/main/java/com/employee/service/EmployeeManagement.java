package com.employee.service;

import java.sql.SQLException;

import com.employee.model.Employee;

/**
 * Used to add new employee by using employee id, view all employees, delete an employee data by 
 * using employee id, and update by using employee.
 */
public interface EmployeeManagement {
    void addNewEmployee(Employee employee) throws SQLException;

    void viewEmployees() throws SQLException;

    void deleteEmployee(int employeeId) throws SQLException;

    void updateEmployee(Employee employee) throws SQLException;
    
    void updateAllEmployeeDetails(Employee employee) throws SQLException;
}