package com.employee.controller;

import com.employee.model.Employee;
import com.employee.service.EmployeeManagement;
import com.employee.service.EmployeeMangementImpl;

public class EmployeeController {
	private final EmployeeManagement EMPLOYEE_SERVICE = new EmployeeMangementImpl();

	public void addNewEmployee(Employee employee) {
		EMPLOYEE_SERVICE.addNewEmployee(employee);
	}

	public void viewEmployees() {
		EMPLOYEE_SERVICE.viewEmployees();
	}

	public void deleteEmployee(int employeeId) {
		EMPLOYEE_SERVICE.deleteEmployee(employeeId);
	}

	public void updateEmployee(Employee employee) {
		EMPLOYEE_SERVICE.updateEmployee(employee);
	}
}
