package com.employee.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.employee.view.EmployeeInformation;
import com.employee.service.Validation;

/**
 * CRUD operation performs functions like creating new employee details, viewing the list of employees registered, 
 * replacing old data with new data, and deleting employee data with employee id.
 * 
 * @author NandiniRakAS
 */
public class EmployeeMain {
   
    private static final String LOG_FILE = "log4j.properties";
    private static final Logger LOGGER = Logger.getLogger(EmployeeMain.class);
    private static Properties properties = new Properties();
    public static final Scanner SCANNER = new Scanner(System.in);
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        properties.load(new FileInputStream(LOG_FILE));
        PropertyConfigurator.configure(properties);
        
        do {
            LOGGER.info("1.COLLECT DETAILS\n2.VIEW DETAILS\n3.DELETE DETAILS\n4.UPDATE DETAILS\nEnter your choice:");
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