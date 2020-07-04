package com.group18.asdc.errorhandling;

public class PasswordPolicyException extends Exception {

	private static final long serialVersionUID = 1L;
	private String exceptionType;

	public PasswordPolicyException(String exceptionType) {
		super(exceptionType);
		this.exceptionType = exceptionType;
	}

	@Override
	public String toString() {
		return ("Password does not match " + exceptionType + " policy");
	}
}