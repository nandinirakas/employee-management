package com.employee.service;

import com.employee.dao.EmployeeDatabase;
import com.employee.dao.EmployeeDatabaseImpl;
import com.employee.exception.CustomException.IdAlreadyAvailableException;
import com.employee.exception.CustomException.IdNotFoundException;
import com.employee.model.Employee;

public class EmployeeManagementImplVersion2 implements EmployeeManagement {
    private final EmployeeDatabase EMPLOYEE_DATABASE = new EmployeeDatabaseImpl();
    
    public void addNewEmployee(final Employee employee) {
        
        if(EMPLOYEE_DATABASE.getEmployees().containsKey(employee.getEmployeeId())) {
            throw new IdAlreadyAvailableException("Id already present, enter new id");
        } else {
            EMPLOYEE_DATABASE.addNewEmployee(employee); 
            System.out.println("Data entered in database successfully");
        }
    }
    
    public void viewEmployees() {
        System.out.println(EMPLOYEE_DATABASE.getEmployees());
    }
    
    public void deleteEmployee(int employeeId) {
        boolean isDeleted = EMPLOYEE_DATABASE.deleteEmployee(employeeId);
        
        if(isDeleted) {
            System.out.println("Data deleted in database successfully");
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
    
    public void updateEmployeeDetails(Employee employee) {
        boolean isUpdated = EMPLOYEE_DATABASE.updateEmployeeDetails(employee);
        
        if(isUpdated) {
            System.out.println("Data updated in database successfully");
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
