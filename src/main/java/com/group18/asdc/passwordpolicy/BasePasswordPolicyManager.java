package com.group18.asdc.passwordpolicy;

import java.util.ArrayList;
import java.util.HashMap;
import com.group18.asdc.SystemConfig;
import com.group18.asdc.database.IPasswordPolicyDB;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.util.ICustomStringUtils;

public class BasePasswordPolicyManager implements IBasePasswordPolicyManager {

	private ArrayList<HashMap> enabledPasswordPolicies = null;
	private IPasswordPolicyDB passwordPolicyDB = null;

	private enum DatabasePolicyName {
		MIN_LENGTH_POLICY("MinLength"), MAX_LENGTH_POLICY("MaxLength"), MIN_LOWERCASE_POLICY("MinLowercase"),
		MIN_UPPERCASE_POLICY("MinUppercase"), MIN_SPECIALCASE_POLICY("MinSpecialCharacter"),
		CHARACTERS_NOT_ALLOWED("CharactersNotAllowed");
		private final String policyName;

		private DatabasePolicyName(String policyName) {
			this.policyName = policyName;
		}

		@Override
		public String toString() {
			return policyName;
		}
	};

	public BasePasswordPolicyManager(IPasswordPolicyDB passwordPolicyDB) {
		this.passwordPolicyDB = passwordPolicyDB;
	}

	private void loadDefaultConfigurations() {
		if (null == enabledPasswordPolicies) {
			enabledPasswordPolicies = passwordPolicyDB.loadBasePoliciesFromDB();
		}
	}

	@Override
	public void validatePassword(String password) throws PasswordPolicyException {
		loadDefaultConfigurations();
		IBasePasswordPolicy passwordPolicy = null;
		ICustomStringUtils customStringUtils = SystemConfig.getSingletonInstance().getCustomStringUtils();

		for (HashMap eachEnabledPolicy : enabledPasswordPolicies) {
			String policyName = (String) eachEnabledPolicy.get("POLICY_NAME");
			String policyValue = (String) eachEnabledPolicy.get("POLICY_VALUE");
			if (policyName.equals(DatabasePolicyName.MIN_LENGTH_POLICY.toString())) {
				passwordPolicy = new MinlengthPolicy(policyValue);
			} else if (policyName.equals(DatabasePolicyName.MAX_LENGTH_POLICY.toString())) {
				passwordPolicy = new MaxlengthPolicy(policyValue);
			} else if (policyName.equals(DatabasePolicyName.MIN_LOWERCASE_POLICY.toString())) {
				passwordPolicy = new MinLowercasePolicy(policyValue, customStringUtils);
			} else if (policyName.equals(DatabasePolicyName.MIN_UPPERCASE_POLICY.toString())) {
				passwordPolicy = new MinUppercasePolicy(policyValue, customStringUtils);
			} else if (policyName.equals(DatabasePolicyName.MIN_SPECIALCASE_POLICY.toString())) {
				passwordPolicy = new MinSpecialcharPolicy(policyValue, customStringUtils);
			} else if (policyName.equals(DatabasePolicyName.CHARACTERS_NOT_ALLOWED.toString())) {
				passwordPolicy = new CharsNotAllowedPolicy(policyValue, customStringUtils);
			}
			passwordPolicy.validate(password);
		}
	}
}