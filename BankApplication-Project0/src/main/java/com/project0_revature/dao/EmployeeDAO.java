package com.project0_revature.dao;

import com.project0_revature.model.Customer;
import com.project0_revature.model.Employee;

public interface EmployeeDAO {
	public boolean addEmployeeDetails(Employee employee);
	public Employee isEmployeeExists(int employeeId, String employeePassword);
	public boolean updateEmployeeDetails(Employee employee);
	public Employee getEmployeeId(int employeeId, String employeePassword);
	
}
