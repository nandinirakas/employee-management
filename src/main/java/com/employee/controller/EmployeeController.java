package com.employee.controller;

import java.util.Map;

import com.employee.model.Employee;
import com.employee.service.EmployeeManagementImplVersion2;
//import com.employee.service.EmployeeMangementImpl;
import com.employee.service.Validation;

/**
 *  Get request from main and send response to service.
 */
public class EmployeeController {
    
    //private static final EmployeeManagement EMPLOYEE_SERVICE = new EmployeeMangementImpl();
    private static final EmployeeManagementImplVersion2 EMPLOYEE_DATABASE = new EmployeeManagementImplVersion2();
    private static final Validation VALIDATION = new Validation();

    public static boolean addNewEmployee(final Employee employee) {
        return EMPLOYEE_DATABASE.addNewEmployee(employee);
        //EMPLOYEE_SERVICE.addNewEmployee(employee);
    }

    public static Map<Integer, Employee> getEmployees() {
        return EMPLOYEE_DATABASE.getEmployees();
        //EMPLOYEE_SERVICE.getEmployees();
    }

    public static boolean deleteEmployee(final int employeeId) {
        return EMPLOYEE_DATABASE.deleteEmployee(employeeId);
        //EMPLOYEE_SERVICE.deleteEmployee(employeeId);
    }
    
    public static boolean updateEmployeeDetails(final Employee employee) {
        return EMPLOYEE_DATABASE.updateEmployeeDetails(employee);
        //EMPLOYEE_SERVICE.updateEmployeeDetails(employee);
    }
    
    public static boolean employeeIdValidation(final String employeeId) {
        return VALIDATION.employeeIdValidation(employeeId);
    }
    
    public static boolean employeeNameValidation(final String employeeName) {
        return VALIDATION.employeeNameValidation(employeeName);
    }

    public static boolean employeeSalaryValidation(final String employeeSalary) {
        return VALIDATION.employeeSalaryValidation(employeeSalary);
    }
       
    public static boolean phoneNumberValidation(final String phoneNumber) {
        return VALIDATION.phoneNumberValidation(phoneNumber);
    }
   
    public static boolean dateValidation(final String joiningDate) {
        return VALIDATION.dateValidation(joiningDate);
    }
    
    public static boolean validateChoice(final String choice) {
        return VALIDATION.validateChoice(choice);
    }
    
    public static boolean checkEmployeeIdAdd(final int employeeId) {
        return EMPLOYEE_DATABASE.checkEmployeeIdAdd(employeeId);
    }
    
    public static boolean checkEmployeeIdUpdate(final int employeeId) {
        return EMPLOYEE_DATABASE.checkEmployeeIdUpdate(employeeId);
    }
}