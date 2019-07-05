package com.sagar.javacase.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {

	public static void main(String[] args) {
		Connection con = null;
		try {
			// load the Driver Class
			Class.forName("com.mysql.jdbc.Driver");

			// create the connection now
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/UserDB", "pankaj", "pankaj123");
			
			Statement stmt = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Check database is UP and configs are correct");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Please include JDBC MySQL jar in classpath");
			e.printStackTrace();
		}
	}

}
