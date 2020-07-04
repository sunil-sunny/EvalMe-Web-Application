package com.group18.asdc.service;

import java.util.Properties;

public class JavaMailSenderConfigurationMock implements IJavaMailSenderConfiguration {

	@Override
	public String getEmail() {
		return "abcd@gmail.com";
	}

	@Override
	public String getPassword() {
		return "password";
	}

	@Override
	public String getHost() {
		return "smtp.gmail.com";
	}

	@Override
	public Integer getPort() {
		return 587;
	}

	@Override
	public Properties getProperties() {
		Properties customProperties = new Properties();
		customProperties.put("mail.transport.protocol", "smtp");
		customProperties.put("mail.smtp.auth", "true");
		customProperties.put("mail.smtp.starttls.enable", "true");
		return customProperties;
	}
}