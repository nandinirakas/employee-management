package com.employee.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.employee.controller.EmployeeController;
import com.employee.dao.EmployeeDatabase;
import com.employee.model.Employee;

/**
 * Implementation of the methods that are given in interface.
 * Created a linked hashmap collection for storing details to maintain order and better performance.
 */
public class EmployeeMangementImpl implements EmployeeManagement {
    private final EmployeeDatabase EMPLOYEE_DATABASE = new EmployeeDatabase();
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
            return EmployeeController.failedphoneNumberValidation(phoneNumber);
        }
        return phoneNumber;
    }
	
    public Date dateValidation(String joiningDate) {
        java.sql.Date sqlDate;
		
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
        EMPLOYEE_DATABASE.addNewEmployee(employee);
        int employeeId = employee.getEmployeeId();
				
        if (EMPLOYEE_DETAILS.containsKey(employeeId)) {
            System.out.println("The given Id already present, please enter new id");
        } else {
            EMPLOYEE_DETAILS.put(employeeId, employee);
        }
    }

    /**
     * Showing all employee details that are stored in the list by using for each.
     * Entry will give both key and value.
     */
    public void viewEmployees() {
        EMPLOYEE_DATABASE.getEmployees();
    }

    /**
     * Deleting each employee details that are stored in the list
     * based on employee id.
     * 
     * @param employeeId
     */
    public void deleteEmployee(int employeeId) {
        EMPLOYEE_DATABASE.deleteEmployee(employeeId);
		
        if (EMPLOYEE_DETAILS.containsKey(employeeId)) {
            EMPLOYEE_DETAILS.remove(employeeId);
        } 
    }

    /**
     * Replacing each employee details based on employee id.
     * 
     * @param employee
     */
    public void updateEmployee(Employee employee) {
        EMPLOYEE_DATABASE.updateEmployee(employee);
        int employeeIdKey = employee.getEmployeeId();
		
        if (EMPLOYEE_DETAILS.containsKey(employeeIdKey)) {
            Employee employeeData = EMPLOYEE_DETAILS.get(employeeIdKey);
		
                if (employee.getEmployeeName() != null) { 
                    employeeData.setEmployeeName(employee.getEmployeeName());
                } else if (employee.getSalary() != 0) {
                    employeeData.setSalary(employee.getSalary());
                } else if (employee.getPhoneNumber() != null) {
                    employeeData.setPhoneNumber(employee.getPhoneNumber());
                } else if (employee.getDate() != null) {
                   employeeData.setDate(employee.getDate());
                }
        }
    }
	
    public void updateAllEmployeeDetails(Employee employee) {
        EMPLOYEE_DATABASE.updateAllEmployeeDetails(employee);
        int employeeIdKey = employee.getEmployeeId();
    	
        if (EMPLOYEE_DETAILS.containsKey(employeeIdKey)) {
            Employee employeeData = EMPLOYEE_DETAILS.get(employeeIdKey);
    		
            if (employee.getEmployeeName() != null && employee.getSalary() != 0 && employee.getPhoneNumber() != null && employee.getDate() != null) {
                employeeData.setEmployeeName(employee.getEmployeeName());
                employeeData.setSalary(employee.getSalary());
                employeeData.setPhoneNumber(employee.getPhoneNumber());
                employeeData.setDate(employee.getDate());
            }
        }
    }
}