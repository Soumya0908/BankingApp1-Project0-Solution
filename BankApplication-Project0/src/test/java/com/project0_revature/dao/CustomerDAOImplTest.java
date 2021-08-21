package com.project0_revature.dao;

import com.project0_revature.model.Customer;

import junit.framework.TestCase;

public class CustomerDAOImplTest extends TestCase {
	CustomerDAO customerDAO;

	public CustomerDAOImplTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		customerDAO = new CustomerDAOImpl();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		customerDAO = null;
		super.tearDown();
	}

	public void testAddCustomerDetails() {
		
		  Customer customer = new Customer(2,9999,"sony","sony123",10000);
		  assertEquals(true, customerDAO.addCustomerDetails(customer));
		  //fail("Not yet implemented");
		 
	}

	public void testUpdateCustomerDetails() {
		
		  double customerAccountNumber=9999; 
		  Customer customer = new Customer(customerAccountNumber, "Sony", "Sony123");
		  customer.setCustomerAccountNumber(customerAccountNumber);
		  assertEquals(true,customerDAO.updateCustomerDetails(customer));
		  //fail("Not yet implemented");
		 
	}

	public void testAmountWithdraw() {
		fail("Not yet implemented");
	}

	public void testAmountDeposit() {
		fail("Not yet implemented");
	}

	public void testGetCustomerByACCNo() {
		double customerAccountNumber = 999;
		String customerPassword="Sandy123@";
		Customer customer = new Customer(customerAccountNumber, customerPassword);
		customer.setCustomerAccountNumber(customerAccountNumber);
		customer.setCustomerPassword(customerPassword);
		assertEquals(true, customerDAO.getCustomerByACCNo(customerAccountNumber, customerPassword));	
		//fail("Not yet implemented");
	}

	public void testIsCustomerExists() {
		double accountNumber = 999;
		String password="Sandy123@";
		assertEquals(true, customerDAO.isCustomerExists(accountNumber,  password));	
		//fail("Not yet implemented");
	}

	public void testCustomerTransactions() {
		fail("Not yet implemented");
	}

}
