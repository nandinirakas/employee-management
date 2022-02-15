package com.employee.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import com.employee.view.EmployeeInformation;

/**
 * CRUD operation performs functions like creating new employee details, viewing the list of employees registered, 
 * replacing old data with new data, and deleting employee data with employee id.
 * 
 * @author NandiniRakAS
 */
public class EmployeeMain {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        final Properties properties = new Properties();
        properties.load(new FileInputStream("log4j.properties"));
        PropertyConfigurator.configure(properties);
        EmployeeInformation.selectChoice();
    }
}