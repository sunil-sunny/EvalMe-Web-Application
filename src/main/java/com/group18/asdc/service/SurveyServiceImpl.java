package com.group18.asdc.service;

import java.util.ArrayList;
import java.util.List;

import com.group18.asdc.SurveyConfig;
import com.group18.asdc.dao.SurveyDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.errorhandling.QuestionExitsException;
import com.group18.asdc.errorhandling.SavingSurveyException;
import com.group18.asdc.errorhandling.SurveyAlreadyPublishedException;

public class SurveyServiceImpl implements SurveyService {

	private static SurveyMetaData surveyMetaData = new SurveyMetaData();

	@Override
	public SurveyMetaData getSavedSurvey(Course course) {
		SurveyDao theSurveyDao = SurveyConfig.getSingletonInstance().getTheSurveyDao();
		surveyMetaData.setTheCourse(course);
		boolean isSurveyExists = theSurveyDao.isSurveyExists(course);
		if (isSurveyExists) {
			surveyMetaData = theSurveyDao.getSavedSurvey(course);
		} else {
			surveyMetaData.setSurveyQuestions(new ArrayList<SurveyQuestion>());
		}
		return surveyMetaData;
	}

	@Override
	public boolean removeQuestionFromSurvey(QuestionMetaData theQuestionMetaData) {

		boolean isDeleted = false;
		SurveyQuestion exitingQuestion = null;
		for (SurveyQuestion theSurveyQuestion : surveyMetaData.getSurveyQuestions()) {
			if (theSurveyQuestion.getQuestionData().getQuestionId() == theQuestionMetaData.getQuestionId()) {
				exitingQuestion = theSurveyQuestion;
				break;
			}
		}
		if (null == exitingQuestion) {
			isDeleted = false;
		} else {
			List<SurveyQuestion> surveyQuestion = surveyMetaData.getSurveyQuestions();
			surveyQuestion.remove(exitingQuestion);
			surveyMetaData.setSurveyQuestions(surveyQuestion);
			isDeleted = true;
		}
		return isDeleted;
	}

	@Override
	public SurveyMetaData getCurrentSurvey() {
		return surveyMetaData;
	}

	@Override
	public boolean addQuestionToSurvey(QuestionMetaData theQuestionMetaData) throws QuestionExitsException {
		boolean isAdded = false;
		SurveyQuestion exitingQuestion = null;
		for (SurveyQuestion theSurveyQuestion : surveyMetaData.getSurveyQuestions()) {
			if (theSurveyQuestion.getQuestionData().getQuestionId() == theQuestionMetaData.getQuestionId()) {
				exitingQuestion = theSurveyQuestion;
				break;
			}
		}
		if (null == exitingQuestion) {
			SurveyQuestion newQuestion = new SurveyQuestion();
			newQuestion.setQuestionData(theQuestionMetaData);
			List<SurveyQuestion> surveyQuestion = surveyMetaData.getSurveyQuestions();
			surveyQuestion.add(newQuestion);
			surveyMetaData.setSurveyQuestions(surveyQuestion);
			isAdded = true;
		} else {
			throw new QuestionExitsException("Question Already Added");
		}
		return isAdded;
	}

	@Override
	public boolean isSurveyPublishedForCourse(Course theCourse) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveSurvey(SurveyMetaData surveyData) throws SavingSurveyException {
		SurveyDao theSurveyDao = SurveyConfig.getSingletonInstance().getTheSurveyDao();
		surveyData.setSurveyId(surveyMetaData.getSurveyId());
		if (theSurveyDao.isSurveyExists(surveyData.getTheCourse())) {
			return theSurveyDao.saveSurvey(surveyData);
		} else {

			int surveyId = theSurveyDao.createSurvey(surveyData.getTheCourse());
			if (0 == surveyId) {
				throw new SavingSurveyException("Exception while creating the Survey");
			} else {
				surveyData.setSurveyId(surveyId);
				return theSurveyDao.saveSurvey(surveyData);
			}
		}
	}

	@Override
	public boolean publishSurvey() throws SurveyAlreadyPublishedException {

		return false;
	}
}