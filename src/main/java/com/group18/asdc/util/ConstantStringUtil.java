package com.group18.asdc.util;

public class ConstantStringUtil {

	private final static String emailRegex = "^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@\"\r\n"
			+ "        + \"[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$";
	private final static String passwordTag = "!dal";
	private final static String bannerIdPatternCheck = "B00(.*)";
	private final static String emailPatternCheck = "(.*)@dal.ca";
	private final static String emailSubject = "you are now a part of EvalMe";
	private final static String emailMessageHeader = "Thank you for being a part of us !! \\n  you username and password is ";

	public static String getEmailregex() {
		return emailRegex;
	}

	public static String getPasswordtag() {
		return passwordTag;
	}

	public static String getBanneridpatterncheck() {
		return bannerIdPatternCheck;
	}

	public static String getEmailpatterncheck() {
		return emailPatternCheck;
	}

	public static String getEmailsubject() {
		return emailSubject;
	}

	public static String getEmailmessageheader() {
		return emailMessageHeader;
	}
}