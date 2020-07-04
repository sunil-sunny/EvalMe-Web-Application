package com.group18.asdc.passwordpolicy;

import com.group18.asdc.errorhandling.PasswordPolicyException;

public class MaxlengthPolicy implements IBasePasswordPolicy {

	private Integer maxLength;

	public MaxlengthPolicy(String maxLength) {
		this.maxLength = Integer.parseInt(maxLength);
	}

	@Override
	public void validate(String password) throws PasswordPolicyException {
		if (password.trim().length() > maxLength) {
			throw new PasswordPolicyException("Password length is greater than " + maxLength);
		}
	}
}