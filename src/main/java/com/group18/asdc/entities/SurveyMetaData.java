package com.group18.asdc.entities;

import java.util.ArrayList;
import java.util.List;

public class SurveyMetaData implements ISurveyMetaData {

	private int surveyId;
	private List<SurveyQuestion> surveyQuestions = new ArrayList<SurveyQuestion>();
	private boolean publishedStatus;
	private int groupSize;
	private Course theCourse;
	
	@Override
	public int getSurveyId() {
		return surveyId;
	}
	
	@Override
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}
	
	@Override
	public List<SurveyQuestion> getSurveyQuestions() {
		return surveyQuestions;
	}
	
	@Override
	public void setSurveyQuestions(List<SurveyQuestion> surveyQuestions) {
		this.surveyQuestions = surveyQuestions;
	}
	
	@Override
	public boolean isPublishedStatus() {
		return publishedStatus;
	}
	
	@Override
	public void setPublishedStatus(boolean publishedStatus) {
		this.publishedStatus = publishedStatus;
	}
	
	@Override
	public int getGroupSize() {
		return groupSize;
	}
	
	@Override
	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}
	
	@Override
	public Course getTheCourse() {
		return theCourse;
	}
	
	@Override
	public void setTheCourse(Course theCourse) {
		this.theCourse = theCourse;
	}
	
	@Override
	public String toString() {
		return "SurveyMetaData [surveyId=" + surveyId + ", surveyQuestions=" + surveyQuestions + ", publishedStatus="
				+ publishedStatus + ", groupSize=" + groupSize + ", theCourse=" + theCourse + "]";
	}	
}
