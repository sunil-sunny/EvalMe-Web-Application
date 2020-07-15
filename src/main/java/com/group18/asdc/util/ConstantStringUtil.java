package com.group18.asdc.util;

public enum ConstantStringUtil {

	EMAIL_REGEX("^[_A-Za-z0-9-\\\\\\\\+]+(\\\\\\\\.[_A-Za-z0-9-]+)*@\\\"\\r\\n\"\r\n"
			+ "\"+\\\"[A-Za-z0-9-]+(\\\\\\\\.[A-Za-z0-9]+)*(\\\\\\\\.[A-Za-z]{2,})$\r\n" + ""),
	PASSWORD_TAG("!dal"), BANNER_ID_CHECK("B00(.*)"), EMAIL_PATTERN_CHECK("(.*)@dal.ca"),
	EMAIL_SUBJECT("you are now a part of EvalMe"),
	EMAIL_MESSAGE_HEADER("Thank you for being a part of us !! \\\\n  you username and password is "),
	GROUP_SIMILAR("Group Similar"), GROUP_DISIMILAR("Group Disimilar"), GREATER_THAN("Greater Than"),
	LESS_THAN("Less Than"), 
	DATE_FORMAT("yyyy-MM-dd hh:mm:ss");

	private final String constantString;

	private ConstantStringUtil(String constantString) {
		this.constantString = constantString;
	}

	@Override
	public String toString() {
		return constantString;
	}
}