package com.employee.view;

import java.util.Date;
import com.employee.main.EmployeeMain;

/**
 * Get inputs from users using scanners. The input data
 * received from user is then send to employee management validation class and
 * checked for validation. And after satisfying those validation conditions, the
 * data will be returned.
 */
public class EmployeeInformation {

	/**
	 * For getting employee id
	 */
	public static int getEmployeeId() {
		System.out.println("Enter employee Id:"); 
		return EmployeeManagementValidation.employeeIdValidation(EmployeeMain.SCANNER.next());
	}

	/**
	 * For getting employee name
	 */
	public static String getEmployeeName() {
		System.out.println("Enter employee Name:");  
		return EmployeeManagementValidation.employeeNameValidation(EmployeeMain.SCANNER.next());
	}

	/**
	 * For getting employee salary
	 */
	public static double getEmployeeSalary() {
		System.out.println("Enter employee salary:"); 
		return EmployeeManagementValidation.employeeSalaryValidation(EmployeeMain.SCANNER.next());
	}

	/**
	 * For getting employee phone number
	 */
	public static String getEmployeePhoneNumber() {
		System.out.println("Enter employee phone number:"); 
		return EmployeeManagementValidation.phoneNumberValidation(EmployeeMain.SCANNER.next());
	}

	/**
	 * For getting employee joining date
	 */
	public static Date getEmployeeJoiningDate() {
		System.out.println("Enter employee joining date(dd-MM-yyyy):"); 
		return EmployeeManagementValidation.dateValidation(EmployeeMain.SCANNER.next());
	}
}