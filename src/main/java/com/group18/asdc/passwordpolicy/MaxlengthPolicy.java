package com.group18.asdc.passwordpolicy;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.errorhandling.PasswordPolicyException;

public class MaxlengthPolicy implements IBasePasswordPolicy {

	private Integer maxLength;
	private Logger logger = Logger.getLogger(MaxlengthPolicy.class.getName());

	public MaxlengthPolicy(String maxLength) {
		this.maxLength = Integer.parseInt(maxLength);
	}

	@Override
	public void validate(String password) throws PasswordPolicyException {
		logger.log(Level.INFO, "Validating maximum length policy for the password");
		if (password.trim().length() > maxLength) {
			throw new PasswordPolicyException("Password length is greater than " + maxLength);
		}
	}
}