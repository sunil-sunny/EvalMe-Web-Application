package com.group18.asdc.passwordpolicy;

import com.group18.asdc.errorhandling.PasswordPolicyException;

public interface IBasePasswordPolicy {

	public void validate(String password) throws PasswordPolicyException;

}