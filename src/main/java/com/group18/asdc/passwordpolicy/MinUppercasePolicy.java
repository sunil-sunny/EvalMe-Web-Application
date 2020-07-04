package com.group18.asdc.passwordpolicy;

import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.util.ICustomStringUtils;

public class MinUppercasePolicy implements IBasePasswordPolicy {

	private Integer minUpperCase = null;
	private ICustomStringUtils customStringUtils = null;

	public MinUppercasePolicy(String minUpperCase, ICustomStringUtils customStringUtils) {
		this.minUpperCase = Integer.parseInt(minUpperCase);
		this.customStringUtils = customStringUtils;
	}

	@Override
	public void validate(String password) throws PasswordPolicyException {
		Integer upperCaseCharsCount = customStringUtils.getUpperCaseCharactersCount(password);
		if (upperCaseCharsCount < this.minUpperCase) {
			throw new PasswordPolicyException(
					"Password does not contain " + minUpperCase + " of upper case characters");
		}
	}
}