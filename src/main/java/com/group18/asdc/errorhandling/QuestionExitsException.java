package com.group18.asdc.errorhandling;

public class QuestionExitsException extends Exception {

	private static final long serialVersionUID = 1L;

	public QuestionExitsException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
