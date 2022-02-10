package com.employee.view;

import java.sql.Date;

import org.apache.log4j.Logger;

import com.employee.main.EmployeeMain;
import com.employee.model.Employee;
import com.employee.controller.EmployeeController;
import com.employee.exception.CustomException;

/**
 * Get inputs from users using scanners. The input data received from user is then checked for validation. 
 * Here employee id, name, salary, phone number and joining date are stored. 
 */
public class EmployeeInformation {
    
    private static final EmployeeController EMPLOYEE_CONTROL = new EmployeeController();
    private static final Logger LOGGER = Logger.getLogger(EmployeeInformation.class);

    public static int getEmployeeId() {
        LOGGER.info("Enter employee Id:"); 
        final String id = EmployeeMain.SCANNER.nextLine();
        final boolean isValidId = EmployeeController.employeeIdValidation(id);
        
        if (isValidId) {
           return Integer.parseInt(id);
        } else {
            LOGGER.info("Please enter valid id that contains only numbers");
           return EmployeeInformation.getEmployeeId();
        }
    }

    public static String getEmployeeName() {
        LOGGER.info("Enter employee Name:");
        final String name = EmployeeMain.SCANNER.nextLine();
        final boolean isValidName = EmployeeController.employeeNameValidation(name);
        
        if (isValidName) {
            return name;
        } else {
            LOGGER.info("Invalid, Please enter a valid Name");
            return EmployeeInformation.getEmployeeName();
         }
    }

    public static double getEmployeeSalary() {
        LOGGER.info("Enter employee salary:"); 
        final String salary = EmployeeMain.SCANNER.nextLine();
        final boolean isValidSalary = EmployeeController.employeeSalaryValidation(salary);
        
        if (isValidSalary) {
            final double totalSalary = Double.parseDouble(salary);
            double grossSalary = 0;
            
            if (totalSalary > 50000) {
                final double providentFund = 6000;
                
                grossSalary =  totalSalary - providentFund;
                //System.out.println(String.format("Provident fund tax = %s", providentFund));
            }
            
            if (totalSalary < 50000) {
                final double providentFund = 3000;
                
                grossSalary =  totalSalary - providentFund;
                //System.out.println(String.format("Provident fund = %s", providentFund));
            }
            return grossSalary;
        } else {
            LOGGER.info("Please enter valid salary detail that contains decimal values");
            return EmployeeInformation.getEmployeeSalary();
        }
    }

    public static String getEmployeePhoneNumber() {
        LOGGER.info("Enter employee phone number:"); 
        final String phoneNumber = EmployeeMain.SCANNER.nextLine();
        final boolean isValidNumber = EmployeeController.phoneNumberValidation(phoneNumber);

        if (isValidNumber) {
            return phoneNumber;
        } else {
            LOGGER.info("Please enter valid 10 digit phone number");
            return EmployeeInformation.getEmployeePhoneNumber();
        }
    }

    public static Date getEmployeeJoiningDate() {
        LOGGER.info("Enter employee joining date(yyyy-MM-dd):");
        final String date = EmployeeMain.SCANNER.nextLine();
        boolean isValidDate = false;
        
        try {
            isValidDate = EmployeeController.dateValidation(date);
        } catch (CustomException e){
            System.out.println(e);
        }
        
        if (isValidDate) {
            return Date.valueOf(date);
        } else {
            LOGGER.info("Please enter valid date");
            return EmployeeInformation.getEmployeeJoiningDate();
        }
    }
    
    /**
     * Add employee details by getting data from the user. 
     * And stored employee id, name, salary, phone number, and joining date in an object named employee.
     */
    public static void addNewEmployee() {
        final int employeeId = EmployeeInformation.getEmployeeId();
        final String employeeName = EmployeeInformation.getEmployeeName();
        final double salary = EmployeeInformation.getEmployeeSalary();
        final String phoneNumber = EmployeeInformation.getEmployeePhoneNumber();
        final Date date = EmployeeInformation.getEmployeeJoiningDate();
        
        final Employee employee = new Employee(employeeId, employeeName, salary, phoneNumber, date);
        
        try {
            boolean isAdded = EMPLOYEE_CONTROL.addNewEmployee(employee);
            
            if (isAdded) {
                LOGGER.info("Data added in database successfully");
            }
        } catch (CustomException e) {
            LOGGER.error(e);
        }
    }

    /**
     * Show all employee details.
     */
    public static void viewEmployees() {
        
        try {
            System.out.println(EMPLOYEE_CONTROL.viewEmployees());
        } catch (CustomException e) {
            LOGGER.error(e);
        }
    }

    /**
     * Delete employee detail by using id.
     */
    public static void deleteEmployee() {
        int employeeId = EmployeeInformation.getEmployeeId();
        
        try {
            boolean deleteData = EMPLOYEE_CONTROL.deleteEmployee(employeeId);
            
            if(deleteData) {
                LOGGER.info("Data deleted in database successfully");
            }
        } catch (CustomException e) {
            LOGGER.error(e);
        }
    }

    /**
     * Update employee detail by using id.
     */
    public static void updateEmployeeDetails() {
        String employeeName = null;
        double salary = 0;
        String phoneNumber = null;
        Date date = null;
        final String choice = "yes";
        int employeeId = EmployeeInformation.getEmployeeId();
        
        LOGGER.info("Do you want to change name ?\t yes or no");
        
        if (choice.equals(EmployeeMain.SCANNER.nextLine())) {
            employeeName = EmployeeInformation.getEmployeeName();
        }
        LOGGER.info("Do you want to change salary ?\t yes or no");
        
        if (choice.equals(EmployeeMain.SCANNER.nextLine())) {
            salary = EmployeeInformation.getEmployeeSalary();
        }
        LOGGER.info("Do you want to change phone number ?\t yes or no");
        
        if (choice.equals(EmployeeMain.SCANNER.nextLine())) {
            phoneNumber = EmployeeInformation.getEmployeePhoneNumber();
        }
        LOGGER.info("Do you want to change joining date ?\t yes or no");
        
        if (choice.equals(EmployeeMain.SCANNER.nextLine())) {
            date = EmployeeInformation.getEmployeeJoiningDate();
        }
        final Employee employee = new Employee();
        
        employee.setEmployeeId(employeeId);
        employee.setEmployeeName(employeeName);
        employee.setSalary(salary);
        employee.setPhoneNumber(phoneNumber);
        employee.setDate(date);
        
        try {
            boolean updateData = EMPLOYEE_CONTROL.updateEmployeeDetails(employee);
                    
            if(updateData) {
                LOGGER.info("Data updated in database successfully");
            }
        } catch (CustomException e) {
            LOGGER.error(e);
        }
    }
}