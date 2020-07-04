package com.group18.asdc.passwordpolicy;

import java.util.ArrayList;
import java.util.HashMap;
import com.group18.asdc.SystemConfig;
import com.group18.asdc.database.IPasswordPolicyDB;
import com.group18.asdc.errorhandling.PasswordPolicyException;

public class PasswordPolicyManager extends BasePasswordPolicyManager implements IPasswordPolicyManager {

	private ArrayList<HashMap> enabledPasswordPolicies = null;
	private IPasswordPolicyDB passwordPolicyDB;
	private final String HISTORY_CONSTRAINT_POLICY = "HistoryConstraint";

	public PasswordPolicyManager(IPasswordPolicyDB passwordPolicyDB) {
		super(passwordPolicyDB);
		this.passwordPolicyDB = passwordPolicyDB;
	}

	private void loadDefaultConfigurations() {
		if (null == enabledPasswordPolicies) {
			enabledPasswordPolicies = passwordPolicyDB.loadPoliciesFromDB();
		}
	}

	@Override
	public void validatePassword(String bannerId, String password) throws PasswordPolicyException {
		loadDefaultConfigurations();
		super.validatePassword(password);
		IPasswordPolicy passwordPolicy = null;
		for (HashMap eachEnabledPolicy : enabledPasswordPolicies) {
			if (eachEnabledPolicy.get("POLICY_NAME").equals(HISTORY_CONSTRAINT_POLICY)) {
				passwordPolicy = new HistoryConstraintPolicy((String) eachEnabledPolicy.get("POLICY_VALUE"),
						SystemConfig.getSingletonInstance().getPasswordHistoryService(),
						SystemConfig.getSingletonInstance().getPasswordEncryption());
			}
			passwordPolicy.validate(bannerId, password);
		}
	}
}