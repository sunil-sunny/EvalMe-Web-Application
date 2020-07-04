package com.group18.asdc.passwordpolicy;

import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.util.ICustomStringUtils;

public class CharsNotAllowedPolicy implements IBasePasswordPolicy {

	private String charsNotAllowed = null;
	private ICustomStringUtils customStringUtils = null;

	public CharsNotAllowedPolicy(String charsNotAllowed, ICustomStringUtils customStringUtils) {
		this.charsNotAllowed = charsNotAllowed;
		this.customStringUtils = customStringUtils;
	}

	@Override
	public void validate(String password) throws PasswordPolicyException {
		if (customStringUtils.containsAnyCharacter(password, this.charsNotAllowed)) {
			throw new PasswordPolicyException("Password contains restricted chars:" + charsNotAllowed);
		}
	}
}