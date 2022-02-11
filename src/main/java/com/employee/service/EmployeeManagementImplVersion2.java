package com.employee.service;

import java.util.Map;

import com.employee.dao.EmployeeDao;
import com.employee.dao.EmployeeDaoImpl;
import com.employee.exception.CustomException.IdNotFoundException;
import com.employee.exception.CustomException.NoRecordFoundException;
import com.employee.model.Employee;

/**
 * Database implementation for adding new employee, viewing all employees, delete and update employee.
 */
public class EmployeeManagementImplVersion2 implements EmployeeManagement {
    private static final EmployeeDao EMPLOYEE_DATABASE = new EmployeeDaoImpl();
    
    public boolean addNewEmployee(final Employee employee) {
        return EMPLOYEE_DATABASE.addNewEmployee(employee);
    }
    
    public Map<Integer, Employee> viewEmployees() {
        final Map<Integer, Employee> employees = EMPLOYEE_DATABASE.getEmployees();
        
        if (!employees.isEmpty()) {
            return employees;
        } 
        throw new NoRecordFoundException("No record found in database !");
    }
    
    public boolean deleteEmployee(final int employeeId) {
        final boolean isDeleted = EMPLOYEE_DATABASE.deleteEmployee(employeeId);
        
        if(isDeleted) {
            return true;
        }
        throw new IdNotFoundException("Id not found!");
    }
    
    public boolean updateEmployeeDetails(final Employee employee) {
        return EMPLOYEE_DATABASE.updateEmployeeDetails(employee);
    }
    
    public boolean checkEmployeeId(final int employeeId) {
        final boolean isEmployeeIdPresent = EMPLOYEE_DATABASE.getEmployees().containsKey(employeeId);
        
        if(!isEmployeeIdPresent) {
            return true;
        }
        throw new IdNotFoundException("Id already found, enter new id");
    }
    
    public boolean checkEmployeeIdUpdate(final int employeeId) {
        final boolean isEmployeeIdPresent = EMPLOYEE_DATABASE.getEmployees().containsKey(employeeId);
        
        if(isEmployeeIdPresent) {
            return true;
        }
        throw new IdNotFoundException("Id not found!");
    }
}
