package com.employee.controller;

import java.sql.Date;
import java.util.Map;

import com.employee.model.Employee;
import com.employee.service.EmployeeManagement;
import com.employee.service.EmployeeManagementImplVersion2;
//import com.employee.service.EmployeeMangementImpl;
import com.employee.service.Validation;
import com.employee.view.EmployeeInformation;

/**
 *  Get request from main and send response to service.
 */
public class EmployeeController {
    //private final EmployeeManagement EMPLOYEE_SERVICE = new EmployeeMangementImpl();
    private final EmployeeManagement EMPLOYEE_DATABASE = new EmployeeManagementImplVersion2();
    private final static Validation VALIDATION = new Validation();

    public boolean addNewEmployee(final Employee employee) {
        return EMPLOYEE_DATABASE.addNewEmployee(employee);
        //EMPLOYEE_SERVICE.addNewEmployee(employee);
    }

    public Map<Integer, Employee> viewEmployees() {
        return EMPLOYEE_DATABASE.viewEmployees();
        //EMPLOYEE_SERVICE.viewEmployees();
    }

    public boolean deleteEmployee(final int employeeId) {
        return EMPLOYEE_DATABASE.deleteEmployee(employeeId);
        //EMPLOYEE_SERVICE.deleteEmployee(employeeId);
    }
    
    public boolean updateEmployeeDetails(final Employee employee) {
        return EMPLOYEE_DATABASE.updateEmployeeDetails(employee);
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