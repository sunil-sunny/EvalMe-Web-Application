package com.group18.asdc.entities;

import java.util.ArrayList;
import java.util.List;

public class SurveyQuestion implements ISurveyQuestion{

	private int surveyQuestionId;
	private String logicDetail;
	private int logicConstraint;
	private QuestionMetaData questionData;
	private int priority;
	private List<Option> options = new ArrayList<Option>();

	@Override
	public String getLogicDetail() {
		return logicDetail;
	}

	@Override
	public void setLogicDetail(String logicDetail) {
		this.logicDetail = logicDetail;
	}

	@Override
	public int getLogicConstraint() {
		return logicConstraint;
	}

	@Override
	public void setLogicConstraint(int logicConstraint) {
		this.logicConstraint = logicConstraint;
	}

	@Override
	public int getSurveyQuestionId() {
		return surveyQuestionId;
	}

	@Override
	public void setSurveyQuestionId(int surveyQuestionId) {
		this.surveyQuestionId = surveyQuestionId;
	}

	@Override
	public QuestionMetaData getQuestionData() {
		return questionData;
	}

	@Override
	public void setQuestionData(QuestionMetaData questionData) {
		this.questionData = questionData;
	}

	@Override
	public int getPriority() {
		return priority;
	}

	@Override
	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public List<Option> getOptions() {
		return options;
	}

	@Override
	public void setOptions(List<Option> options) {
		this.options = options;
	}
}