package com.employee.view;

import java.sql.Date;

import com.employee.main.EmployeeMain;

/**
 * Get inputs from users using scanners. The input data received from user 
 * is then checked for validation. Here employee id, name, salary, phone number and 
 * joining date are stored. 
 */
public class EmployeeInformation {

	public static int getEmployeeId() {
		System.out.println("Enter employee Id:"); 
		return EmployeeManagementValidation.employeeIdValidation(EmployeeMain.SCANNER.next());
	}

	public static String getEmployeeName() {
		System.out.println("Enter employee Name:");  
		return EmployeeManagementValidation.employeeNameValidation(EmployeeMain.SCANNER.next());
	}

	public static double getEmployeeSalary() {
		System.out.println("Enter employee salary:"); 
		return EmployeeManagementValidation.employeeSalaryValidation(EmployeeMain.SCANNER.next());
	}

	public static String getEmployeePhoneNumber() {
		System.out.println("Enter employee phone number:"); 
		return EmployeeManagementValidation.phoneNumberValidation(EmployeeMain.SCANNER.next());
	}

	public static Date getEmployeeJoiningDate() {
		System.out.println("Enter employee joining date(yyyy-MM-dd):"); 
		return EmployeeManagementValidation.dateValidation(EmployeeMain.SCANNER.next());
	}
}