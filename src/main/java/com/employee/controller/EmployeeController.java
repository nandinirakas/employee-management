package com.employee.controller;

import java.sql.Date;

import com.employee.exception.IdAlreadyAvailableException;
import com.employee.exception.IdNotFoundException;
import com.employee.model.Employee;
import com.employee.service.EmployeeManagement;
import com.employee.service.EmployeeManagementImplVersion2;
import com.employee.service.EmployeeMangementImpl;
import com.employee.service.Validation;
import com.employee.view.EmployeeInformation;

public class EmployeeController {
    private final static EmployeeManagement EMPLOYEE_SERVICE = new EmployeeMangementImpl();
    private final static EmployeeManagement EMPLOYEE_DATABASE = new EmployeeManagementImplVersion2();
    private final static Validation VALIDATION = new Validation();

    public void addNewEmployee(Employee employee) throws IdAlreadyAvailableException {
        EMPLOYEE_DATABASE.addNewEmployee(employee);
        EMPLOYEE_SERVICE.addNewEmployee(employee);
    }

    public void viewEmployees() {
        EMPLOYEE_DATABASE.viewEmployees();
        EMPLOYEE_SERVICE.viewEmployees();
    }

    public void deleteEmployee(int employeeId) throws IdNotFoundException {
        EMPLOYEE_DATABASE.deleteEmployee(employeeId);
        EMPLOYEE_SERVICE.deleteEmployee(employeeId);
    }
    
    public void updateEmployeeDetails(Employee employee) throws IdNotFoundException {
        EMPLOYEE_DATABASE.updateEmployeeDetails(employee);
        EMPLOYEE_SERVICE.updateEmployeeDetails(employee);
    }
    
    public static int employeeIdValidation(String employeeId) {
        return VALIDATION.employeeIdValidation(employeeId);
    }
    
    public static int failedEmployeeIdValidation(String employeeId) {
        return EmployeeInformation.getEmployeeId();
    }
    
    public static String employeeNameValidation(String employeeName) {
        return VALIDATION.employeeNameValidation(employeeName);
    }
    
    public static String failedEmployeeNameValidation(String employeeName) {
        return EmployeeInformation.getEmployeeName();
    }

    public static double employeeSalaryValidation(String employeeSalary) {
        return VALIDATION.employeeSalaryValidation(employeeSalary);
    }
    
    public static double failedEmployeeSalaryValidation(String employeeSalary) {
        return EmployeeInformation.getEmployeeSalary();
    }
    
    public static String phoneNumberValidation(String phoneNumber) {
        return VALIDATION.phoneNumberValidation(phoneNumber);
    }
    
    public static String failedPhoneNumberValidation(String phoneNumber) {
        return EmployeeInformation.getEmployeePhoneNumber();
    }
    
    public static Date dateValidation(String joiningDate) {
        return VALIDATION.dateValidation(joiningDate);
    }
    
    public static Date failedDateValidation(String joiningDate) {
        return EmployeeInformation.getEmployeeJoiningDate();
    }
}