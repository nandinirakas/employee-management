package com.employee.service;

import java.util.Map;

import com.employee.dao.EmployeeDao;
import com.employee.dao.EmployeeDaoImpl;
import com.employee.exception.CustomException.IdAlreadyAvailableException;
import com.employee.exception.CustomException.IdNotFoundException;
import com.employee.model.Employee;

/**
 * Database implementation for adding new employee, viewing all employees, delete and update employee.
 */
public class EmployeeManagementImplVersion2 implements EmployeeManagement {
    private static final EmployeeDao EMPLOYEE_DATABASE = new EmployeeDaoImpl();
    
    public boolean addNewEmployee(final Employee employee) {
        
        if(EMPLOYEE_DATABASE.getEmployees().containsKey(employee.getEmployeeId())) {
            throw new IdAlreadyAvailableException("Id already present, enter new id");
        } else {
            return EMPLOYEE_DATABASE.addNewEmployee(employee);
        }
    }
    
    public Map<Integer, Employee> viewEmployees() {
        return EMPLOYEE_DATABASE.getEmployees();
    }
    
    public boolean deleteEmployee(final int employeeId) {
        final boolean isDeleted = EMPLOYEE_DATABASE.deleteEmployee(employeeId);
        
        if(isDeleted) {
            return true;
        }
        throw new IdNotFoundException("Id not found!");
    }
    
    public boolean updateEmployeeDetails(final Employee employee) {
        final boolean isUpdated = EMPLOYEE_DATABASE.updateEmployeeDetails(employee);
        
        if(isUpdated) {
            return true;
        }
        throw new IdNotFoundException("Id not found!");
    }
}
