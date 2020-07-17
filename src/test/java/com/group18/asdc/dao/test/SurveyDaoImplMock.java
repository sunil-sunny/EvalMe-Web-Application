package com.group18.asdc.dao.test;

import java.util.ArrayList;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.SurveyDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.errorhandling.SavingSurveyException;

public class SurveyDaoImplMock implements SurveyDao {

	@Override
	public SurveyMetaData getSavedSurvey(Course course) {
		SurveyMetaData surveyData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyMetaDataTest();
		return surveyData;
	}

	@Override
	public boolean saveSurvey(SurveyMetaData surveyData) throws SavingSurveyException {
		surveyData.setSurveyQuestions(new ArrayList<SurveyQuestion>());
		return Boolean.TRUE;
	}

	@Override
	public boolean isSurveyExists(Course course) {
		SurveyMetaData surveyMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyMetaDataTest();
		surveyMetaData.getSurveyId();
		return Boolean.TRUE;
	}

	@Override
	public int createSurvey(Course course) {
		SurveyMetaData surveyMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyMetaDataTest();
		surveyMetaData.setSurveyId(1);
		return surveyMetaData.getSurveyId();
	}

	@Override
	public boolean isSurveyPublished(Course course) {
		SurveyMetaData survey = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyMetaDataTest();
		survey.setPublishedStatus(Boolean.FALSE);
		return survey.isPublishedStatus();
	}

	@Override
	public boolean publishSurvey(SurveyMetaData surveyMetaData) {
		surveyMetaData.setPublishedStatus(Boolean.TRUE);
		return surveyMetaData.isPublishedStatus();
	}
}
