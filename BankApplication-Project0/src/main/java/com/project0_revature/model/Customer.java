package com.project0_revature.model;

import java.io.Serializable;

public class Customer implements Serializable {
	private int customerId;
	private double customerAccountNumber;
	private String customerName;
	private String customerPassword;
	private double customerBalance;
	public Customer() {
		super();
	}
	public Customer(int customerId, double customerAccountNumber, String customerName, String customerPassword,
			double customerBalance) {
		super();
		this.customerId = customerId;
		this.customerAccountNumber = customerAccountNumber;
		this.customerName = customerName;
		this.customerPassword = customerPassword;
		this.customerBalance = customerBalance;
	}
	
	public Customer(int customerId, double customerAccountNumber, String customerName, double customerBalance) {
		super();
		this.customerId = customerId;
		this.customerAccountNumber = customerAccountNumber;
		this.customerName = customerName;
		this.customerBalance = customerBalance;
	}
	public Customer(String customerName,String customerPassword , double customerBalance) {
		super();
		this.customerName = customerName;
		this.customerPassword = customerPassword;
		this.customerBalance = customerBalance;
	}
	
	public Customer(String customerName, double customerAccountNumber,String customerPassword ) {
		// TODO Auto-generated constructor stub
		super();
		this.customerName = customerName;
		this.customerAccountNumber = customerAccountNumber;
		this.customerPassword = customerPassword;
	}
	
	public Customer(double customerAccountNumber, String customerPassword) {
		super();
		this.customerAccountNumber = customerAccountNumber;
		this.customerPassword = customerPassword;
	}
	public Customer(double customerAccountNumber, String customerName, String customerPassword) {
		super();
		this.customerAccountNumber = customerAccountNumber;
		this.customerName = customerName;
		this.customerPassword = customerPassword;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public double getCustomerAccountNumber() {
		return customerAccountNumber;
	}
	public void setCustomerAccountNumber(double customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public double getCustomerBalance() {
		return customerBalance;
	}
	public void setCustomerBalance(double customerBalance) {
		this.customerBalance = customerBalance;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerAccountNumber=" + customerAccountNumber
				+ ", customerName=" + customerName + ", customerPassword=" + customerPassword + ", customerBalance="
				+ customerBalance + "]";
	}

}
