package com.project0_revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.project0_revature.model.Customer;
import com.project0_revature.model.Employee;
import com.project0_revature.util.DBConnection;


public class EmployeeDAOImpl implements EmployeeDAO {
	private static Logger logger = Logger.getLogger("EmployeeDAOImpl");
	Employee employee;
	Connection connection = DBConnection.getDBConnection();
	private final String ADD_EMPLOYEES_QUERY = "insert into bank.employees values (?,?,?,?)";
	private final String VERIFY_EMPLOYEE_DETAILS = "select employeeId, employeePassword from bank.employees where employeeId = ? and employeePassword=?";
	private final String VIEW_EMPLOYEE_DETAILS = "select * from bank.employees where employeeId = ? and employeePassword=?";
	private final String UPDATE_EMPLOYEE_QUERY = "update bank.employees set employeeName = ?, employeePassword=?, employeeSalary= ? where employeeId= ? ";
	
	public boolean addEmployeeDetails(Employee employee) {
		int res = 0;
		try {
		PreparedStatement statement = connection.prepareStatement(ADD_EMPLOYEES_QUERY);
		statement.setString(2, employee.getEmployeeName());
		statement.setString(3, employee.getEmployeePassword());
		statement.setInt(1, employee.getEmployeeId());
		statement.setDouble(4, employee.getEmployeeSalary());
		res = statement.executeUpdate();
		System.out.println(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Employee with employeeId : " + employee.getEmployeeId() + " already  exists");
		}
		if(res == 0) {
			return false;
		}
		else {
			return true;
		}
	}

public Employee isEmployeeExists(int employeeId,String employeePassword) {
		
		boolean result = false;
		ResultSet ress=null;
		int res1=0,check=0;

		try {
			PreparedStatement stat = connection.prepareStatement( VERIFY_EMPLOYEE_DETAILS);
			stat.setInt(1, employeeId );
			stat.setString(2, employeePassword);
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
				PreparedStatement stat=connection.prepareStatement(VIEW_EMPLOYEE_DETAILS);
				stat.setInt(1, employeeId);
				stat.setString(2, employeePassword);
				ress=stat.executeQuery();
				if(ress.next()) {
					employee =new Employee(ress.getInt(1),ress.getString(2),ress.getString(3),ress.getDouble(4));
					 check=1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(check==1)
			return employee;
		else
			return null;
		

	}

	public boolean updateEmployeeDetails(Employee employee) {
		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_QUERY);
			statement.setInt(4, employee.getEmployeeId());
			statement.setString(2, employee.getEmployeePassword());
			statement.setString(1, employee.getEmployeeName());			
			statement.setDouble(3, employee.getEmployeeSalary());
//			statement.setString(5, employee.getEmployeePassword());			
			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (res == 0)
			return false;
		else
			System.out.println(employee);
			return true;
	}

	public Employee getEmployeeId(int employeeId, String employeePassword) {
		Employee employee = new Employee();
		ResultSet res = null;
		int check = 0;
		try {
		PreparedStatement statement = connection.prepareStatement(VIEW_EMPLOYEE_DETAILS);
		statement.setInt(1, employeeId);
		statement.setString(2, employeePassword);
		res = statement.executeQuery();
		if(res.next()) {
		employee =new Employee(res.getInt(1),res.getString(2),res.getString(3),res.getDouble(4));
		 check=1;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(check==1)
			return employee;
		else
			return null;
	}

}
