package com.employee.controller;

import java.sql.Date;

import com.employee.exception.IdNotFoundException;
import com.employee.model.Employee;
import com.employee.service.EmployeeManagementImplVersion2;
import com.employee.service.EmployeeMangementImpl;
import com.employee.view.EmployeeInformation;

public class EmployeeController {
    private final static EmployeeMangementImpl EMPLOYEE_SERVICE = new EmployeeMangementImpl();
    private final static EmployeeManagementImplVersion2 EMPLOYEE_DATABASE = new EmployeeManagementImplVersion2();

    public void addNewEmployee(Employee employee) {
        EMPLOYEE_SERVICE.addNewEmployee(employee);
        EMPLOYEE_DATABASE.addNewEmployee(employee);
    }

    public void viewEmployees() {
        EMPLOYEE_SERVICE.viewEmployees();
        EMPLOYEE_DATABASE.viewEmployees();
    }

    public void deleteEmployee(int employeeId) throws IdNotFoundException {
        EMPLOYEE_SERVICE.deleteEmployee(employeeId);
        EMPLOYEE_DATABASE.deleteEmployee(employeeId);
    }
    
    public void updateEmployeeDetails(Employee employee) {
        EMPLOYEE_SERVICE.updateEmployeeDetails(employee);
        EMPLOYEE_DATABASE.updateEmployeeDetails(employee);
    }
    
    public static int employeeIdValidation(String employeeId) {
        return EMPLOYEE_SERVICE.employeeIdValidation(employeeId);
    }
    
    public static int failedEmployeeIdValidation(String employeeId) {
        return EmployeeInformation.getEmployeeId();
    }
    
    public static String employeeNameValidation(String employeeName) {
        return EMPLOYEE_SERVICE.employeeNameValidation(employeeName);
    }
    
    public static String failedEmployeeNameValidation(String employeeName) {
        return EmployeeInformation.getEmployeeName();
    }

    public static double employeeSalaryValidation(String employeeSalary) {
        return EMPLOYEE_SERVICE.employeeSalaryValidation(employeeSalary);
    }
    
    public static double failedEmployeeSalaryValidation(String employeeSalary) {
        return EmployeeInformation.getEmployeeSalary();
    }
    
    public static String phoneNumberValidation(String phoneNumber) {
        return EMPLOYEE_SERVICE.phoneNumberValidation(phoneNumber);
    }
    
    public static String failedPhoneNumberValidation(String phoneNumber) {
        return EmployeeInformation.getEmployeePhoneNumber();
    }
    
    public static Date dateValidation(String joiningDate) {
        return EMPLOYEE_SERVICE.dateValidation(joiningDate);
    }
    
    public static Date failedDateValidation(String joiningDate) {
        return EmployeeInformation.getEmployeeJoiningDate();
    }
}