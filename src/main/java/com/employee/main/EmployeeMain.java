package com.employee.main;

import java.sql.Date;
import java.util.Scanner;

import com.employee.controller.EmployeeController;
import com.employee.exception.IdAlreadyAvailableException;
import com.employee.exception.IdNotFoundException;
import com.employee.model.Employee;
import com.employee.view.EmployeeInformation;

/**
 * The simple Employee Management program using CRUD operation performs functions like creating new employee details, 
 * viewing the list of employee registered, replacing old data with new data and deleting employee data with employee id.
 * 
 * @author NandiniRakAS
 */
public class EmployeeMain {
    public static final Scanner SCANNER = new Scanner(System.in);
    private static final EmployeeController EMPLOYEE_CONTROL = new EmployeeController();

    /**
     * Performs switch case function for selecting according to preference. 1 for adding a new employee detail, 
     * 2 for viewing all employee details, 3 for deleting an employee detail using id, 4 for updating details.
     */
    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("1.COLLECT DETAILS\n2.VIEW DETAILS\n3.DELETE DETAILS\n4.UPDATE DETAILS\nEnter your choice:");
            choice = SCANNER.nextInt();

            switch (choice) {
            case 1:
                EmployeeMain.addNewEmployee();
                break;
            case 2:
                EmployeeMain.viewEmployees();
                break;
            case 3:
                EmployeeMain.deleteEmployee();
                break;
            case 4:
                EmployeeMain.updateEmployeeDetails();
                break;
            default:
                System.exit(0);
            }
         } while (choice != 0);
    }

    /**
     * Add employee details by getting data from user. 
     * And stored employee id, name, salary, phone number and joining date in an object named employee.
     */
    private static void addNewEmployee() {
        int employeeId = EmployeeInformation.getEmployeeId();
        String employeeName = EmployeeInformation.getEmployeeName();
        double salary = EmployeeInformation.getEmployeeSalary();
        String phoneNumber = EmployeeInformation.getEmployeePhoneNumber();
        Date date = EmployeeInformation.getEmployeeJoiningDate();

        Employee employee = new Employee(employeeId, employeeName, salary, phoneNumber, date);
        try {
            EMPLOYEE_CONTROL.addNewEmployee(employee);
        } catch (IdAlreadyAvailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Show all employee details.
     */
    private static void viewEmployees() {
        EMPLOYEE_CONTROL.viewEmployees(); 
    }

    /**
     * Delete employee detail by using id.
     */
    private static void deleteEmployee() {
        int employeeId = EmployeeInformation.getEmployeeId();
        
        try {
            EMPLOYEE_CONTROL.deleteEmployee(employeeId);
        } catch (IdNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * Update employee detail by using id.
     */
    private static void updateEmployeeDetails() {
        String employeeName = null;
        double salary = 0;
        String phoneNumber = null;
        Date date = null;
        int employeeId = EmployeeInformation.getEmployeeId();
        
        System.out.println("Do you want to change name ?\t yes or no");
        
        if (EmployeeMain.SCANNER.next().equals("yes")) {
            employeeName = EmployeeInformation.getEmployeeName();
        }
        System.out.println("Do you want to change salary ?\t yes or no");
        
        if (EmployeeMain.SCANNER.next().equals("yes")) {
            salary = EmployeeInformation.getEmployeeSalary();
        }
        System.out.println("Do you want to change phone number ?\t yes or no");
        
        if (EmployeeMain.SCANNER.next().equals("yes")) {
            phoneNumber = EmployeeInformation.getEmployeePhoneNumber();
        }
        System.out.println("Do you want to change joining date ?\t yes orno");
        
        if (EmployeeMain.SCANNER.next().equals("yes")) {
            date = EmployeeInformation.getEmployeeJoiningDate();
        }
        Employee employee = new Employee();
        
        employee.setEmployeeId(employeeId);
        employee.setEmployeeName(employeeName);
        employee.setSalary(salary);
        employee.setPhoneNumber(phoneNumber);
        employee.setDate(date);
        
        try {
            EMPLOYEE_CONTROL.updateEmployeeDetails(employee);
        } catch (IdNotFoundException e) {
            System.out.println(e);
        }
    }
}