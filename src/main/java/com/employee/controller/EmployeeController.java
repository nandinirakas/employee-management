package com.employee.controller;

import java.util.Map;

import com.employee.model.Employee;
import com.employee.service.EmployeeManagement;
import com.employee.service.EmployeeManagementImplVersion2;
//import com.employee.service.EmployeeMangementImpl;
import com.employee.service.Validation;

/**
 *  Get request from main and send response to service.
 */
public class EmployeeController {
    
    //private static final EmployeeManagement EMPLOYEE_SERVICE = new EmployeeMangementImpl();
    private static final EmployeeManagement EMPLOYEE_DATABASE = new EmployeeManagementImplVersion2();
    private static final Validation VALIDATION = new Validation();

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
}