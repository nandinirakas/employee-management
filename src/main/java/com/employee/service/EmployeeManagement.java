package com.employee.service;

import java.util.Map;

import com.employee.model.Employee;

/**
 * Used to add new employee by using employee id, view all employees, delete an employee data by 
 * using employee id, and update by using employee.
 */
public interface EmployeeManagement {   
    boolean addNewEmployee(final Employee employee);

    Map<Integer, Employee> viewEmployees();

    boolean deleteEmployee(final int employeeId);

    boolean updateEmployeeDetails(final Employee employee);
}