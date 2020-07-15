package com.group18.asdc.entities;

import java.util.ArrayList;
import java.util.List;

public class SurveyGroups {
	
	private int surveyId;
	private List<Group> surveyGroups = new ArrayList<Group>();
	
	public int getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}
	public List<Group> getSurveyGroups() {
		return surveyGroups;
	}
	public void setSurveyGroups(List<Group> surveyGroups) {
		this.surveyGroups = surveyGroups;
	}
}