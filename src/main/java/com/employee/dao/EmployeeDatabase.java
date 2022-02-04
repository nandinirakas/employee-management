package com.employee.dao;

import java.util.Map;

import com.employee.model.Employee;

public interface EmployeeDatabase {
    boolean addNewEmployee(final Employee employee);
    
    boolean deleteEmployee(final int employeeId);
    
    Map<Integer, Employee> getEmployees();
    
    boolean updateEmployeeDetails(final Employee employee);
}
