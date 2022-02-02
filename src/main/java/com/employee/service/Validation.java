package com.employee.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.employee.controller.EmployeeController;

/**
 * Validation
 */
public class Validation {
    public int employeeIdValidation(String employeeId) {
        
        if (!employeeId.matches("[0-9]{1,}")) {
            System.out.println("Please enter valid id that contains only numbers");
            return EmployeeController.failedEmployeeIdValidation(employeeId);
        }
        return Integer.parseInt(employeeId);
    }
    
    public String employeeNameValidation(String employeeName) {
        Pattern pattern = Pattern.compile("[A-Za-z]{1,}");
        Matcher match = pattern.matcher(employeeName);

        if (!(match.find() && match.group().equals(employeeName))) {
            System.out.println("Invalid, Please enter a valid Name");
            return EmployeeController.failedEmployeeNameValidation(employeeName);
        }
        return employeeName;
    }
    
    public String phoneNumberValidation(String phoneNumber) {

        if (!phoneNumber.matches("[6-9][0-9]{9}")) {
            System.out.println("Please enter valid 10 digit phone number");
            return EmployeeController.failedPhoneNumberValidation(phoneNumber);
        }
        return phoneNumber;
    }
    
    public Date dateValidation(String joiningDate) {
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
    
    public double employeeSalaryValidation(String employeeSalary) {
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
}
