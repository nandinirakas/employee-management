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
        return EmployeeController.employeeIdValidation(EmployeeMain.SCANNER.next());
    }

    public static String getEmployeeName() {
        System.out.println("Enter employee Name:");  
        return EmployeeController.employeeNameValidation(EmployeeMain.SCANNER.next());
    }

    public static double getEmployeeSalary() {
        System.out.println("Enter employee salary:"); 
        return EmployeeController.employeeSalaryValidation(EmployeeMain.SCANNER.next());
    }

    public static String getEmployeePhoneNumber() {
        System.out.println("Enter employee phone number:"); 
        return EmployeeController.phoneNumberValidation(EmployeeMain.SCANNER.next());
    }

    public static Date getEmployeeJoiningDate() {
        System.out.println("Enter employee joining date(yyyy-MM-dd):"); 
        return EmployeeController.dateValidation(EmployeeMain.SCANNER.next());
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
        
        if ((choice).equals(EmployeeMain.SCANNER.next())) {
            employeeName = EmployeeInformation.getEmployeeName();
        }
        System.out.println("Do you want to change salary ?\t yes or no");
        
        if ((choice).equals(EmployeeMain.SCANNER.next())) {
            salary = EmployeeInformation.getEmployeeSalary();
        }
        System.out.println("Do you want to change phone number ?\t yes or no");
        
        if ((choice).equals(EmployeeMain.SCANNER.next())) {
            phoneNumber = EmployeeInformation.getEmployeePhoneNumber();
        }
        System.out.println("Do you want to change joining date ?\t yes or no");
        
        if ((choice).equals(EmployeeMain.SCANNER.next())) {
            date = EmployeeInformation.getEmployeeJoiningDate();
        }
        Employee employee = new Employee();
        
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