package com.employee.dao;

import java.util.Map;

import com.employee.model.Employee;

public interface EmployeeDatabase {
    void addNewEmployee(Employee employee);
    
    boolean deleteEmployee(int employeeId);
    
    Map<Integer, Employee> getEmployees();
    
    boolean updateEmployeeDetails(Employee employee);
}
