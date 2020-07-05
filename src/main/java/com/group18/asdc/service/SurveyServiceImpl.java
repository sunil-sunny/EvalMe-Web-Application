package com.group18.asdc.service;

import java.util.ArrayList;
import java.util.List;

import com.group18.asdc.SurveyConfig;
import com.group18.asdc.dao.SurveyDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.errorhandling.QuestionExitsException;
import com.group18.asdc.errorhandling.SurveyAlreadyPublishedException;

public class SurveyServiceImpl implements SurveyService {
	private static List<SurveyQuestion> surveyQuestions = new ArrayList<>();

	@Override
	public List<SurveyQuestion> getAllSavedSurveyQuestions(Course course) {
		SurveyDao theSurveyDao = SurveyConfig.getSingletonInstance().getTheSurveyDao();
		surveyQuestions = theSurveyDao.getAllSavedSurveyQuestions(course);
		return surveyQuestions;
	}

	@Override
	public boolean saveSurveyQuestions(List<SurveyQuestion> allSurveyQuestions) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean publishSurvey() throws SurveyAlreadyPublishedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeQuestionFromSurvey(QuestionMetaData theQuestionMetaData) {

		boolean isDeleted = false;
		SurveyQuestion exitingQuestion = null;
		for (SurveyQuestion theSurveyQuestion : surveyQuestions) {
			if (theSurveyQuestion.getQuestionData().getQuestionId() == theQuestionMetaData.getQuestionId()) {
				exitingQuestion = theSurveyQuestion;
				break;
			}
		}
		if (null == exitingQuestion) {
			isDeleted = false;
		} else {
			surveyQuestions.remove(exitingQuestion);
			isDeleted = true;
		}
		return isDeleted;
	}

	@Override
	public List<SurveyQuestion> getCurrentListOfSurveyQuestions() {
		return surveyQuestions;
	}

	@Override
	public boolean addQuestionToSurvey(QuestionMetaData theQuestionMetaData) throws QuestionExitsException {
		boolean isAdded = false;
		SurveyQuestion exitingQuestion = null;
		for (SurveyQuestion theSurveyQuestion : surveyQuestions) {
			if (theSurveyQuestion.getQuestionData().getQuestionId() == theQuestionMetaData.getQuestionId()) {
				exitingQuestion = theSurveyQuestion;
				break;
			}
		}
		if (null == exitingQuestion) {
			SurveyQuestion newQuestion = new SurveyQuestion();
			newQuestion.setQuestionData(theQuestionMetaData);
			surveyQuestions.add(newQuestion);
			isAdded = true;
		} else {
			throw new QuestionExitsException("Question Already Added");
		}
		return isAdded;
	}
}
