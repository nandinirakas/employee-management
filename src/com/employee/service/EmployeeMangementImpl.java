package com.employee.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import com.employee.model.Employee;

/**
 * Implementation of the methods that are given in interface.
 * Created a linked hashmap collection for storing details to maintain order and better performance.
 */
public class EmployeeMangementImpl implements EmployeeManagement {
	private static final Map<Integer, Employee> EMPLOYEE_DETAILS = new LinkedHashMap<>();

	/**
	 * Adding a new employee.
	 * 
	 * @param employeeId 
	 * @param employee Object contains name, salary, phone number and date
	 */
	public void addNewEmployee(Employee employee) {
		int employeeId = employee.getEmployeeId();
				
		if (EMPLOYEE_DETAILS.containsKey(employeeId)) {
            System.out.println("The given Id already present, please enter new id");
        } else {
        	EMPLOYEE_DETAILS.put(employeeId, employee);
        }
	}

	/**
	 * Showing all employee details that are stored in the list by using for each.
	 * Entry will give both key and value.
	 */
	public void viewEmployees() {
		
		for (Entry<Integer, Employee> entry : EMPLOYEE_DETAILS.entrySet()) {
		    Integer key = entry.getKey();
		    Employee value = entry.getValue();
		   	System.out.println(String.format("%s %s", String.valueOf(key), value));
		}
	}

	/**
	 * Deleting each employee details that are stored in the list
	 * based on employee id.
	 * 
	 * @param employeeId
	 */
	public void deleteEmployee(int employeeId) {
		
		if (EMPLOYEE_DETAILS.containsKey(employeeId)) {
			EMPLOYEE_DETAILS.remove(employeeId);
        } else {
        	System.out.println("The given Id already present, please enter new id");
        }
	}

	/**
	 * Replacing each employee details based on employee id.
	 * 
	 * @param employeeId
	 * @param employee
	 */
	public void updateEmployee(Employee employee) {
		int employeeIdKey = employee.getEmployeeId();
		
		if (EMPLOYEE_DETAILS.containsKey(employeeIdKey)) {
			Employee employeeData = EMPLOYEE_DETAILS.get(employeeIdKey);
		
				if (employee.getEmployeeName() != null) { 
					employeeData.setEmployeeName(employee.getEmployeeName());
				} else if (employee.getSalary() != 0) {
					employeeData.setSalary(employee.getSalary());
				} else if (employee.getPhoneNumber() != null) {
					employeeData.setPhoneNumber(employee.getPhoneNumber());
				} else if (employee.getDate() != null) {
					employeeData.setDate(employee.getDate());
				}
		 }
	}
}
