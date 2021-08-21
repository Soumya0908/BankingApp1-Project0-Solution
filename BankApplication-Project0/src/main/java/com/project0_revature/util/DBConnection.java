package com.project0_revature.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	public static Connection getDBConnection(){
		Connection connection = null;
		Properties properties = new  Properties();
		try {
			FileReader reader = new FileReader("E:\\DB.properties");
			properties.load(reader);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String driver = null;
		String url = null;
		String username = null;
		String password = null;
		driver = properties.getProperty("driver");
		url = properties.getProperty("url");
		username = properties.getProperty("username");
		password = properties.getProperty("password");
			try {
				Class.forName(driver); //type4 Driver
                connection = DriverManager.getConnection(url, username, password);
			}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return connection;
	}
}
