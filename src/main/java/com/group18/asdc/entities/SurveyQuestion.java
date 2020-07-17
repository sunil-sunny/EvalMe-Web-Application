package com.group18.asdc.entities;

import java.util.ArrayList;
import java.util.List;

public class SurveyQuestion {

	private int surveyQuestionId;
	private String logicDetail;
	private int logicConstraint;
	private QuestionMetaData questionData;
	private int priority;
	private List<Option> options = new ArrayList<Option>();

	
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

	
	public int getPriority() {
		return priority;
	}

	
	public void setPriority(int priority) {
		this.priority = priority;
	}

	
	public List<Option> getOptions() {
		return options;
	}
	
	public void setOptions(List<Option> options) {
		this.options = options;
	}
}