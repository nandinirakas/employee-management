package com.employee.service;

import java.time.LocalDate;

import com.employee.exception.CustomException.DateNotValidException;

/**
 * Validation for employee id, name, phone number, joining date, salary. 
 */
public class Validation {
    
    /**
     * Check whether id contains only numbers. 
     * 
     * @param employeeId
     */
    public boolean employeeIdValidation(final String employeeId) {
        return employeeId.matches("[0-9]{1,}") ? true : false;
    }
    
    /**
     * Check whether the name contains only alphabets. 
     * 
     * @param employeeName
     */
    public boolean employeeNameValidation(final String employeeName) {
        return employeeName.matches("[A-Za-z\\s]{1,}") ? true : false;
    }
    
    /**
     * Check whether phone number contains only numbers. 
     * 
     * @param phoneNumber
     */
    public boolean phoneNumberValidation(final String phoneNumber) {
        return phoneNumber.matches("[6-9][0-9]{9}") ? true : false;
    }
    
    /**
     * Check whether the date is correct or not. 
     * 
     * @param joiningDate
     */
    public boolean dateValidation(final String joiningDate) {
        
        try {
            final LocalDate parsedDate = LocalDate.parse(joiningDate);
            final LocalDate todayDate = LocalDate.now();
            
            return todayDate.plusDays(1).isAfter(parsedDate) ? true : false;
        } catch (Exception exception) {
            throw new DateNotValidException("Invalid date!!");
        }
    }
    
    /**
     * Check whether salary contains only decimal. 
     * 
     * @param employeeSalary
     */
    public boolean employeeSalaryValidation(final String employeeSalary) {
        return employeeSalary.matches("(\\d+\\.\\d+)") ? true : false;
    }
    
    /**
     * Validation for choice given by user.
     * 
     * @param choice
     */
    public boolean validateChoice(final String choice) {
        return choice.matches("[1-5]") ? true : false;
    }
}
