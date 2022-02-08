package com.employee.main;

import java.util.Scanner;

import com.employee.view.EmployeeInformation;
import com.employee.service.Validation;

/**
 * CRUD operation performs functions like creating new employee details, viewing the list of employees registered, 
 * replacing old data with new data, and deleting employee data with employee id.
 * 
 * @author NandiniRakAS
 */
public class EmployeeMain {
    
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        do {
            System.out.println("1.COLLECT DETAILS\n2.VIEW DETAILS\n3.DELETE DETAILS\n4.UPDATE DETAILS\nEnter your choice:");
            final int choice = Integer.parseInt(Validation.validateChoice(SCANNER.nextLine()));

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
            default:
                SCANNER.close();
                System.exit(0);
            }
         } while (true);
    }
}