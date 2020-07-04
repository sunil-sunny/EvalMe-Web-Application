package com.group18.asdc.passwordpolicy;

import com.group18.asdc.errorhandling.PasswordPolicyException;

public interface IPasswordPolicyManager {
	public void validatePassword(String bannerId, String password) throws PasswordPolicyException;
}