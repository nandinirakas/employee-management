package com.employee.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validating details that are received from user. 
 */
public class EmployeeManagementValidation {
	/**
	 * Validating employee id by accepting only numbers.
	 * 
	 * @return String to integer value of id
	 */
	public static int employeeIdValidation(String employeeId) {

		if (!employeeId.matches("[0-9]{1,}")) {
			System.out.println("Please enter valid id that contains only numbers");
			return EmployeeInformation.getEmployeeId();
		}
		return Integer.parseInt(employeeId);
	}

	/**
	 * Validating employee name by accepting only alphabets. 
	 * 
	 * @return employeeName
	 */
	public static String employeeNameValidation(String employeeName) {
		Pattern pattern = Pattern.compile("[A-Za-z]{1,}");
		Matcher match = pattern.matcher(employeeName);

		if (!(match.find() && match.group().equals(employeeName))) {
			System.out.println("Invalid, Please enter a valid Name");
			return EmployeeInformation.getEmployeeName();
		}
		return employeeName;
	}

	/**
	 * Validating employee phone numbers by accepting only 10 digit numbers
	 * starting with 6-9.
	 * 
	 * @return phoneNumber
	 */
	public static String phoneNumberValidation(String phoneNumber) {

		if (!phoneNumber.matches("[6-9][0-9]{9}")) {
			System.out.println("Please enter valid 10 digit phone number");
			return EmployeeInformation.getEmployeePhoneNumber();
		}
		return phoneNumber;
	}

	/**
	 * Validating employee joining date by checking day, month, year by setLenient(). 
	 * 
	 * @return date
	 */
	public static Date dateValidation(String joiningDate) {
		Date date;

		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			dateFormat.setLenient(false);
			date = dateFormat.parse(joiningDate);
		} catch (Exception exception) {
			System.out.println("Please enter valid date");
			return EmployeeInformation.getEmployeeJoiningDate();
		}
		return date;
	}

	/**
	 * Validating employee gross salary.
	 */
	public static double employeeSalaryValidation(String employeeSalary) {
		double grossSalary = 0;
		
		if (employeeSalary.matches("[0-9]{1,}")) {
			double totalSalary = Double.parseDouble(employeeSalary);
		    double incomeTax = 600; 
		    double providentFund = 1000;    
		    
		    grossSalary = totalSalary - (incomeTax + providentFund);
		} else {
			System.out.println("Please enter valid salary detail that contains only numbers");
			return EmployeeInformation.getEmployeeSalary();
		}
	    return grossSalary;
		}
}
