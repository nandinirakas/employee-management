package com.employee.service;

import java.util.Map;

import com.employee.dao.EmployeeDao;
import com.employee.dao.EmployeeDaoImpl;
import com.employee.model.Employee;

/**
 * Database implementation for adding new employee, viewing all employees, delete and update employee.
 */
public class EmployeeManagementImplVersion2 implements EmployeeManagement {
    private final EmployeeDao EMPLOYEE_DATABASE = new EmployeeDaoImpl();
    
    public boolean addNewEmployee(final Employee employee) {
        return EMPLOYEE_DATABASE.addNewEmployee(employee);
    }
    
    public Map<Integer, Employee> viewEmployees() {
        return EMPLOYEE_DATABASE.getEmployees();
    }
    
    public boolean deleteEmployee(final int employeeId) {
        return EMPLOYEE_DATABASE.deleteEmployee(employeeId);
    }
    
    public boolean updateEmployeeDetails(final Employee employee) {
        return EMPLOYEE_DATABASE.updateEmployeeDetails(employee);
    }
}
