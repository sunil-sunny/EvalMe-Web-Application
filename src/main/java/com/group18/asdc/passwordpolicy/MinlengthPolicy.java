package com.group18.asdc.passwordpolicy;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.errorhandling.PasswordPolicyException;

public class MinlengthPolicy implements IBasePasswordPolicy {

	private Integer minLength;
	private Logger logger = Logger.getLogger(MinlengthPolicy.class.getName());

	public MinlengthPolicy(String minLength) {
		this.minLength = Integer.parseInt(minLength);
	}

	@Override
	public void validate(String password) throws PasswordPolicyException {
		logger.log(Level.INFO, "Validating minimum length policy for the password");
		if (password == null || password.trim().length() < minLength) {
			throw new PasswordPolicyException("Password length lesser than " + minLength);
		}
	}
}