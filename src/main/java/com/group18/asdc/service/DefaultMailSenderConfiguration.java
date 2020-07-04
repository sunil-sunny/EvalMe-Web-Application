package com.group18.asdc.service;

import java.util.Properties;

public class DefaultMailSenderConfiguration implements IJavaMailSenderConfiguration {

	private String userName = System.getenv("MAIL_SENDER_NAME");
	private String password = System.getenv("MAIL_SENDER_PASSWORD");
	private final String host = "smtp.gmail.com";
	private final String transportProtocol = "smtp";
	private final String authStatus = "true";
	private final String tlsStatus = "true";
	private final Integer port = 587;

	@Override
	public String getEmail() {
		return userName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public Integer getPort() {
		return port;
	}

	@Override
	public Properties getProperties() {
		Properties customProperties = new Properties();
		customProperties.put("mail.transport.protocol", transportProtocol);
		customProperties.put("mail.smtp.auth", authStatus);
		customProperties.put("mail.smtp.starttls.enable", tlsStatus);
		return customProperties;
	}
}