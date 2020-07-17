package com.group18.asdc.util;

public enum PasswordPolicyQueries {

    GET_BASEPASSWORD_POLICIES("select * from BasePasswordPolicyConfig where IS_ENABLED = true"),
    GET_HISTORYPASSWORD_POLICIES("select * from HistoryPasswordPolicyConfig where IS_ENABLED = true");

    private final String sqlQuery;

    private PasswordPolicyQueries(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	@Override
	public String toString() {
		return sqlQuery;
	}
}