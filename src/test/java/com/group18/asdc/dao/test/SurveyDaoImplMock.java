package com.group18.asdc.dao.test;

import java.util.ArrayList;

import com.group18.asdc.dao.SurveyDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.errorhandling.SavingSurveyException;

public class SurveyDaoImplMock implements SurveyDao {

	@Override
	public SurveyMetaData getSavedSurvey(Course course) {
		SurveyMetaData surveyData = new SurveyMetaData();
		return surveyData;
	}

	@Override
	public boolean saveSurvey(SurveyMetaData surveyData) throws SavingSurveyException {
		surveyData.setSurveyQuestions(new ArrayList<SurveyQuestion>());
		return true;
	}

	@Override
	public boolean isSurveyExists(Course course) {
		SurveyMetaData surveyMetaData = new SurveyMetaData();
		surveyMetaData.getSurveyId();
		return true;
	}

	@Override
	public int createSurvey(Course course) {
		SurveyMetaData surveyMetaData = new SurveyMetaData();
		surveyMetaData.setSurveyId(1);
		return surveyMetaData.getSurveyId();
	}

	@Override
	public boolean isSurveyPublished(Course course) {
		SurveyMetaData survey=new SurveyMetaData();
		survey.setPublishedStatus(Boolean.FALSE);
		return survey.isPublishedStatus();
	}

	@Override
	public boolean publishSurvey(SurveyMetaData surveyMetaData) {
		surveyMetaData.setPublishedStatus(Boolean.TRUE);
		return surveyMetaData.isPublishedStatus();
	}

}
