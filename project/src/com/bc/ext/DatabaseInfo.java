/**
Name: Nathan Liew & Dinesh Budhathoki
Program: InvoiceData.java
Purpose : Load the JDBC Driver & Create a connection to the DataBase
**/

package com.bc.ext;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseInfo {

	/**
	 * Connection parameters that are necessary for CSE's configuration
	 */

	public static Connection getConnection() {

		String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://cse.unl.edu/wliew?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "wliew";
		String password = "2YpZTDKT";

		// *1 Load the JDBC Driver

		try {
			Class.forName(DRIVER_CLASS).getDeclaredConstructor().newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// *2 Create a connection to our DB
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

}
