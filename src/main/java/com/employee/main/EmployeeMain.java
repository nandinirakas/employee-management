package com.employee.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.employee.view.EmployeeInformation;

/**
 * CRUD operation performs functions like creating new employee details, viewing the list of employees registered, 
 * replacing old data with new data, and deleting employee data with employee id.
 * 
 * @author NandiniRakAS
 */
public class EmployeeMain {
  
    public static final Scanner SCANNER = new Scanner(System.in);
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        final Properties properties = new Properties();
        properties.load(new FileInputStream("log4j.properties"));
        PropertyConfigurator.configure(properties);
        EmployeeMain.selectChoice();
    }
        
    public static void selectChoice() {
        
        do {
            final Logger logger = Logger.getLogger(EmployeeMain.class);
            logger.info("EMPLOYEE MANAGEMENT\n1.ADD DETAILS\n2.VIEW DETAILS\n3.DELETE DETAILS\n4.UPDATE DETAILS\n5.EXIT\nEnter your choice:");
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
}