package com.employee.controller;

import java.sql.Date;

import com.employee.model.Employee;
import com.employee.service.EmployeeManagement;
import com.employee.service.EmployeeManagementImplVersion2;
//import com.employee.service.EmployeeMangementImpl;
import com.employee.service.Validation;
import com.employee.view.EmployeeInformation;

public class EmployeeController {
    //private final EmployeeManagement EMPLOYEE_SERVICE = new EmployeeMangementImpl();
    private final EmployeeManagement EMPLOYEE_DATABASE = new EmployeeManagementImplVersion2();
    private final static Validation VALIDATION = new Validation();

    public void addNewEmployee(final Employee employee) {
        EMPLOYEE_DATABASE.addNewEmployee(employee);
        //EMPLOYEE_SERVICE.addNewEmployee(employee);
    }

    public void viewEmployees() {
        EMPLOYEE_DATABASE.viewEmployees();
        //EMPLOYEE_SERVICE.viewEmployees();
    }

    public void deleteEmployee(final int employeeId) {
        EMPLOYEE_DATABASE.deleteEmployee(employeeId);
        //EMPLOYEE_SERVICE.deleteEmployee(employeeId);
    }
    
    public void updateEmployeeDetails(final Employee employee) {
        EMPLOYEE_DATABASE.updateEmployeeDetails(employee);
        //EMPLOYEE_SERVICE.updateEmployeeDetails(employee);
    }
    
    public static int employeeIdValidation(final String employeeId) {
        return VALIDATION.employeeIdValidation(employeeId);
    }
    
    public static int failedEmployeeIdValidation(final String employeeId) {
        return EmployeeInformation.getEmployeeId();
    }
    
    public static String employeeNameValidation(final String employeeName) {
        return VALIDATION.employeeNameValidation(employeeName);
    }
    
    public static String failedEmployeeNameValidation(final String employeeName) {
        return EmployeeInformation.getEmployeeName();
    }

    public static double employeeSalaryValidation(final String employeeSalary) {
        return VALIDATION.employeeSalaryValidation(employeeSalary);
    }
    
    public static double failedEmployeeSalaryValidation(final String employeeSalary) {
        return EmployeeInformation.getEmployeeSalary();
    }
    
    public static String phoneNumberValidation(final String phoneNumber) {
        return VALIDATION.phoneNumberValidation(phoneNumber);
    }
    
    public static String failedPhoneNumberValidation(final String phoneNumber) {
        return EmployeeInformation.getEmployeePhoneNumber();
    }
    
    public static Date dateValidation(final String joiningDate) {
        return VALIDATION.dateValidation(joiningDate);
    }
    
    public static Date failedDateValidation(final String joiningDate) {
        return EmployeeInformation.getEmployeeJoiningDate();
    }
}