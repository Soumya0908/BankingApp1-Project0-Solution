package com.project0_revature.dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

import com.project0_revature.model.Customer;
import com.project0_revature.util.DBConnection;

public class CustomerDAOImpl implements CustomerDAO {
	
	Customer customer;
	
	Connection connection = DBConnection.getDBConnection();
	
	private final String ADD_CUSTOMER_QUERY = "insert into bank.customers values(?,?,?,?,?)";
	private final String FIND_DETAILS_BY_ACCNO ="select * from bank.customers where customerAccountNumber=? and customerPassword=?";
	private final String VERIFY_CUSTOMER_DETAILS = "select customerAccountNumber,customerPassword from bank.customers"
			+ " where customerAccountNumber = ? and customerPassword=?";
	private final String UPDATE_CUSTOMER_QUERY = "update bank.customers set customerName =?, customerPassword= ? where customerAccountNumber= ?";
	private final String AMOUNT_WITHDRAWAL = "update bank.customers set customerBalance = customerBalance - ? where customerAccountNumber = ?";
	private final String AMOUNT_DEPOSIT = "update bank.customers set customerBalance = customerBalance + ? where customerAccountNumber = ? and customerPassword=? ";
	private final String AMOUNT_TRANSACTIONS_QUERY = "call bank.transferAmountAndReturn(?,?,?,?,?)";
	private final String VERIFY_VIEW_CUSTOMER_DETAILS ="select customerId, customerAccountNumber, customerName, customerBalance from bank.customers where customerId =?";
	private final String GET_BALANCE = "select customerBalance from bank.customers where customerAccountNumber = ? and customerPassword=?";
	private final String VIEW_BALANCE ="select customerId, customerAccountNumber, customerName, customerBalance from bank.customers where customerAccountNumber =?";
	
