package com.group18.asdc.util;

public enum RegistrationStatus {

	SUCCESS("0"), INVALID_BANNER_PATTERN("1"), INVALID_BANNER_LENGTH("2"), INVALID_EMAIL_PATTERN("3"),
	PASSWORD_POLICY_ERROR("4"), EXISTING_BANNER_ID("5"), EXISTING_EMAIL_ID("6"), UNSUCCESSFUL("7");

	private final String status;

	private RegistrationStatus(String status) {
		this.status = status;
	}

	public int value() {
		return Integer.parseInt(status);
	}
}