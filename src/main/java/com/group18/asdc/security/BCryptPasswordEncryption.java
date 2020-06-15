package com.group18.asdc.security;

import org.springframework.security.crypto.bcrypt.*;

public class BCryptPasswordEncryption implements IPasswordEncryption
{
	private BCryptPasswordEncoder encoder;
	
	public BCryptPasswordEncryption()
	{
		encoder = new BCryptPasswordEncoder();
	}
	
	public String encryptPassword(String rawPassword)
	{
		return encoder.encode(rawPassword);
	}
	
	public boolean matches(String rawPassword, String encryptedPassword)
	{
		return encoder.matches(rawPassword, encryptedPassword);
	}
}
