package com.group18.asdc.entities;

import java.util.List;

public interface ISurveyMetaData {

	public int getSurveyId();

	public void setSurveyId(int surveyId);

	public List<SurveyQuestion> getSurveyQuestions();

	public void setSurveyQuestions(List<SurveyQuestion> surveyQuestions);

	public boolean isPublishedStatus();

	public void setPublishedStatus(boolean publishedStatus);

	public int getGroupSize();

	public void setGroupSize(int groupSize);

	public Course getTheCourse();

	public void setTheCourse(Course theCourse);
}
