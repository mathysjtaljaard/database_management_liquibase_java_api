package org.taljaard.liquibase.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import org.taljaard.liquibase.database.DatabaseConnection;

public class LiquibaseImplementation {

	public static void main(String[] args) {
		
		try {
			DatabaseConnection database = new DatabaseConnection();
			Connection connection = database.getConnection();
			
			Database liquibaseDatabase = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
			
			Liquibase liquibase = new Liquibase("org/taljaard/liquibase/changelog/changelog.xml", new ClassLoaderResourceAccessor(), liquibaseDatabase);
			liquibase.update(new Contexts(), new LabelExpression());
				
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("Select count(*) from DATABASECHANGELOG");
			
			while(resultSet.next()) {
				int count = resultSet.getInt(1);
				System.out.println("total rows in DATABASECHANGELOG table = " + count);
			}
			
			liquibase.rollback(1, null);
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery("Select count(*) from DATABASECHANGELOG");
			
			while(resultSet.next()) {
				int count = resultSet.getInt(1);
				System.out.println("total rows in DATABASECHANGELOG table = " + count);
			}
			
			database.closeConnection();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
