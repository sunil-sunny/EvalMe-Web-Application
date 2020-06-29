package com.group18.asdc.entities;

import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.passwordpolicy.IBasePasswordPolicyManager;
import com.group18.asdc.passwordpolicy.IPasswordPolicyManager;
import com.group18.asdc.service.UserService;

public class User implements UserInterface {

	private String firstName;
	private String lastName;
	private String bannerId;
	private String email;
	private String password;

	public User() {
		super();
	}

	public User(String firstName, String lastName, String bannerId, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.bannerId = bannerId;
		this.email = email;
	}

	public User(String bannerId, UserService userService) {
		super();
		userService.loadUserWithBannerId(bannerId, this);
	}

	public Boolean isValidUser() {
		if (bannerId != null && !bannerId.isEmpty()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
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

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", bannerId=" + bannerId + ", email=" + email
				+ "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static void isPasswordValid(String password, IBasePasswordPolicyManager passwordPolicyManager)
			throws PasswordPolicyException {
		passwordPolicyManager.validatePassword(password);
	}

	public void isPasswordValid(IPasswordPolicyManager passwordPolicyManager) throws PasswordPolicyException {
		passwordPolicyManager.validatePassword(bannerId, password);
	}

}
