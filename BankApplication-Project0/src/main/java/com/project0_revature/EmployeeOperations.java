package com.project0_revature;

import java.util.Scanner;
import com.project0_revature.BankingApp;
import com.project0_revature.dao.CustomerDAO;
import com.project0_revature.dao.CustomerDAOImpl;
import com.project0_revature.dao.EmployeeDAO;
import com.project0_revature.dao.EmployeeDAOImpl;
import com.project0_revature.model.Customer;
import com.project0_revature.model.Employee;

public class EmployeeOperations {
	EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	CustomerDAO customerDAO = new CustomerDAOImpl();
	BankingApp bankingApp=new BankingApp();
	Scanner sc=new Scanner(System.in);
	Employee employee = new Employee();
	Customer customer = new Customer();
	public void startEmployeeOperations(Employee employee)  {
		while (true) {
			System.out.println("welcome to Employee Session");
			System.out.println("enter your choice");
			System.out.println("1.Get employee Details");
			System.out.println("2.Update Employee Details");
			System.out.println("3.VERIFY CUSTOMER DETAILS");
			System.out.println("4.VIEW CUSTOMER DETAILS");
			System.out.println("5.Logout");
			int choice = 0;
			choice = sc.nextInt();
			boolean result;
			switch (choice) {
			
			case 1:
				System.out.println("enter EmployeeId:"); 
				int employeeId=sc.nextInt();
				System.out.println("Enter the Password:");String Password=sc.next();
				employee=employeeDAO.isEmployeeExists(employeeId, Password);
				 if(employee==null)
					 System.out.println("Employee with EmployeeId:" +employee.getEmployeeId()+", does not exist");
				 else
				if(result = employeeDAO.getEmployeeId(employeeId, Password) != null)
				System.out.println("Printing the employee details");
				System.out.println(employee);
				break;
				
			case 2:
				System.out.println("Welcome to update employee details section , please enter employee details to  update ##");
				 System.out.println("Enter the EmployeeId:");int employeeId1 =sc.nextInt(); 
				 System.out.println("Enter the Password:");String password1=sc.next();
				 employee=employeeDAO.isEmployeeExists(employeeId1, password1);
				 if(employee==null)
					 System.out.println("Employee with EmployeeId:" +employee.getEmployeeId()+", does not exist");
				 else
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
					 employee = new Employee( employeeId1,employeeName, employeePassword, employeeSalary);
					 System.out.println(employee);
					}
					else
						System.out.println("Password Mismatch");
					result = employeeDAO.updateEmployeeDetails(employee);
				if (result)
					System.out.println("#Employee with EmployeeId : " + employee.getEmployeeId() + ",updated successfully");
				else
					System.out.println("#Employee with EmployeeId: " +employee.getEmployeeId()+ ", not updated successfully");
				break;
				
			case 3:
				System.out.println("Welcome to customer verification section");
				System.out.println("Enter the Customer Id:"); int customerId = sc.nextInt();
				customer = customerDAO.customerWithIdExists(customerId);
				if(customer==null) {
					System.out.println("Customer with customerId:"+customerId+",does not exist");
					System.out.println("You can create your account");
				}
				else
					System.out.println("Customer with CustomerId:"+customer.getCustomerId()+",already exists");
				break;
			case 4:
				System.out.println("Welcome to Customer View Balance Section");
				System.out.println("Enter the CustomerAccountNumber:"); double customerAccountNumber = sc.nextDouble();
				customer = customerDAO.viewBalance(customerAccountNumber);
				if(customer==null) {
					System.out.println("Customer with CustomerAccountNumber:"+customer.getCustomerAccountNumber()+",does not exist");
				}
				else
					System.out.println("Customer with CustomerAccountNumber:"+customer.getCustomerAccountNumber());
				    System.out.println(customer);
				break;
				
			case 5:
				System.out.println("Thanks for using my Banking app");
				bankingApp.startBankingApp();
				System.exit(0);
				break;
				
}}}
	}
