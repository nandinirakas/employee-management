package com.employee.service;

import com.employee.model.Employee;

/**
 * Used to add new employee by using employee id, view all employees, delete an employee data by 
 * using employee id, and update by using employee.
 */
public interface EmployeeManagement {
    void addNewEmployee(Employee employee);

    void viewEmployees();

    void deleteEmployee(int employeeId);

    void updateEmployee(Employee employee);
	
    void updateAllEmployeeDetails(Employee employee);
}
