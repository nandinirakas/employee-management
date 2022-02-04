package com.employee.service;

import com.employee.dao.EmployeeDatabase;
import com.employee.dao.EmployeeDatabaseImpl;
import com.employee.exception.CustomException.DataNotAddedException;
import com.employee.exception.CustomException.IdNotFoundException;
import com.employee.model.Employee;

/**
 * Database implementation for adding new employee, viewing all employees, delete and update employee.
 */
public class EmployeeManagementImplVersion2 implements EmployeeManagement {
    private final EmployeeDatabase EMPLOYEE_DATABASE = new EmployeeDatabaseImpl();
    
    public void addNewEmployee(final Employee employee) {
        boolean isAdded = EMPLOYEE_DATABASE.addNewEmployee(employee);
        
        if(isAdded) {
            System.out.println("Data added in database successfully");
        } else {
            throw new DataNotAddedException("Data not added");
        }
    }
    
    public void viewEmployees() {
        System.out.println(EMPLOYEE_DATABASE.getEmployees());
    }
    
    public void deleteEmployee(final int employeeId) {
        boolean isDeleted = EMPLOYEE_DATABASE.deleteEmployee(employeeId);
        
        if(isDeleted) {
            System.out.println("Data deleted in database successfully");
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
    
    public void updateEmployeeDetails(final Employee employee) {
        boolean isUpdated = EMPLOYEE_DATABASE.updateEmployeeDetails(employee);
        
        if(isUpdated) {
            System.out.println("Data updated in database successfully");
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
