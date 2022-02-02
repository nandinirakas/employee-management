package com.employee.service;

import com.employee.dao.EmployeeDatabase;
import com.employee.dao.EmployeeDatabaseImpl;
import com.employee.model.Employee;

public class EmployeeManagementImplVersion2 implements EmployeeManagement {
    private final EmployeeDatabase EMPLOYEE_DATABASE = new EmployeeDatabaseImpl();
    
    public void addNewEmployee(Employee employee) {
        EMPLOYEE_DATABASE.addNewEmployee(employee);
    }
    
    public void viewEmployees() {
        System.out.println(EMPLOYEE_DATABASE.getEmployees());
    }
    
    public void deleteEmployee(int employeeId) {
        EMPLOYEE_DATABASE.deleteEmployee(employeeId);
    }
    
    public void updateEmployeeDetails(Employee employee) {
        EMPLOYEE_DATABASE.updateEmployeeDetails(employee);
    }
}
