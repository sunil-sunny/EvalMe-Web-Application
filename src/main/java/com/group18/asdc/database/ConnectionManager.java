package com.group18.asdc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.group18.asdc.SystemConfig;


// Singleton for retrieving connections.
public class ConnectionManager
{
	private static ConnectionManager uniqueInstance = null;
	
	private String dbURL;
	private String dbUserName;
	private String dbPassword;
	
	public ConnectionManager()
	{
		IDatabaseConfiguration config = SystemConfig.getSingletonInstance().getDatabaseConfiguration();
		dbURL = config.getDatabaseURL();
		dbUserName = config.getDatabaseUserName();
		dbPassword = config.getDatabasePassword();
	}
	
	public static ConnectionManager getInstance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new ConnectionManager();
		}
		return uniqueInstance;
	}
	
	public Connection getDBConnection() throws SQLException
	{
		System.out.println("bbbbbbbbbbbbbb"+dbURL+"qqqqq"+dbUserName+"uuu"+dbPassword);
		return DriverManager.getConnection(dbURL, dbUserName, dbPassword);
	}
}
