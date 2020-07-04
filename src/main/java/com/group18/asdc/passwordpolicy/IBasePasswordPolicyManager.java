package com.group18.asdc.passwordpolicy;

import com.group18.asdc.errorhandling.PasswordPolicyException;

public interface IBasePasswordPolicyManager {

	public void validatePassword(String password) throws PasswordPolicyException;

}