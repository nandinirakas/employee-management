package com.employee.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.employee.controller.EmployeeController;
import com.employee.exception.IdNotFoundException;
import com.employee.model.Employee;

/**
 * Implementation of the methods that are given in interface.
 * Created a linked hashmap collection for storing details to maintain order and better performance.
 */
public class EmployeeMangementImpl implements EmployeeManagement {
    private static final Map<Integer, Employee> EMPLOYEE_DETAILS = new LinkedHashMap<>();

    /**
     * Validation
     */
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
    
    /**
     * Adding a new employee.
     * 
     * @param employee Object contains id, name, salary, phone number and date
     */
    public void addNewEmployee(Employee employee) {
     
        if (EMPLOYEE_DETAILS.containsKey(employee.getEmployeeId())) {
               System.out.println("The given Id already present, please enter new id");
        } else {
            EMPLOYEE_DETAILS.put(employee.getEmployeeId(), employee);
        }
    }

    /**
     * Showing all employee details that are stored in the list by using for each.
     * Entry will give both key and value.
     */
    public void viewEmployees() {   
        
        for (Entry<Integer, Employee> entry : EMPLOYEE_DETAILS.entrySet()) {
            Integer key = entry.getKey();
            Employee value = entry.getValue();
            System.out.println(String.format("%s %s", String.valueOf(key), value));
        }
    }

    /**
     * Deleting each employee details that are stored in the list
     * based on employee id.
     * 
     * @param employeeId
     * @throws IdNotFoundException 
     */
    public void deleteEmployee(int employeeId) throws IdNotFoundException {
        
        if (EMPLOYEE_DETAILS.containsKey(employeeId)) {
            EMPLOYEE_DETAILS.remove(employeeId);
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
    
    public void updateEmployeeDetails(Employee employee) {
        int employeeIdKey = employee.getEmployeeId();
        
            if (EMPLOYEE_DETAILS.containsKey(employeeIdKey)) {
            Employee employeeData = EMPLOYEE_DETAILS.get(employeeIdKey);
            
                if (employee.getEmployeeName() != null) { 
                    employeeData.setEmployeeName(employee.getEmployeeName());
                }
            
                if (employee.getSalary() != 0) {
                    employeeData.setSalary(employee.getSalary());
                }
            
                if (employee.getPhoneNumber() != null) {
                    employeeData.setPhoneNumber(employee.getPhoneNumber());
                }
            
                if (employee.getDate() != null) {
                   employeeData.setDate(employee.getDate());
                }
            } else {
                System.out.println("Given id not present");        
            }
    }
}