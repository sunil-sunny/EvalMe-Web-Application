package com.group18.asdc.security;

public interface IPasswordEncryption {

	public String encryptPassword(String rawPassword);

	public boolean matches(String rawPassword, String encryptedPassword);
}
