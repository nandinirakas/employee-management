package com.employee.view;

import java.sql.Date;
import java.util.Scanner;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.employee.model.Employee;
import com.employee.controller.EmployeeController;
import com.employee.exception.CustomException;

public class EmployeeInformation {
    
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Logger LOGGER = Logger.getLogger(EmployeeInformation.class);

    public static void selectChoice() {
        
        do {
            LOGGER.info("EMPLOYEE MANAGEMENT\n1.ADD DETAILS\n2.VIEW DETAILS\n3.DELETE DETAILS\n4.UPDATE DETAILS\n5.EXIT\nEnter your choice:");
            final int choice = Integer.parseInt(EmployeeInformation.getChoice());

            switch (choice) {
            case 1:
                EmployeeInformation.addNewEmployee();
                break;
            case 2:
                EmployeeInformation.viewEmployees();
                break;
            case 3:
                EmployeeInformation.deleteEmployee();
                break;
            case 4:
                EmployeeInformation.updateEmployeeDetails();
                break;
            case 5:
                SCANNER.close();
                System.exit(0);
            }
         } while (true);
    }  
    /**
     * Get employee id
     */
    public static int getEmployeeId() {
        LOGGER.info("Enter employee Id: \nPress ~ to exit to main menu"); 
        final String id = SCANNER.nextLine().trim();
        
        EmployeeInformation.returnToMainMenu(id);
        
        if (EmployeeController.employeeIdValidation(id)) {
           return Integer.parseInt(id);
        } else {
           LOGGER.info("Please enter valid id that contains only numbers");
           return EmployeeInformation.getEmployeeId();
        }
    }

    /**
     * Get employee name
     */
    public static String getEmployeeName() {
        LOGGER.info("Enter employee Name: \nPress ~ to exit to main menu");
        final String name = SCANNER.nextLine().trim();
        
        EmployeeInformation.returnToMainMenu(name);
        
        if (EmployeeController.employeeNameValidation(name)) {
            return name;
        } else {
            LOGGER.info("Invalid, Please enter a valid Name");
            return EmployeeInformation.getEmployeeName();
        }
    }

    /**
     * Get employee salary
     */
    public static double getEmployeeSalary() {
        LOGGER.info("Enter employee salary: \nPress ~ to exit to main menu"); 
        final String salary = SCANNER.nextLine().trim();
        
        EmployeeInformation.returnToMainMenu(salary);
        
        if (EmployeeController.employeeSalaryValidation(salary)) {
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

    /**
     * Get employee phone number
     */
    public static String getEmployeePhoneNumber() {
        LOGGER.info("Enter employee phone number: \nPress ~ to exit to main menu"); 
        final String phoneNumber = SCANNER.nextLine().trim();
        
        EmployeeInformation.returnToMainMenu(phoneNumber);

        if (EmployeeController.phoneNumberValidation(phoneNumber)) {
            return phoneNumber;
        } else {
            LOGGER.info("Please enter valid 10 digit phone number");
            return EmployeeInformation.getEmployeePhoneNumber();
        }
    }

    /**
     * Get employee joining date
     */
    public static Date getEmployeeJoiningDate() {
        LOGGER.info("Enter employee joining date(yyyy-MM-dd): \nPress ~ to exit to main menu");
        final String date = SCANNER.nextLine().trim();
        
        EmployeeInformation.returnToMainMenu(date);
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
    
    /**
     * Get choice for CRUD operation from user
     */
    public static String getChoice() {
        final String choice = SCANNER.nextLine().trim();
        
        if (EmployeeController.validateChoice(choice)) {
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
            EmployeeController.checkEmployeeIdAdd(employeeId);
        } catch (CustomException e) {
            LOGGER.error(e);
            EmployeeInformation.addNewEmployee();
            EmployeeInformation.selectChoice();
        }
        final String employeeName = EmployeeInformation.getEmployeeName();
        final double salary = EmployeeInformation.getEmployeeSalary();
        final String phoneNumber = EmployeeInformation.getEmployeePhoneNumber();
        final Date date = EmployeeInformation.getEmployeeJoiningDate();
        
        final Employee employee = new Employee(employeeId, employeeName, salary, phoneNumber, date);
                
        try {
            final boolean isAdded = EmployeeController.addNewEmployee(employee);
                
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
            
            for (Entry<Integer, Employee> entry : EmployeeController.getEmployees().entrySet()) {
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
            final int employeeId = EmployeeInformation.getEmployeeId();
            final boolean deleteData = EmployeeController.deleteEmployee(employeeId);
            
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
        final String name = "name";
        final String salary = "salary";
        final String date = "date";
        final String number = "number";
        Employee employee = new Employee();
        final int employeeId = EmployeeInformation.getEmployeeId();
        
        try {
            EmployeeController.checkEmployeeIdUpdate(employeeId);
        } catch (CustomException e) {
            LOGGER.error(e);
            EmployeeInformation.updateEmployeeDetails();;
            EmployeeInformation.selectChoice();
        }
        employee.setEmployeeId(employeeId);
        
        LOGGER.info("Do you want to change name ?\t yes or no\nPress ~ to exit to main menu");
        checkingCondition(employee, name);
        
        LOGGER.info("Do you want to change salary ?\t yes or no\nPress ~ to exit to main menu");
        EmployeeInformation.checkingCondition(employee, salary);
        
        LOGGER.info("Do you want to change phone number ?\t yes or no\nPress ~ to exit to main menu");
        EmployeeInformation.checkingCondition(employee, number);
        
        LOGGER.info("Do you want to change joining date ?\t yes or no\nPress ~ to exit to main menu");
        EmployeeInformation.checkingCondition(employee, date);
        
        try {
            boolean updateData = EmployeeController.updateEmployeeDetails(employee);
                    
            if(updateData) {
                LOGGER.info("Data updated in database successfully");
            }
        } catch (CustomException e) {
            LOGGER.error(e);
        }
    }
    
    /**
     * Return to main menu.
     * 
     * @param option
     */
    public static void returnToMainMenu(String option) {
        
        if ("~".equals(option)) {
            EmployeeInformation.selectChoice();
        }
    }
    
    public static Employee checkingCondition(Employee employee, String employeeDetail) {
        final String choiceYes = "yes";
        final String choiceNo = "no";
        while (true) {
            final String option = SCANNER.nextLine().trim();
            
            EmployeeInformation.returnToMainMenu(option);
            
            if (choiceYes.equalsIgnoreCase(option)) {
                
                if ("name".equalsIgnoreCase(employeeDetail)) {
                    employee.setEmployeeName(EmployeeInformation.getEmployeeName());
                    break;
                } else if ("salary".equalsIgnoreCase(employeeDetail)) {
                    employee.setSalary(EmployeeInformation.getEmployeeSalary());
                    break;
                } else if ("number".equalsIgnoreCase(employeeDetail)) {
                    employee.setPhoneNumber(EmployeeInformation.getEmployeePhoneNumber());
                    break;
                } else if ("date".equalsIgnoreCase(employeeDetail)) {
                    employee.setDate(EmployeeInformation.getEmployeeJoiningDate());
                }
            } else if (choiceNo.equalsIgnoreCase(option)) {
                break;
            } else {
                LOGGER.info("Invalid option, please enter valid option");
                continue;
            }
        }
        return employee;
    }
}
