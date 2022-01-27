package com.employee.controller;

import com.employee.dao.EmployeeDatabase;
import com.employee.model.Employee;
import com.employee.service.EmployeeManagement;
import com.employee.service.EmployeeMangementImpl;

public class EmployeeController {
	private final EmployeeManagement EMPLOYEE_SERVICE = new EmployeeMangementImpl();
	private final EmployeeDatabase EMPLOYEE_DATABASE = new EmployeeDatabase();

	public void addNewEmployee(Employee employee) {
		EMPLOYEE_DATABASE.addNewEmployee(employee);
		EMPLOYEE_SERVICE.addNewEmployee(employee);
	}

	public void viewEmployees() {
		EMPLOYEE_DATABASE.viewEmployees();
		EMPLOYEE_SERVICE.viewEmployees();
	}

	public void deleteEmployee(int employeeId) {
		EMPLOYEE_DATABASE.deleteEmployee(employeeId);
		EMPLOYEE_SERVICE.deleteEmployee(employeeId);
	}

	public void updateEmployee(Employee employee) {
		EMPLOYEE_DATABASE.updateEmployee(employee);
		EMPLOYEE_SERVICE.updateEmployee(employee);
	}
}
