package com.group18.asdc.dao;

import java.util.ArrayList;

public interface IPasswordPolicyDB {

	public ArrayList loadBasePoliciesFromDB();

	public ArrayList loadPoliciesFromDB();
}