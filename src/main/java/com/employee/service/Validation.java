package com.employee.service;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.employee.main.EmployeeMain;
import com.employee.exception.CustomException.DateNotValidException;

/**
 * Validation for employee id, name, phone number, joining date, salary. 
 */
public class Validation {
    
    /**
     * Check whether id contains only numbers. 
     */
    public boolean employeeIdValidation(final String employeeId) {
        
        if (!employeeId.matches("[0-9]{1,}")) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Check whether the name contains only alphabets. 
     */
    public boolean employeeNameValidation(final String employeeName) {
        Pattern pattern = Pattern.compile("[A-Za-z\\s]{1,}");
        Matcher match = pattern.matcher(employeeName);

        if (!(match.find() && match.group().equals(employeeName))) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Check whether phone number contains only numbers. 
     */
    public boolean phoneNumberValidation(final String phoneNumber) {

        if (!phoneNumber.matches("[6-9][0-9]{9}")) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Check whether the date is correct or not. 
     */
    public boolean dateValidation(final String joiningDate) {
        
        try {
            final LocalDate parsedDate = LocalDate.parse(joiningDate);
            final LocalDate todayDate = LocalDate.now();
            
            if (todayDate.isAfter(parsedDate)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            throw new DateNotValidException("Invalid date!!");
        }
    }
    
    /**
     * Check whether salary contains only decimal. 
     */
    public boolean employeeSalaryValidation(final String employeeSalary) {
        
        if (employeeSalary.matches("(\\d+\\.\\d+)")) {
            return true;
        } else {
            return false;
        }
    }
    
    public static String validateChoice(final String choice) {
        
        if(!choice.matches("[1-4]")) {
            System.out.println("Please enter a choice between 1-4"); 
            return validateChoice(EmployeeMain.SCANNER.nextLine());
        }
        return choice;
    }
}
