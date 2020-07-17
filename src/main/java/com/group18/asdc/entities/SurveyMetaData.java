package com.group18.asdc.entities;

import java.util.ArrayList;
import java.util.List;

public class SurveyMetaData implements ISurveyMetaData {

	private int surveyId;
	private List<SurveyQuestion> surveyQuestions = new ArrayList<SurveyQuestion>();
	private boolean publishedStatus;
	private int groupSize;
	private Course theCourse;

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public List<SurveyQuestion> getSurveyQuestions() {
		return surveyQuestions;
	}

	public void setSurveyQuestions(List<SurveyQuestion> surveyQuestions) {
		this.surveyQuestions = surveyQuestions;
	}

	public boolean isPublishedStatus() {
		return publishedStatus;
	}

	public void setPublishedStatus(boolean publishedStatus) {
		this.publishedStatus = publishedStatus;
	}

	public int getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

	public Course getTheCourse() {
		return theCourse;
	}

	public void setTheCourse(Course theCourse) {
		this.theCourse = theCourse;
	}

	@Override
	public String toString() {
		return "SurveyMetaData [surveyId=" + surveyId + ", surveyQuestions=" + surveyQuestions + ", publishedStatus="
				+ publishedStatus + ", groupSize=" + groupSize + ", theCourse=" + theCourse + "]";
	}
}
