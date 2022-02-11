package com.employee.view;

import java.sql.Date;
import java.util.Map.Entry;

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
        LOGGER.info("Enter employee Id: \nPress ~ to exit to main menu"); 
        final String id = EmployeeMain.SCANNER.nextLine().trim();
        
        if (("~").equals(id)) {
            EmployeeMain.selectChoice();
        }
        final boolean isValidId = EmployeeController.employeeIdValidation(id);
        
        if (isValidId) {
           return Integer.parseInt(id);
        } else {
            LOGGER.info("Please enter valid id that contains only numbers");
           return EmployeeInformation.getEmployeeId();
        }
    }

    public static String getEmployeeName() {
        LOGGER.info("Enter employee Name: \nPress ~ to exit to main menu");
        final String name = EmployeeMain.SCANNER.nextLine().trim();
        
        if (("~").equals(name)) {
            EmployeeMain.selectChoice();
        }
        final boolean isValidName = EmployeeController.employeeNameValidation(name);
        
        if (isValidName) {
            return name;
        } else {
            LOGGER.info("Invalid, Please enter a valid Name");
            return EmployeeInformation.getEmployeeName();
         }
    }

    public static double getEmployeeSalary() {
        LOGGER.info("Enter employee salary: \nPress ~ to exit to main menu"); 
        final String salary = EmployeeMain.SCANNER.nextLine().trim();
        
        if (("~").equals(salary)) {
            EmployeeMain.selectChoice();
        }
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
        LOGGER.info("Enter employee phone number: \nPress ~ to exit to main menu"); 
        final String phoneNumber = EmployeeMain.SCANNER.nextLine().trim();
        
        if (("~").equals(phoneNumber)) {
            EmployeeMain.selectChoice();
        }
        final boolean isValidNumber = EmployeeController.phoneNumberValidation(phoneNumber);

        if (isValidNumber) {
            return phoneNumber;
        } else {
            LOGGER.info("Please enter valid 10 digit phone number");
            return EmployeeInformation.getEmployeePhoneNumber();
        }
    }

    public static Date getEmployeeJoiningDate() {
        LOGGER.info("Enter employee joining date(yyyy-MM-dd): \nPress ~ to exit to main menu");
        final String date = EmployeeMain.SCANNER.nextLine().trim();
        
        if (("~").equals(date)) {
            EmployeeMain.selectChoice();
        }
        boolean isValidDate = false;
        
        try {
            isValidDate = EmployeeController.dateValidation(date);
        } catch (CustomException e){
            LOGGER.error(e);
        }
        
        if (isValidDate) {
            return Date.valueOf(date);
        } else {
            LOGGER.info("Please enter valid date");
            return EmployeeInformation.getEmployeeJoiningDate();
        }
    }
    
    public static String getChoice() {
        final String choice = EmployeeMain.SCANNER.nextLine().trim();
        final boolean isValidChoice = EmployeeController.validateChoice(choice);
        
        if (isValidChoice) {
            return choice;
        } else {
            LOGGER.info("Please enter valid choice between 1 - 5");
            return EmployeeInformation.getChoice();
        }
    }
    
    /**
     * Add employee details by getting data from the user. 
     * And stored employee id, name, salary, phone number, and joining date in an object named employee.
     */
    public static void addNewEmployee() {
        final int employeeId = EmployeeInformation.getEmployeeId();
        
        try {
            EmployeeController.checkEmployeeId(employeeId);
        } catch (CustomException e) {
            LOGGER.error(e);
            EmployeeInformation.addNewEmployee();
            EmployeeMain.selectChoice();
        }
        final String employeeName = EmployeeInformation.getEmployeeName();
        final double salary = EmployeeInformation.getEmployeeSalary();
        final String phoneNumber = EmployeeInformation.getEmployeePhoneNumber();
        final Date date = EmployeeInformation.getEmployeeJoiningDate();
        
        final Employee employee = new Employee(employeeId, employeeName, salary, phoneNumber, date);
                
        try {
            final boolean isAdded = EMPLOYEE_CONTROL.addNewEmployee(employee);
                
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
            for (Entry<Integer, Employee> entry : EMPLOYEE_CONTROL.viewEmployees().entrySet()) {
                System.out.println(entry.getValue());
            }
        } catch (CustomException e) {
            LOGGER.error(e);
        }
    }

    /**
     * Delete employee detail by using id.
     */
    public static void deleteEmployee() {
 
        try {
            int employeeId = EmployeeInformation.getEmployeeId();
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
        final String choiceYes = "yes";
        final String choiceNo = "no";
        
        final int employeeId = EmployeeInformation.getEmployeeId();
        
        try {
            EmployeeController.checkEmployeeIdUpdate(employeeId);
        } catch (CustomException e) {
            LOGGER.error(e);
            EmployeeInformation.updateEmployeeDetails();;
            EmployeeMain.selectChoice();
        }
        LOGGER.info("Do you want to change name ?\t yes or no\nPress ~ to exit to main menu");
        
        while (true) {
            final String option = EmployeeMain.SCANNER.nextLine();
            
            if (("~").equals(option)) {
                EmployeeMain.selectChoice();
            }
            
            if (choiceYes.equalsIgnoreCase(option)) {
                employeeName = EmployeeInformation.getEmployeeName();
                break;
            } else if (choiceNo.equalsIgnoreCase(option)) {
                break;
            } else {
                LOGGER.info("Invalid option, please enter valid option");
                continue;
            }
        }
        LOGGER.info("Do you want to change salary ?\t yes or no\nPress ~ to exit to main menu");
        
        while (true) {
            final String option = EmployeeMain.SCANNER.nextLine();
            
            if (("~").equals(option)) {
                EmployeeMain.selectChoice();
            }
            
            if (choiceYes.equalsIgnoreCase(option)) {
                salary = EmployeeInformation.getEmployeeSalary();
                break;
            } else if (choiceNo.equalsIgnoreCase(option)) {
                break;
            } else {
                LOGGER.info("Invalid option, please enter valid option");
                continue;
            }
        }
        LOGGER.info("Do you want to change phone number ?\t yes or no\nPress ~ to exit to main menu");
        
        while (true) {
            final String option = EmployeeMain.SCANNER.nextLine();
            
            if (("~").equals(option)) {
                EmployeeMain.selectChoice();
            }
            
            if (choiceYes.equalsIgnoreCase(option)) {
                phoneNumber = EmployeeInformation.getEmployeePhoneNumber();
                break;
            } else if (choiceNo.equalsIgnoreCase(option)) {
                break;
            } else {
                LOGGER.info("Invalid option, please enter valid option");
                continue;
            }
        }
        LOGGER.info("Do you want to change joining date ?\t yes or no\nPress ~ to exit to main menu");
        
        while (true) {
            final String option = EmployeeMain.SCANNER.nextLine();
            
            if (("~").equals(option)) {
                EmployeeMain.selectChoice();
            }
            
            if (choiceYes.equalsIgnoreCase(option)) {
                date = EmployeeInformation.getEmployeeJoiningDate();
                break;
            } else if (choiceNo.equalsIgnoreCase(option)) {
                break;
            } else {
                LOGGER.info("Invalid option, please enter valid option");
                continue;
            }
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
