package com.group18.asdc.entities;

import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.passwordpolicy.IPasswordPolicyManager;

public interface IUser {

	public Boolean isValidUser();

	public String getFirstName();

	public void setFirstName(String firstName);

	public String getLastName();

	public void setLastName(String lastName);

	public String getBannerId();

	public void setBannerId(String bannerId);

	public String getEmail();

	public void setEmail(String email);

	public String getPassword();

	public void setPassword(String password);

	public void validatePassword(IPasswordPolicyManager passwordPolicyManager) throws PasswordPolicyException;

}