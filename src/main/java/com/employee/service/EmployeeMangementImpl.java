package com.employee.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.employee.exception.CustomException.IdAlreadyAvailableException;
import com.employee.exception.CustomException.IdNotFoundException;
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
     * @param employee Object contains id, name, salary, phone number and date
     * @throws IdAlreadyAvailableException 
     */
    public void addNewEmployee(final Employee employee) {
     
        if (EMPLOYEE_DETAILS.containsKey(employee.getEmployeeId())) {
            throw new IdAlreadyAvailableException("Id already present, enter new id");
        } else {
            EMPLOYEE_DETAILS.put(employee.getEmployeeId(), employee);
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
     * Deleting each employee detail that are stored in the list
     * based on employee id.
     * 
     * @param employeeId
     * @throws IdNotFoundException 
     */
    public void deleteEmployee(final int employeeId) {
        
        if (EMPLOYEE_DETAILS.containsKey(employeeId)) {
            EMPLOYEE_DETAILS.remove(employeeId);
        } else {
            throw new IdNotFoundException("Id not found!!");
        }
    }
    
    /**
     * Update each employee detail that are stored in the list.
     * @throws IdNotFoundException 
     * 
     */
    public void updateEmployeeDetails(final Employee employee) {
        int employeeIdKey = employee.getEmployeeId();
        
            if (EMPLOYEE_DETAILS.containsKey(employeeIdKey)) {
            Employee employeeData = EMPLOYEE_DETAILS.get(employeeIdKey);
            
                if (employee.getEmployeeName() != null) { 
                    employeeData.setEmployeeName(employee.getEmployeeName());
                }
            
                if (employee.getSalary() != 0) {
                    employeeData.setSalary(employee.getSalary());
                }
            
                if (employee.getPhoneNumber() != null) {
                    employeeData.setPhoneNumber(employee.getPhoneNumber());
                }
            
                if (employee.getDate() != null) {
                   employeeData.setDate(employee.getDate());
                }
            } else {
                throw new IdNotFoundException("Id not found!!");      
            }
    }
}