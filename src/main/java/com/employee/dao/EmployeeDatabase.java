package com.employee.dao;

import java.util.Map;

import com.employee.model.Employee;

public interface EmployeeDatabase {
    void addNewEmployee(Employee employee);
    
    void deleteEmployee(int employeeId);
    
    Map<Integer, Employee> getEmployees();
    
    void updateAllEmployeeDetails(Employee employee);
    
    void updateEmployee(Employee employee);
}
