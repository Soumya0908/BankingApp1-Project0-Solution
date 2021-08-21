package com.project0_revature.dao;

import com.project0_revature.model.Customer;

public interface CustomerDAO {
	
	public boolean addCustomerDetails(Customer customer);
	public boolean updateCustomerDetails(Customer customer);
	public Customer amountWithdraw(Customer customer);
	public Customer amountDeposit(Customer customer);
	public Customer getCustomerByACCNo(double customerAccountNumber, String customerPassword);
	public Customer isCustomerExists(double accountNumber,String password);
	public Customer CustomerTransactions(Customer customer);
	public Customer customerWithIdExists(int customerId);
	public Customer getBalance(double accountNumber, String password);
	public Customer viewBalance(double accountNumber);

}
