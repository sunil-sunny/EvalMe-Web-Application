package com.group18.asdc.service.test;

import java.util.ArrayList;
import java.util.List;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.SurveyDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.errorhandling.PublishSurveyException;
import com.group18.asdc.errorhandling.QuestionExitsException;
import com.group18.asdc.errorhandling.SavingSurveyException;
import com.group18.asdc.service.SurveyService;

public class SurveyServiceImplMock implements SurveyService {

	private final static SurveyDao theSurveyDao = TestConfig.getTestSingletonIntance().getDaoTestAbstractFactory()
			.getSurveyDaoTest();

	@Override
	public boolean addQuestionToSurvey(QuestionMetaData theQuestionMetaData) throws QuestionExitsException {
		SurveyMetaData surveyMetaData = new SurveyMetaData();
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		surveyQuestion.setQuestionData(theQuestionMetaData);
		List<SurveyQuestion> surveyQuestions = new ArrayList<SurveyQuestion>();
		surveyMetaData.setSurveyQuestions(surveyQuestions);
		surveyMetaData.getSurveyQuestions().add(surveyQuestion);
		return Boolean.TRUE;
	}

	@Override
	public boolean saveSurvey(SurveyMetaData surveyData) {

		try {
			return theSurveyDao.saveSurvey(surveyData);
		} catch (SavingSurveyException e) {
			return Boolean.FALSE;
		}
	}

	@Override
	public boolean publishSurvey() throws PublishSurveyException {
		return theSurveyDao.publishSurvey(new SurveyMetaData());
	}

	@Override
	public boolean removeQuestionFromSurvey(QuestionMetaData theQuestionMetaData) {
		SurveyMetaData surveyMetaData = new SurveyMetaData();
		SurveyQuestion surveyQuestion = new SurveyQuestion();
		surveyQuestion.setQuestionData(theQuestionMetaData);
		List<SurveyQuestion> surveyQuestions = new ArrayList<SurveyQuestion>();
		surveyMetaData.setSurveyQuestions(surveyQuestions);
		surveyMetaData.getSurveyQuestions().add(surveyQuestion);
		surveyMetaData.getSurveyQuestions().remove(0);
		return Boolean.TRUE;
	}

	@Override
	public boolean isSurveyPublishedForCourse(Course theCourse) {
		return theSurveyDao.isSurveyPublished(theCourse);
	}

	@Override
	public SurveyMetaData getSavedSurvey(Course course) {
		return theSurveyDao.getSavedSurvey(course);
	}

	@Override
	public SurveyMetaData getCurrentSurvey() {
		SurveyMetaData surveyMetaData = new SurveyMetaData();
		return surveyMetaData;
	}

}
