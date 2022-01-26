package com.employee.main;

import java.util.Date;
import java.util.Scanner;
import com.employee.controller.EmployeeController;
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
	 * 
	 * @param args
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
	 * Adding employee details by getting data from user. 
	 * And stored employee id, name, salary, phone number and joining date in an object named employee.
	 */
	private static void addNewEmployee() {
		int employeeId = EmployeeInformation.getEmployeeId();
		String employeeName = EmployeeInformation.getEmployeeName();
		double salary = EmployeeInformation.getEmployeeSalary();
		String phoneNumber = EmployeeInformation.getEmployeePhoneNumber();
		Date date = EmployeeInformation.getEmployeeJoiningDate();

		Employee employee = new Employee(employeeId, employeeName, salary, phoneNumber, date);
		EMPLOYEE_CONTROL.addNewEmployee(employee);
	}

	/**
	 * Showing all employee details.
	 */
	private static void viewEmployees() {
		EMPLOYEE_CONTROL.viewEmployees();
	}

	/**
	 * Deleting employee detail by using id.
	 */
	private static void deleteEmployee() {
		int employeeId = EmployeeInformation.getEmployeeId();

		EMPLOYEE_CONTROL.deleteEmployee(employeeId);
	}

	/**
	 * Updating various employee details using switch case for specific input given by user.
	 */
	public static void updateEmployeeDetails() {
		int choice;
		
		System.out.println("Choose option to update\n1.Employee Name\n2.Employee Salary\n3.Employee Phone Number\n4.Employee Joining Date");
		choice = EmployeeMain.SCANNER.nextInt();

		if (choice == 1) {
			EmployeeMain.updateEmployeeName();
		} else if (choice == 2) {
			EmployeeMain.updateEmployeeSalary();
		} else if (choice == 3) {
			EmployeeMain.updateEmployeePhoneNumber();
		} else if (choice == 4) {
			EmployeeMain.updateEmployeeJoiningDate();
		} else {
			System.out.println("Invalid choice");
		}
	}

	/**
	 * Updating employee name by using employee id.
	 */
	private static void updateEmployeeName() {
		int employeeId = EmployeeInformation.getEmployeeId();
		String employeeName = EmployeeInformation.getEmployeeName();
		Employee employee = new Employee();
		
		employee.setEmployeeId(employeeId);
		employee.setEmployeeName(employeeName);
		EMPLOYEE_CONTROL.updateEmployee(employee);
	}

	/**
	 * Updating employee salary by using employee id.
	 */
	private static void updateEmployeeSalary() {
		int employeeId = EmployeeInformation.getEmployeeId();
		double salary = EmployeeInformation.getEmployeeSalary();
		Employee employee = new Employee();
		
		employee.setEmployeeId(employeeId);
		employee.setSalary(salary);
		EMPLOYEE_CONTROL.updateEmployee(employee);
	}

	/**
	 * Updating employee phone number by using employee id.
	 */
	private static void updateEmployeePhoneNumber() {
		int employeeId = EmployeeInformation.getEmployeeId();
		String phoneNumber = EmployeeInformation.getEmployeePhoneNumber();
		Employee employee = new Employee();
		
		employee.setEmployeeId(employeeId);
		employee.setPhoneNumber(phoneNumber);
		EMPLOYEE_CONTROL.updateEmployee(employee);
	}

	/**
	 * Updating employee joining date by using employee id.
	 */
	private static void updateEmployeeJoiningDate() {
		int employeeId = EmployeeInformation.getEmployeeId();
		Date date = EmployeeInformation.getEmployeeJoiningDate();
		Employee employee = new Employee();

		employee.setEmployeeId(employeeId);
		employee.setDate(date);
		EMPLOYEE_CONTROL.updateEmployee(employee);
	}
}
