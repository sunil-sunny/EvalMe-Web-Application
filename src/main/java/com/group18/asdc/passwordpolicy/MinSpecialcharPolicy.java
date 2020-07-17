package com.group18.asdc.passwordpolicy;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.util.ICustomStringUtils;

public class MinSpecialcharPolicy implements IBasePasswordPolicy {

	private Integer minSpecialChars = null;
	private ICustomStringUtils customStringUtils = null;
	private Logger logger = Logger.getLogger(MinSpecialcharPolicy.class.getName());

	public MinSpecialcharPolicy(String minSpecialChars, ICustomStringUtils customStringUtils) {
		this.minSpecialChars = Integer.parseInt(minSpecialChars);
		this.customStringUtils = customStringUtils;
	}

	@Override
	public void validate(String password) throws PasswordPolicyException {
		logger.log(Level.INFO, "Validating minimum special characters policy for the password");
		Integer numberSpecialCharacters = customStringUtils.getSpecialCharactersCount(password);
		if (numberSpecialCharacters < minSpecialChars) {
			throw new PasswordPolicyException(
					"Password does not contain " + minSpecialChars + " of special characters.");
		}
	}
}