package com.employee.service;

import com.employee.dao.EmployeeDatabase;
import com.employee.dao.EmployeeDatabaseImpl;
import com.employee.exception.IdAlreadyAvailableException;
import com.employee.exception.IdNotFoundException;
import com.employee.model.Employee;

public class EmployeeManagementImplVersion2 implements EmployeeManagement {
    private final EmployeeDatabase EMPLOYEE_DATABASE = new EmployeeDatabaseImpl();
    
    public void addNewEmployee(Employee employee) throws IdAlreadyAvailableException {
        
        if(EMPLOYEE_DATABASE.getEmployees().containsKey(employee.getEmployeeId())) {
            throw new IdAlreadyAvailableException("Id already present, enter new id");
        } else {
            EMPLOYEE_DATABASE.addNewEmployee(employee); 
        }
    }
    
    public void viewEmployees() {
        System.out.println(EMPLOYEE_DATABASE.getEmployees());
    }
    
    public void deleteEmployee(int employeeId) throws IdNotFoundException {
        
        if(EMPLOYEE_DATABASE.getEmployees().containsKey(employeeId)) {
            EMPLOYEE_DATABASE.deleteEmployee(employeeId);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
    
    public void updateEmployeeDetails(Employee employee) throws IdNotFoundException {
        
        if(EMPLOYEE_DATABASE.getEmployees().containsKey(employee.getEmployeeId())) {
            EMPLOYEE_DATABASE.updateEmployeeDetails(employee);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
