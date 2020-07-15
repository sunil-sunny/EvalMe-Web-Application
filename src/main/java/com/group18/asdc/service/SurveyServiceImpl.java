package com.group18.asdc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.SurveyDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.errorhandling.PublishSurveyException;
import com.group18.asdc.errorhandling.QuestionExitsException;
import com.group18.asdc.errorhandling.SavingSurveyException;

public class SurveyServiceImpl implements SurveyService {

	private final Logger log = Logger.getLogger(SurveyServiceImpl.class.getName());
	private static SurveyMetaData surveyMetaData = new SurveyMetaData();
	private static final SurveyDao theSurveyDao = SystemConfig.getSingletonInstance().getDaoAbstractFactory()
			.getSurveyDao();

	@Override
	public SurveyMetaData getSavedSurvey(Course course) {

		surveyMetaData.setTheCourse(course);
		boolean isSurveyExists = theSurveyDao.isSurveyExists(course);
		if (isSurveyExists) {
			surveyMetaData = theSurveyDao.getSavedSurvey(course);
		} else {
			surveyMetaData.setPublishedStatus(Boolean.FALSE);
			surveyMetaData.setSurveyQuestions(new ArrayList<SurveyQuestion>());
		}
		return surveyMetaData;
	}

	@Override
	public boolean removeQuestionFromSurvey(QuestionMetaData theQuestionMetaData) {

		boolean isDeleted = Boolean.FALSE;
		SurveyQuestion exitingQuestion = null;
		for (SurveyQuestion theSurveyQuestion : surveyMetaData.getSurveyQuestions()) {
			if (theSurveyQuestion.getQuestionData().getQuestionId() == theQuestionMetaData.getQuestionId()) {
				exitingQuestion = theSurveyQuestion;
				break;
			}
		}
		if (null == exitingQuestion) {
			isDeleted = Boolean.FALSE;
		} else {
			List<SurveyQuestion> surveyQuestion = surveyMetaData.getSurveyQuestions();
			surveyQuestion.remove(exitingQuestion);
			surveyMetaData.setSurveyQuestions(surveyQuestion);
			isDeleted = Boolean.TRUE;
		}
		return isDeleted;
	}

	@Override
	public SurveyMetaData getCurrentSurvey() {
		return surveyMetaData;
	}

	@Override
	public boolean addQuestionToSurvey(QuestionMetaData theQuestionMetaData) throws QuestionExitsException {
		boolean isAdded = Boolean.FALSE;
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
			isAdded = Boolean.TRUE;
		} else {
			log.log(Level.FINE, "Survey already has the question with id " + theQuestionMetaData.getQuestionId());
			throw new QuestionExitsException("Question Already Added");
		}
		return isAdded;
	}

	@Override
	public boolean isSurveyPublishedForCourse(Course theCourse) {
		return theSurveyDao.isSurveyPublished(theCourse);
	}

	@Override
	public boolean saveSurvey(SurveyMetaData surveyData) throws SavingSurveyException {
		surveyData.setSurveyId(surveyMetaData.getSurveyId());
		if ((null != surveyData.getSurveyQuestions())) {
			if (0 == surveyData.getSurveyQuestions().size()) {
				throw new SavingSurveyException("Add questions before saving the survey");
			} else {
				if (theSurveyDao.isSurveyExists(surveyData.getTheCourse())) {
					surveyData.setSurveyId(surveyMetaData.getSurveyId());
					boolean isSaved = theSurveyDao.saveSurvey(surveyData);
					if (isSaved) {
						surveyMetaData = theSurveyDao.getSavedSurvey(surveyData.getTheCourse());
						return isSaved;
					} else {
						return isSaved;
					}
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
		} else {
			log.log(Level.WARNING, "Survey is empty and questions need to be added");
			throw new SavingSurveyException("Add questions before saving the survey");
		}
	}

	@Override
	public boolean publishSurvey() throws PublishSurveyException {
		
		if (null == surveyMetaData.getSurveyQuestions()) {
			throw new PublishSurveyException("Add questions before publishing the survey");
		} else {
			if (0 == surveyMetaData.getSurveyQuestions().size()) {
				throw new PublishSurveyException("Add questions before publishing the survey");
			} else {
				if (theSurveyDao.isSurveyPublished(surveyMetaData.getTheCourse())) {
					throw new PublishSurveyException("Survey Already Published");
				} else {
					boolean isPublished = theSurveyDao.publishSurvey(surveyMetaData);
					surveyMetaData.setPublishedStatus(isPublished);
					return isPublished;
				}
			}

		}
	}
}