package com.employee.main;

import java.util.Scanner;

import com.employee.view.EmployeeInformation;

/**
 * CRUD operation performs functions like creating new employee details, viewing the list of employees registered, 
 * replacing old data with new data, and deleting employee data with employee id.
 * 
 * @author NandiniRakAS
 */
public class EmployeeMain {
    public static final Scanner SCANNER = new Scanner(System.in);

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
         } while (choice != 0);
    }
}