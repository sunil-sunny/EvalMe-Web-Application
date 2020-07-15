package com.group18.asdc.entities;

public class BasicQuestionData implements IBasicQuestionData{

	private String questionTitle;
	private String questionText;
	private String questionType;

	@Override
	public String getQuestionTitle() {
		return questionTitle;
	}

	@Override
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	@Override
	public String getQuestionText() {
		return questionText;
	}

	@Override
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	@Override
	public String getQuestionType() {
		return questionType;
	}

	@Override
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	@Override
	public String toString() {
		return "BasicQuestionData [questionTitle=" + questionTitle + ", questionText=" + questionText
				+ ", questionType=" + questionType + "]";
	}
}