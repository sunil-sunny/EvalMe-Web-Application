package com.group18.asdc.entities;

public enum QuestionType {

	NUMERIC_TYPE("numeric"), MULTIPLE_CHOOSE_ONE("multiple-choice-choose-one"),
	MULTIPLE_CHOOSE_MORE("multiple-choice-choose-more"), FREE_TEXT("freetext");

	private final String questionType;

	private QuestionType(String questionType) {
		this.questionType = questionType;
	}

	@Override
	public String toString() {
		return questionType;
	}
}