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
     */
    public boolean addNewEmployee(final Employee employee) {
     
        if (EMPLOYEE_DETAILS.containsKey(employee.getEmployeeId())) {
            throw new IdAlreadyAvailableException("Id already present, enter new id");
        } else {
            EMPLOYEE_DETAILS.put(employee.getEmployeeId(), employee);
            return true;
        }
    }

    /**
     * Showing all employee details that are stored in the list by using for each.
     * Entry will give both key and value.
     */
    public Map<Integer, Employee> viewEmployees() {   
        
        for (Entry<Integer, Employee> entry : EMPLOYEE_DETAILS.entrySet()) {
            System.out.println(entry);
        }
        return EMPLOYEE_DETAILS;
    }

    /**
     * Deleting each employee detail that are stored in the list
     * based on employee id.
     * 
     * @param employeeId
     */
    public boolean deleteEmployee(final int employeeId) {
        
        if (EMPLOYEE_DETAILS.containsKey(employeeId)) {
            EMPLOYEE_DETAILS.remove(employeeId);
            return true;
        } else {
            throw new IdNotFoundException("Id not found!!");
        }
    }
    
    /**
     * Update each employee detail that are stored in the list.
     * 
     */
    public boolean updateEmployeeDetails(final Employee employee) {
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
            return true;
            } else {
                throw new IdNotFoundException("Id not found!!");      
            }
    }
}