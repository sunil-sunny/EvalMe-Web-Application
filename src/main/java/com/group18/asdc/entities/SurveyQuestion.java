package com.group18.asdc.entities;

public class SurveyQuestion{

	private int surveyQuestionId;
	private String logicDetail;
	private int logicConstraint;
	private QuestionMetaData questionData;

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

	public QuestionMetaData getQuestionData() {
		return questionData;
	}

	public void setQuestionData(QuestionMetaData questionData) {
		this.questionData = questionData;
	}

}
