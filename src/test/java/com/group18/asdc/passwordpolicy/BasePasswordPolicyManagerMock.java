package com.group18.asdc.passwordpolicy;

import com.group18.asdc.errorhandling.PasswordPolicyException;

public class BasePasswordPolicyManagerMock extends BasePasswordPolicyFactory  {

	private IBasePasswordPolicy passwordPolicyObj;

	public BasePasswordPolicyManagerMock(IBasePasswordPolicy passwordPolicyObj) {
		this.passwordPolicyObj = passwordPolicyObj;
	}

	@Override
	public void validatePassword(String password) throws PasswordPolicyException {
		passwordPolicyObj.validate(password);
	}
}