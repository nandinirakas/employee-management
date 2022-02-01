package com.employee.service;

import com.employee.dao.EmployeeDatabaseImpl;
import com.employee.model.Employee;

public class EmployeeManagementImplVersion2 {
    private final EmployeeDatabaseImpl EMPLOYEE_DATABASE = new EmployeeDatabaseImpl();
    
    public void addNewEmployee(Employee employee) {
        EMPLOYEE_DATABASE.addNewEmployee(employee);
    }
    
    public void viewEmployees() {
        EMPLOYEE_DATABASE.getEmployees();
    }
    
    public void deleteEmployee(int employeeId) {
        EMPLOYEE_DATABASE.deleteEmployee(employeeId);
    }
    
    public void updateEmployee(Employee employee) {
        EMPLOYEE_DATABASE.updateEmployee(employee);
    }
    
    public void updateAllEmployeeDetails(Employee employee) {
        EMPLOYEE_DATABASE.updateAllEmployeeDetails(employee);
    }
}
