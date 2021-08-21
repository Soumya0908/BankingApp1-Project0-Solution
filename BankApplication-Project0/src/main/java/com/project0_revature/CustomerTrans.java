package com.project0_revature;

import java.util.Scanner;
import com.project0_revature.dao.CustomerDAO;
import com.project0_revature.dao.CustomerDAOImpl;
import com.project0_revature.model.Customer;

public class CustomerTrans {
	
	CustomerDAO customerDAO = new CustomerDAOImpl();
	BankingApp bankingApp=new BankingApp();
	Scanner sc=new Scanner(System.in);
	private boolean result;
	Customer customer = new Customer();
	
	public void startCustomerTrans(Customer customer){
	
		while (true) {
			System.out.println("welcome");
			System.out.println("enter your choice");
			System.out.println("1.GET BALANCE");
			System.out.println("2.UPDATE DETAILS");
			System.out.println("3.DEPOSIT");
			System.out.println("4.WITHDRAW");
			System.out.println("5.AMOUNTTRANSFER");
			System.out.println("6.LOGOUT");
			int choice = 0;
			choice = sc.nextInt();
			switch (choice) {
			
			case 1:
				
				System.out.println("Welcome to Get Balance section");
				System.out.println("Enter the Account Number:"); double accountNumber1=sc.nextDouble(); 
				System.out.println("Enter the Password:");String password1=sc.next();
				customer=customerDAO.isCustomerExists(accountNumber1,password1);
				  if(customer!=null) {	
					  customer = customerDAO.getBalance(accountNumber1,password1);
					  if(customer!=null) 
							System.out.println("#Customer with customer account number : " + customer.getCustomerAccountNumber()
							+ " , and current balance is"+customer.getCustomerBalance());
				  }
				else {	
					  
					System.out.println("Customer with AccountNumber:" +customer.getCustomerAccountNumber()+",does not exist");

					  }
					  
				break;
				
            case 2:
				
            	System.out.println("Welcome to update customer details section , please enter customer details to  update ##");
				 System.out.println("Enter the Account Number:"); double accountNumber=sc.nextDouble(); 
				 System.out.println("Enter the Password:");String password=sc.next();
				  customer=customerDAO.isCustomerExists(accountNumber,password);
				  if(customer==null)
					  
					 System.out.println("Customer with AccountNumber:" +customer.getCustomerAccountNumber()+",does not exist");
				  
				  else 
					System.out.println("Please enter customerName: ");
				    String customerName = sc.next();
					System.out.println("Please enter customerPassword: ");
					String customerPassword = sc.next();
					System.out.println("Please re-enter Password : ");
					String customerRePassword = sc.next();
					if(customerPassword.equals(customerRePassword)) {
					System.out.println("Please enter customerBalance: ");	
					customer = new Customer(customerName, accountNumber, customerPassword);
					System.out.println(customer);
					}
					else {
						System.out.println("PasswordMismatch");
					}
				  boolean result = customerDAO.updateCustomerDetails(customer);
				  if (result)
				  System.out.println("#Customer with customer account number : " +customer.getCustomerAccountNumber() + " , updated successfully"); 
				  else
				  System.out.println("#Customer with customer account number: " +customer.getCustomerAccountNumber()+ ", not updated successfully");
					
				break;
				
            case 3:
            	System.out.println("Welcome to deposit section, please enter amount for deposit");
				customer = customerDAO.amountDeposit(customer);
				//result = customerDAO.amountDeposit(customerAccountNumber);
				if (customer != null)
					System.out.println("#Customer with customer account number : " + customer.getCustomerAccountNumber()
							+ " , deposited amount successfully and new balance is"+customer.getCustomerBalance());
				else
					System.out.println("#Customer with customer account number: " + customer.getCustomerAccountNumber()
							+ ", deposit of amount was not successful");

				break;
				
            case 4:
            	System.out.println("Welcome to Withdraw section, please enter amount for Withdrawal");
//				customer = customerDAO.isCustomerExists();
					customer = customerDAO.amountWithdraw(customer);
						if (customer!= null) {
						System.out.println("#Customer with customer accountNumber : " + customer.getCustomerAccountNumber() + " , withdrawal was successful ");
						System.out.println("Available balance is:" +customer.getCustomerBalance());
						}else
								System.out.println("#Customer with customer accountNumber: " + customer.getCustomerAccountNumber() + ", withdrawal failed");

			break;
			
            case 5:
            	System.out.println("Welcome to transaction section, please enterthe details");
            	double amount = 0;
				double creditorId = 0;
				double debitorId = 0;
				customer = customerDAO.CustomerTransactions(customer);
				if (customer!= null) {
					System.out.println("Transaction successful");
					}else
							System.out.println("# transaction failed");

            	
            	
            	case 6:
				System.out.println("Thanks for using my Banking app");
				bankingApp.startBankingApp();
				System.exit(0);
			
				break;
            	
				
			}}}
}
