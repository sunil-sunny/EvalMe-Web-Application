package com.group18.asdc.entities;

import java.util.List;

public interface ISurveyQuestion {

	public String getLogicDetail();

	public void setLogicDetail(String logicDetail);

	public int getLogicConstraint();

	public void setLogicConstraint(int logicConstraint);

	public int getSurveyQuestionId();

	public void setSurveyQuestionId(int surveyQuestionId);

	public QuestionMetaData getQuestionData();

	public void setQuestionData(QuestionMetaData questionData);

	public int getPriority();

	public void setPriority(int priority);

	public List<Option> getOptions();

	public void setOptions(List<Option> options);
}
