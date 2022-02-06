package com.employee.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.employee.controller.EmployeeController;
import com.employee.main.EmployeeMain;

/**
 * Validation for employee id, name, phone number, joining date, salary. 
 */
public class Validation {
    /**
     * Check whether id contains only numbers. 
     */
    public int employeeIdValidation(final String employeeId) {
        
        if (!employeeId.matches("[0-9]{1,}")) {
            System.out.println("Please enter valid id that contains only numbers");
            return EmployeeController.failedEmployeeIdValidation(employeeId);
        }
        return Integer.parseInt(employeeId);
    }
    
    /**
     * Check whether the name contains only alphabets. 
     */
    public String employeeNameValidation(final String employeeName) {
        Pattern pattern = Pattern.compile("[A-Za-z]{1,}");
        Matcher match = pattern.matcher(employeeName);

        if (!(match.find() && match.group().equals(employeeName))) {
            System.out.println("Invalid, Please enter a valid Name");
            return EmployeeController.failedEmployeeNameValidation(employeeName);
        }
        return employeeName;
    }
    
    /**
     * Check whether phone number contains only numbers. 
     */
    public String phoneNumberValidation(final String phoneNumber) {

        if (!phoneNumber.matches("[6-9][0-9]{9}")) {
            System.out.println("Please enter valid 10 digit phone number");
            return EmployeeController.failedPhoneNumberValidation(phoneNumber);
        }
        return phoneNumber;
    }
    
    /**
     * Check whether the date is in given format. 
     */
    public Date dateValidation(final String joiningDate) {
        Date sqlDate;
        
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            java.util.Date date = dateFormat.parse(joiningDate); 
            sqlDate = new Date(date.getTime());    
        } catch (Exception exception) {
            System.out.println("Please enter valid date");
            return EmployeeController.failedDateValidation(joiningDate);
        }
        return sqlDate;
    }
    
    /**
     * Check whether salary contains only decimal and validating by reducing provident fund and income tax. 
     */
    public double employeeSalaryValidation(final String employeeSalary) {
        double grossSalary = 0;
        
        if (employeeSalary.matches("(\\d+\\.\\d+)")) {
            double totalSalary = Double.parseDouble(employeeSalary);
            double incomeTax = 600; 
            double providentFund = 1000;    
            
            grossSalary = totalSalary - (incomeTax + providentFund);
        } else {
            System.out.println("Please enter valid salary detail that contains decimal values");
            return EmployeeController.failedEmployeeSalaryValidation(employeeSalary);
        }
        return grossSalary;
    }
    
    public static int validateChoice(final String choice) {
        
        if(!choice.matches("[1-4]")) {
            System.out.println("Please enter a choice between 1-4"); 
            return validateChoice(EmployeeMain.SCANNER.next());
        }
        return Integer.parseInt(choice);
    }
}
