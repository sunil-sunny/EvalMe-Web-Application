package com.group18.asdc.service;

import java.util.Properties;

public interface IJavaMailSenderConfiguration {

	public String getEmail();

	public String getPassword();

	public String getHost();

	public Integer getPort();

	public Properties getProperties();

}