package com.employee.model;

import java.sql.Date;

/**
 * Gives information about employee
 */
public class Employee {
    private int employeeId;
    private String employeeName;
    private double salary;
    private String phoneNumber;
    private Date date;

    public Employee() {
        super();
    }

    public Employee(int employeeId, String employeeName, double salary, String phoneNumber, Date date) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.date = date;
    }
	
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString() {
        return String.format("Employee id :%s \nName :%s \nSalary :%s \nPhone number :%s \nJoining date :%s \n", employeeId, employeeName, salary, phoneNumber, date);
    }
}