	public boolean addCustomerDetails(Customer customer) {
		int res = 0;
		System.out.println(customer);
		try {
		PreparedStatement statement  = connection.prepareStatement(ADD_CUSTOMER_QUERY);
		statement.setInt(1, customer.getCustomerId());
		statement.setDouble(2, customer.getCustomerAccountNumber());
		statement.setString(3, customer.getCustomerName());
		statement.setString(4, customer.getCustomerPassword());
		statement.setDouble(5, customer.getCustomerBalance());
		System.out.println("hello");
		res = statement.executeUpdate();
		System.out.println(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Customer with customerid : " + customer.getCustomerId() + " already  exists");
		}
		//waitt
		if(res == 0) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean updateCustomerDetails(Customer customer) {
		int res = 0;
		PreparedStatement statement = null;
		System.out.println(customer);
		try {
			 statement = connection.prepareStatement(UPDATE_CUSTOMER_QUERY);
			//statement.setInt(2,customer.getCustomerId());
			statement.setDouble(3, customer.getCustomerAccountNumber());;
			statement.setString(1, customer.getCustomerName());
			statement.setString(2, customer.getCustomerPassword());
			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(res == 0)
		    return false;
		else
			//customer=isCustomerExists(customer.getCustomerAccountNumber(), customer.getCustomerPassword());
			System.out.println(customer);
		    return true;
	}

	public Customer amountWithdraw(Customer customer) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter Amount:");
		double amount = sc.nextDouble();
		double res = 0;
		PreparedStatement statement ;
		try {
		statement = connection.prepareStatement(AMOUNT_WITHDRAWAL);
		statement.setDouble(1, amount);
		statement.setDouble(2, customer.getCustomerAccountNumber());
		res=statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res == 0)
		    return null;
		else {
			customer=isCustomerExists(customer.getCustomerAccountNumber(), customer.getCustomerPassword());
			return customer;
		}
	}

	public Customer amountDeposit(Customer customer) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter Amount:");
		double amount = sc.nextDouble();
		int res = 0;
		PreparedStatement statement;
		try {
		statement = connection.prepareStatement(AMOUNT_DEPOSIT);
		statement.setDouble(1, amount);
		statement.setDouble(2, customer.getCustomerAccountNumber());
		statement.setString(3, customer.getCustomerPassword());
		
		res=statement.executeUpdate();
//		System.out.println(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res == 0)
		    return null;
		else
			customer=isCustomerExists(customer.getCustomerAccountNumber(), customer.getCustomerPassword());
			return customer;
		
	}

	public Customer getCustomerByACCNo(double customerAccountNumber, String customerPassword) {
			Customer customer = new Customer();
			ResultSet res = null;
			int check = 0;
			try {
			PreparedStatement statement = connection.prepareStatement(FIND_DETAILS_BY_ACCNO);
			statement.setDouble(1, customerAccountNumber);
			statement.setString(2, customerPassword);
			res = statement.executeQuery();
			if(res.next()) {
			customer =new Customer(res.getInt(1),res.getDouble(2), res.getString(3),res.getDouble(4));
			 check=1;
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(check==1)
				return customer;
			else
				return null;
	}
	

	public Customer isCustomerExists(double accountNumber, String password) {
		boolean result = false;
		ResultSet ress=null;
		int res1=0,check=0;

		try {
			PreparedStatement stat = connection.prepareStatement(VERIFY_CUSTOMER_DETAILS);
			stat.setDouble(1, accountNumber);
			stat.setString(2, password);
			ResultSet res = stat.executeQuery();

			if (res.next()) {
				res1 = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res1==1) {
			try {
				PreparedStatement stat=connection.prepareStatement(FIND_DETAILS_BY_ACCNO);
				stat.setDouble(1, accountNumber);
				stat.setString(2, password);
				ress=stat.executeQuery();
				if(ress.next()) {
					customer =new Customer(ress.getInt(1),ress.getDouble(2),ress.getString(3),ress.getString(4),ress.getDouble(5));
					 check=1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(check==1)
			return customer;
		else
			return null;


	}

	public Customer CustomerTransactions(Customer customer) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter debitAccount:");
		int debitAccountNumber = sc.nextInt();
		System.out.println("Please enter creditAccount:");
		int creditAccountNumber = sc.nextInt();
		System.out.println("Please enter Amount to be transfered:");
		int amount1 = sc.nextInt();
		int debitorBalance = 0, creditorBalance = 0;
		boolean res=false;
		try {
		CallableStatement statement = connection.prepareCall(AMOUNT_TRANSACTIONS_QUERY);
		statement.setInt(1, debitAccountNumber);	
		statement.setInt(2, creditAccountNumber);
		statement.setInt(3, amount1);
		statement.registerOutParameter(4, Types.INTEGER);
		statement.setInt(4, debitorBalance);
		statement.registerOutParameter(5, Types.INTEGER);
		statement.setInt(5, creditorBalance);
		res=statement.execute();
		debitorBalance = statement.getInt(4);
		creditorBalance = statement.getInt(5);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res == true)
		    return null;
		else {
			System.out.println("Transferred INR :"+amount1 +" from account : "+debitAccountNumber+" to account : "+creditAccountNumber );
			System.out.println("DEBITOR BALANCE:"+ debitorBalance );
			System.out.println("CREDITOR BALANCE:" + creditorBalance);
			customer=isCustomerExists(customer.getCustomerAccountNumber(), customer.getCustomerPassword());
			return customer;
		}
			

	}

	public Customer customerWithIdExists(int customerId) {
//		Customer customer = new Customer();
//		ResultSet res = null;
		int check = 0;
		try {
		PreparedStatement statement = connection.prepareStatement(VERIFY_VIEW_CUSTOMER_DETAILS);
//		System.out.println(customerId);
		statement.setInt(1, customerId);
		ResultSet res = statement.executeQuery();
		if(res.next()) {
//			System.out.println("hello hi");
		customer =new Customer(res.getInt(1),res.getDouble(2), res.getString(3),res.getDouble(4));
		 check=1;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(check==1) {
			System.out.println(customer);
			return customer;
		}
			
		else {
			System.out.println(customer);
			return null;
		}
			
	}

	public Customer getBalance(double accountNumber, String password) {
		// TODO Auto-generated method stub
//		Customer customer = new Customer();
		ResultSet res=null;
		try {
			PreparedStatement stat = connection.prepareStatement(GET_BALANCE);
			stat.setDouble(1, accountNumber);
			stat.setString(2, password);
			 res = stat.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res == null)
		    return null;
		else {
	//		customer=isCustomerExists(customer.getCustomerAccountNumber(), customer.getCustomerPassword());
			return customer;
		}
	}

	public Customer viewBalance(double accountNumber) {
		// TODO Auto-generated method stub
//		Customer customer = new Customer();
		ResultSet res=null;
		int check=0;
		try {
			PreparedStatement stat = connection.prepareStatement(VIEW_BALANCE);
			stat.setDouble(1, accountNumber);
			 res = stat.executeQuery();

			 if(res.next()) {
					customer =new Customer(res.getInt(1),res.getDouble(2),res.getString(3),res.getDouble(4));
					 check=1;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(check == 0)
		    return null;
		else {
			//customer=isCustomerExists(customer.getCustomerAccountNumber(), customer.getCustomerPassword());
			return customer;
		}
	}

	}


