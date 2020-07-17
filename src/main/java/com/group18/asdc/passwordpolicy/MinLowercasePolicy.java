package com.group18.asdc.passwordpolicy;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.util.ICustomStringUtils;

public class MinLowercasePolicy implements IBasePasswordPolicy {

	private Integer minLowerCase = null;
	private ICustomStringUtils customStringUtils = null;
	private Logger logger = Logger.getLogger(MinLowercasePolicy.class.getName());

	public MinLowercasePolicy(String minLowerCase, ICustomStringUtils customStringUtils) {
		this.minLowerCase = Integer.parseInt(minLowerCase);
		this.customStringUtils = customStringUtils;
	}

	@Override
	public void validate(String password) throws PasswordPolicyException {
		logger.log(Level.INFO, "Validating minimum lower case policy for the password");
		Integer lowerCaseCharsCount = customStringUtils.getLowerCaseCharactersCount(password);
		if (lowerCaseCharsCount < this.minLowerCase) {
			throw new PasswordPolicyException(
					"Password does not contain " + minLowerCase + " of lower case characters");
		}
	}
}