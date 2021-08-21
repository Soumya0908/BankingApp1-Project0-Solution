
package com.project0_revature.dao;

import com.project0_revature.model.Employee;

import junit.framework.TestCase;

public class EmployeeDAOImplTest extends TestCase {
    EmployeeDAO employeeDAO;
   
	public EmployeeDAOImplTest(String name) {	
		super(name);
			}

	protected void setUp() throws Exception {
		employeeDAO = new EmployeeDAOImpl();
		
				super.setUp();
	}

	protected void tearDown() throws Exception {
		employeeDAO = null;
		super.tearDown();
	}

	public void testAddEmployeeDetails() {
		
		  Employee employee = new Employee(105, "sss", "s123", 2500);
		  assertEquals(true, employeeDAO.addEmployeeDetails(employee));
		 
	}

	public void testIsEmployeeExists() {
		int employeeId = 100;
		String employeePassword="john1";
		assertEquals(true, employeeDAO.isEmployeeExists(employeeId, employeePassword));	
		//fail("Not yet implemented");
	}


	public void testUpdateEmployeeDetails() {
		int employeeId = 103;
		Employee employee = new Employee(employeeId, "Sam", "Sam123", 40000);
		employee.setEmployeeId(employeeId);
		assertEquals(true, employeeDAO.updateEmployeeDetails(employee));
		//fail("Not yet implemented");
	}
    
	public void testGetEmployeeId() {
		int employeeId = 103;
		String employeePassword="Sam123";
		assertEquals(true,employeeDAO.getEmployeeId(employeeId, employeePassword));	
		//fail("Not yet implemented");
		
	}

}
