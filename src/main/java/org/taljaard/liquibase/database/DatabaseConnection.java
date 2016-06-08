package org.taljaard.liquibase.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	Connection connection;
	
	public DatabaseConnection() throws Exception {
		// TODO Auto-generated constructor stub
		//Class.forName("com.mysql.jdbc.Driver").newInstance();
	}
	
	private void initializeConnection() throws Exception {
		connection = DriverManager.getConnection("jdbc:mysql://192.168.1.200:3306/liquibase?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "", "");
	}
	
	public Connection getConnection() throws Exception {
		if (connection == null) {
			initializeConnection();
		}
		return connection;
	}
	
	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
