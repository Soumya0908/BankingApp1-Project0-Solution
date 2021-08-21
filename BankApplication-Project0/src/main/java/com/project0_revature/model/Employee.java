package com.project0_revature.model;

public class Employee {
	private int employeeId;
	private String employeeName;
	private String employeePassword;
	private double employeeSalary;
	public Employee() {
		super();
	}
	public Employee(int employeeId, String employeeName, String employeePassword, double employeeSalary) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeePassword = employeePassword;
		this.employeeSalary = employeeSalary;
	}
	
	public Employee(String employeeName, String employeePassword, double employeeSalary) {
		super();
		this.employeeName = employeeName;
		this.employeePassword = employeePassword;
		this.employeeSalary = employeeSalary;
	}
	
	public Employee(int employeeId, String employeePassword) {
		super();
		this.employeeId = employeeId;
		this.employeePassword = employeePassword;
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
	public String getEmployeePassword() {
		return employeePassword;
	}
	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}
	public double getEmployeeSalary() {
		return employeeSalary;
	}
	public void setEmployeeSalary(double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeePassword="
				+ employeePassword + ", employeeSalary=" + employeeSalary + "]";
	}
	
}
