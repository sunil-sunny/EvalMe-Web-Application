package com.group18.asdc.entities;

import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.passwordpolicy.IBasePasswordPolicyManager;
import com.group18.asdc.passwordpolicy.IPasswordPolicyManager;

public class User implements IUser {

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

	@Override
	public Boolean isValidUser() {
		if (bannerId != null && !bannerId.isEmpty()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getBannerId() {
		return bannerId;
	}

	@Override
	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	public static void validatePassword(String password, IBasePasswordPolicyManager passwordPolicyManager)
			throws PasswordPolicyException {
		passwordPolicyManager.validatePassword(password);
	}

	@Override
	public void validatePassword(IPasswordPolicyManager passwordPolicyManager) throws PasswordPolicyException {
		passwordPolicyManager.validatePassword(bannerId, password);
	}
	
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", bannerId=" + bannerId + ", email=" + email
				+ "]";
	}
}
