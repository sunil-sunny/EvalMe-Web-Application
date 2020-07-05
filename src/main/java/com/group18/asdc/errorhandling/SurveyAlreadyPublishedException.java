package com.group18.asdc.errorhandling;

public class SurveyAlreadyPublishedException extends Exception {

	private static final long serialVersionUID = 1L;

	public SurveyAlreadyPublishedException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
