package com.employee.service;

import java.util.Map;

import com.employee.dao.EmployeeDao;
import com.employee.dao.EmployeeDaoImpl;
import com.employee.exception.CustomException.IdNotFoundException;
import com.employee.exception.CustomException.NoRecordFoundException;
import com.employee.exception.CustomException.IdAlreadyAvailableException;
import com.employee.model.Employee;

/**
 * Database implementation for adding new employee, viewing all employees, delete and update employee.
 */
public class EmployeeManagementImplVersion2 implements EmployeeManagement {
    private static final EmployeeDao EMPLOYEE_DATABASE = new EmployeeDaoImpl();
    
    /**
     * Returns true if any employee is added.
     * 
     * @param employee
     */
    public boolean addNewEmployee(final Employee employee) {
        return EMPLOYEE_DATABASE.addNewEmployee(employee);
    }
    
    /**
     * Returns true if database is not empty.
     */
    public Map<Integer, Employee> getEmployees() {
        final Map<Integer, Employee> employees = EMPLOYEE_DATABASE.getEmployees();
        
        if (!employees.isEmpty()) {
            return employees;
        } 
        throw new NoRecordFoundException("No record found in database !");
    }
    
    /**
     * Returns true if deletion is done by checking id.
     * 
     * @param employeeId
     */
    public boolean deleteEmployee(final int employeeId) {
        
        if (EMPLOYEE_DATABASE.deleteEmployee(employeeId)) {
            return true;
        }
        throw new IdNotFoundException("Id not found!");
    }
    
    /**
     * Returns true if update is done by checking id.
     * 
     * @param employee
     */
    public boolean updateEmployeeDetails(final Employee employee) {
        return EMPLOYEE_DATABASE.updateEmployeeDetails(employee);
    }
    
    /**
     * Check whether employee id is present for addition.
     * 
     * @param employeeId
     */
    public boolean checkEmployeeIdAdd(final int employeeId) {
        
        if (!EMPLOYEE_DATABASE.getEmployees().containsKey(employeeId)) {
            return true;
        }
        throw new IdAlreadyAvailableException("Id already found, enter new id");
    }
    
    /**
     * Check whether employee id is present for updation.
     * 
     * @param employeeId
     */
    public boolean checkEmployeeIdUpdate(final int employeeId) {
        
        if (EMPLOYEE_DATABASE.getEmployees().containsKey(employeeId)) {
            return true;
        }
        throw new IdNotFoundException("Id not found!");
    }
}
