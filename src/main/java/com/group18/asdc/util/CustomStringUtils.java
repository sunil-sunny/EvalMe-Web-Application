package com.group18.asdc.util;

import org.apache.commons.lang3.StringUtils;

public class CustomStringUtils implements ICustomStringUtils {

	private Integer getStringCount(String value) {
		int upperCase = 0, lowerCase = 0, numberCount = 0, specialCharCount = 0;
		for (char c : value.toCharArray()) {
			if (Character.isUpperCase(c)) {
				upperCase++;
			} else if (Character.isLowerCase(c)) {
				lowerCase++;
			} else if (Character.isDigit(c)) {
				numberCount++;
			} else {
				specialCharCount++;
			}
		}
		return specialCharCount;
	}

	@Override
	public Integer getLowerCaseCharactersCount(String value) {
		return (int) value.chars().filter((s) -> Character.isLowerCase(s)).count();
	}

	@Override
	public Integer getUpperCaseCharactersCount(String value) {
		return (int) value.chars().filter((s) -> Character.isUpperCase(s)).count();
	}

	@Override
	public Boolean containsAnyCharacter(String value, String charsNotAllowed) {
		return StringUtils.containsAny(value, charsNotAllowed);
	}

	@Override
	public Integer getSpecialCharactersCount(String value) {
		return getStringCount(value);
	}
}