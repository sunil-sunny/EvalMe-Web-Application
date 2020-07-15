package com.group18.asdc.service.test;

import java.util.Properties;

import com.group18.asdc.service.IJavaMailSenderConfiguration;

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