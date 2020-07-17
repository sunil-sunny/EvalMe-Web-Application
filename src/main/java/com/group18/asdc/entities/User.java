package com.group18.asdc.entities;

import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.passwordpolicy.BasePasswordPolicyFactory;
import com.group18.asdc.passwordpolicy.PasswordPolicyFactory;

public class User {

	private String firstName;
	private String lastName;
	private String bannerId;
	private String email;
	private String password;

	public User() {
		bannerId = null;
		firstName = null;
		lastName = null;
		email = null;
		password = null;
	}

	public User(String firstName, String lastName, String bannerId, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.bannerId = bannerId;
		this.email = email;
	}

	public Boolean isValidUser() {
		if (bannerId == null && bannerId.isEmpty()) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBannerId() {
		return bannerId;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static void validatePassword(String password, BasePasswordPolicyFactory passwordPolicyManager)
			throws PasswordPolicyException {
		passwordPolicyManager.validatePassword(password);
	}

	public void validatePassword(PasswordPolicyFactory passwordPolicyManager) throws PasswordPolicyException {
		passwordPolicyManager.validatePassword(bannerId, password);
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", bannerId=" + bannerId + ", email=" + email
				+ "]";
	}
}
