package com.group18.asdc.passwordpolicy;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.util.ICustomStringUtils;

public class CharsNotAllowedPolicy implements IBasePasswordPolicy {

	private String charsNotAllowed = null;
	private ICustomStringUtils customStringUtils = null;
	private Logger logger = Logger.getLogger(CharsNotAllowedPolicy.class.getName());

	public CharsNotAllowedPolicy(String charsNotAllowed, ICustomStringUtils customStringUtils) {
		this.charsNotAllowed = charsNotAllowed;
		this.customStringUtils = customStringUtils;
	}

	@Override
	public void validate(String password) throws PasswordPolicyException {
		logger.log(Level.INFO, "Validating characters not allowed policy for the password" );
		if (customStringUtils.containsAnyCharacter(password, this.charsNotAllowed)) {
			throw new PasswordPolicyException("Password contains restricted chars:" + charsNotAllowed);
		}
	}
}