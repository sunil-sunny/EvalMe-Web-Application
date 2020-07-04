package com.group18.asdc.util;

public interface ICustomStringUtils {

	public Integer getLowerCaseCharactersCount(String value);

	public Integer getUpperCaseCharactersCount(String value);

	public Boolean containsAnyCharacter(String value, String charsNotAllowed);

	public Integer getSpecialCharactersCount(String value);
}