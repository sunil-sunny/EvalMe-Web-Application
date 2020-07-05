package com.group18.asdc.entities;

public class SurveyQuestion {

	private int surveyQuestionId;
	private QuestionMetaData questionData;
	private String logicDetail;
	private int logicConstraint;

	public QuestionMetaData getQuestionData() {
		return questionData;
	}

	public void setQuestionData(QuestionMetaData questionData) {
		this.questionData = questionData;
	}

	public String getLogicDetail() {
		return logicDetail;
	}

	public void setLogicDetail(String logicDetail) {
		this.logicDetail = logicDetail;
	}

	public int getLogicConstraint() {
		return logicConstraint;
	}

	public void setLogicConstraint(int logicConstraint) {
		this.logicConstraint = logicConstraint;
	}

	public int getSurveyQuestionId() {
		return surveyQuestionId;
	}

	public void setSurveyQuestionId(int surveyQuestionId) {
		this.surveyQuestionId = surveyQuestionId;
	}

	@Override
	public String toString() {
		return "SurveyQuestion [surveyQuestionId=" + surveyQuestionId + ", questionData=" + questionData
				+ ", logicDetail=" + logicDetail + ", logicConstraint=" + logicConstraint + "]";
	}
}
