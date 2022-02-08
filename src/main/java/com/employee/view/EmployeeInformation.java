package com.employee.view;

import java.sql.Date;

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

    public static int getEmployeeId() {
        System.out.println("Enter employee Id:"); 
        final String id = EmployeeMain.SCANNER.nextLine();
        final boolean isValidId = EmployeeController.employeeIdValidation(id);
        
        if (isValidId) {
           return Integer.parseInt(id);
        } else {
           System.out.println("Please enter valid id that contains only numbers");
           return EmployeeInformation.getEmployeeId();
        }
    }

    public static String getEmployeeName() {
        System.out.println("Enter employee Name:");
        final String name = EmployeeMain.SCANNER.nextLine();
        final boolean isValidName = EmployeeController.employeeNameValidation(name);
        
        if (isValidName) {
            return name;
        } else {
            System.out.println("Invalid, Please enter a valid Name");
            return EmployeeInformation.getEmployeeName();
         }
    }

    public static double getEmployeeSalary() {
        System.out.println("Enter employee salary:"); 
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
            System.out.println("Please enter valid salary detail that contains decimal values");
            return EmployeeInformation.getEmployeeSalary();
        }
    }

    public static String getEmployeePhoneNumber() {
        System.out.println("Enter employee phone number:"); 
        final String phoneNumber = EmployeeMain.SCANNER.nextLine();
        final boolean isValidNumber = EmployeeController.phoneNumberValidation(phoneNumber);

        if (isValidNumber) {
            return phoneNumber;
        } else {
            System.out.println("Please enter valid 10 digit phone number");
            return EmployeeInformation.getEmployeePhoneNumber();
        }
    }

    public static Date getEmployeeJoiningDate() {
        System.out.println("Enter employee joining date(yyyy-MM-dd):");
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
            System.out.println("Please enter valid date");
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
                System.out.println("Data added in database successfully");
            }
        } catch (CustomException e) {
            System.out.println(e);
        }
    }

    /**
     * Show all employee details.
     */
    public static void viewEmployees() {
        
        try {
            System.out.println(EMPLOYEE_CONTROL.viewEmployees());
        } catch (CustomException e) {
            System.out.println(e);
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
                System.out.println("Data deleted in database successfully");
            }
        } catch (CustomException e) {
            System.out.println(e);
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
        
        System.out.println("Do you want to change name ?\t yes or no");
        
        if (choice.equals(EmployeeMain.SCANNER.nextLine())) {
            employeeName = EmployeeInformation.getEmployeeName();
        }
        System.out.println("Do you want to change salary ?\t yes or no");
        
        if (choice.equals(EmployeeMain.SCANNER.nextLine())) {
            salary = EmployeeInformation.getEmployeeSalary();
        }
        System.out.println("Do you want to change phone number ?\t yes or no");
        
        if (choice.equals(EmployeeMain.SCANNER.nextLine())) {
            phoneNumber = EmployeeInformation.getEmployeePhoneNumber();
        }
        System.out.println("Do you want to change joining date ?\t yes or no");
        
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
                System.out.println("Data updated in database successfully");
            }
        } catch (CustomException e) {
            System.out.println(e);
        }
    }
}