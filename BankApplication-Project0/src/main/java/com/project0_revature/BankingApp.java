package com.project0_revature;
import java.util.Scanner;
import java.util.logging.Logger;

import com.project0_revature.dao.CustomerDAO;
import com.project0_revature.dao.CustomerDAOImpl;
import com.project0_revature.dao.EmployeeDAO;
import com.project0_revature.dao.EmployeeDAOImpl;
import com.project0_revature.model.Customer;
import com.project0_revature.model.Employee;
import com.project0_revature.EmployeeOperations;
import com.project0_revature.CustomerTrans;


public class BankingApp {
	int choice = 0;
	Scanner sc = new Scanner(System.in);
	CustomerDAO customerDAO = new CustomerDAOImpl();
	EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	boolean result;
	Customer customer = new Customer();
	Employee employee = new Employee();
	private static Logger logger = Logger.getLogger("ProductApp");
			    
 public void startBankingApp() {
	 System.out.println("Please enter your name:");
	    String username = sc.next();
	    logger.info("Welcome."+username+"you logged just now in:"+new java.util.Date() );
	    System.out.println("Welcome,"+username);
	 while (true) {
			System.out.println("**** MENU ****");
			System.out.println("1.ADD EMPLOYEE DETAILS");
			System.out.println("2.BANK-APPLICATION OF CUSTOMER");
			System.out.println("3.EMPLOYEE LOGIN");
			System.out.println("4.CUSTOMER LOGIN");
			System.out.println("5.GET CUSTOMER INFO");
			System.out.println("10.E X I T ");
			System.out.println("Enter your choice : ");
			choice = sc.nextInt();
			
			switch (choice) {
			
			case 1:
				System.out.println("Welcome to Bank Application Section , please enter employee details ##");
				employee=acceptEmployeeDetails();
				System.out.println(employee);
				result = employeeDAO.addEmployeeDetails(employee);
				System.out.println(result);
					if (result)
						System.out.println("Employee with employeeId :" + employee.getEmployeeId() + " , saved successfully");
					else
						System.out.println("Employee with employeeId : " + employee.getEmployeeId() + ", not saved successfully");
				
				break;
				
			
			case 2:
				System.out.println("Welcome to Bank Application Section , please enter customer details to open account##");
				customer=acceptCustomerDetails();
				System.out.println(customer);
				boolean result1 = customerDAO.addCustomerDetails(customer);
				System.out.println(result1);
				if (result1) 
						System.out.println("Customer with customerId : " + customer.getCustomerId() + " , saved successfully");
					else
						System.out.println("Customer with customerId : " + customer.getCustomerId() + ", not saved successfully");
				break;
				
			case 3:
				System.out.println("enter EmployeeId:"); 
				int employeeId=sc.nextInt();
				System.out.println("enter Password"); 
				String employeePassword=sc.next();
				employee =employeeDAO.isEmployeeExists(employeeId,employeePassword);
				if(employee!=null) {
					EmployeeOperations employeeoperations=new EmployeeOperations();
					employeeoperations.startEmployeeOperations(employee);
				}
				else 
					System.out.println("sorry does not exist");
				
				break;
				
			case 4:
				
				System.out.println("enter Customer Account Number"); 
				double accountNumber=sc.nextDouble();
				System.out.println("enter Customer password"); 
				String password=sc.next();
				customer=customerDAO.isCustomerExists(accountNumber,password);
				if(customer!=null) {
					CustomerTrans customerTrans=new CustomerTrans();
					customerTrans.startCustomerTrans(customer);
				}
				else {
					System.out.println("sorry does not exist");
				}
				break;
				
			case 5:
				System.out.println("enter accountNumber:"); 
				double accountNumber1=sc.nextDouble();
				System.out.println("Enter the Password:");
				String Password=sc.next();
				customer = customerDAO.isCustomerExists(accountNumber1, Password);
				if(customer==null)
					 System.out.println("Customer with customerAccNO:" +customer.getCustomerAccountNumber()+", does not exist");
				 else
				if(result = customerDAO.getCustomerByACCNo(accountNumber1, Password)!=null)
				System.out.println("Printing the employee details");
				System.out.println(customer);
				break;
				
			case 10:
				System.out.println("Thanks for using my Banking app");
				System.exit(0);
				break;
				
			}
	 }
 }
 public Employee acceptEmployeeDetails(){
		
		System.out.println("Please enter Employeeid : ");
		int employeeId = sc.nextInt();
		System.out.println("Please enter EmployeeName : ");
		String employeeName = sc.next();
		System.out.println("Please enter EmployeePassword: ");
		String employeePassword = sc.next();
		System.out.println("Please re-enter Password: ");
		String employeeRePassword = sc.next();
		if(employeePassword.equals(employeeRePassword)) { 	
		System.out.println("Please enter Salary : ");
		double employeeSalary  = sc.nextDouble();
		if(employeeSalary<=0) {
			System.out.println("Salary cant be less than or equal to zero");
		}
		 employee = new Employee(employeeId,  employeeName, employeePassword, employeeSalary);
		 System.out.println(employee);
		}
		else
			System.out.println("Password Mismatch");
	
		return employee;
 }
 
			public Customer acceptCustomerDetails(){
				System.out.println("Please enter Customerid : ");
				int customerId = sc.nextInt();
				System.out.println("Please enter CustomerAccountNumber : ");
				double customerAccountNumber = sc.nextDouble();
				System.out.println("Please enter customerName: ");
				String customerName = sc.next();
				System.out.println("Please enter customerPassword: ");
				String customerPassword = sc.next();
				System.out.println("Please re-enter Password: ");
				String customerRePassword = sc.next();
				if(customerPassword.equals(customerRePassword)) {
					
				System.out.println("Please enter customerBalance: ");
				double customerBalance = sc.nextDouble();
				
				if(customerBalance<500) {
					System.out.println("Amount should be not less than 500");
				}
				
				 customer = new Customer(customerId,  customerAccountNumber, customerName, customerPassword, customerBalance);
				 System.out.println(customer);
				}
				 else
					 System.out.println("Password Mismatch");
				
				
				return customer;
			}
 }
