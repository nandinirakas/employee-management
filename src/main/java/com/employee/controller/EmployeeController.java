package com.employee.controller;

import java.sql.Date;
import java.sql.SQLException;

import com.employee.model.Employee;
import com.employee.service.EmployeeMangementImpl;
import com.employee.view.EmployeeInformation;

public class EmployeeController {
    private final static EmployeeMangementImpl EMPLOYEE_SERVICE = new EmployeeMangementImpl();

    public void addNewEmployee(Employee employee) throws SQLException {
        EMPLOYEE_SERVICE.addNewEmployee(employee);
    }

    public void viewEmployees() throws SQLException {
        EMPLOYEE_SERVICE.viewEmployees();
    }

    public void deleteEmployee(int employeeId) throws SQLException {
        EMPLOYEE_SERVICE.deleteEmployee(employeeId);
    }

    public void updateEmployee(Employee employee) throws SQLException {
        EMPLOYEE_SERVICE.updateEmployee(employee);
    }
	
    public void updateAllEmployeeDetails(Employee employee) throws SQLException {
        EMPLOYEE_SERVICE.updateEmployee(employee);
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
	
    public static String failedphoneNumberValidation(String phoneNumber) {
        return EmployeeInformation.getEmployeePhoneNumber();
    }
	
    public static Date dateValidation(String joiningDate) {
        return EMPLOYEE_SERVICE.dateValidation(joiningDate);
    }
	
    public static Date failedDateValidation(String joiningDate) {
        return EmployeeInformation.getEmployeeJoiningDate();
    }
}