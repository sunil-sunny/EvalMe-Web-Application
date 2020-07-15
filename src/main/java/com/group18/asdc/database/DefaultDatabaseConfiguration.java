package com.group18.asdc.database;

public class DefaultDatabaseConfiguration implements IDatabaseConfiguration {

	private String url = System.getenv("DB_URL");
	private String username = System.getenv("DB_USERNAME");
	private String password = System.getenv("DB_PASSWORD");
	
	@Override
	public String getDatabaseUserName() {
		return username;
	}

	@Override
	public String getDatabasePassword() {
		return password;
	}

	@Override
	public String getDatabaseURL() {
		return url;
	}
}