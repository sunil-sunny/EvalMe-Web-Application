package com.group18.asdc.entities;

public enum Role {

	ADMIN("ADMIN"), STUDENT("STUDENT"), INSTRUCTOR("INSTRUCTOR"), TA("TA");

	private final String role;

	private Role(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return role;
	}
}