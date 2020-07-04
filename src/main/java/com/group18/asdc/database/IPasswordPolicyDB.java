package com.group18.asdc.database;

import java.util.ArrayList;

public interface IPasswordPolicyDB {

	public ArrayList loadBasePoliciesFromDB();

	public ArrayList loadPoliciesFromDB();
}