package com.group18.asdc.service;

import java.util.List;

import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.errorhandling.QuestionExitsException;
import com.group18.asdc.errorhandling.SurveyAlreadyPublishedException;

public interface SurveyService {
	
	public boolean addQuestionToSurvey(QuestionMetaData theQuestionMetaData) throws QuestionExitsException;
	
	public List<SurveyQuestion> getAllSavedSurveyQuestions(Course course);
	
	public boolean saveSurveyQuestions(List<SurveyQuestion> allSurveyQuestions);
	
	public boolean publishSurvey() throws SurveyAlreadyPublishedException;	
	
	public boolean removeQuestionFromSurvey(QuestionMetaData theQuestionMetaData);
	
	public List<SurveyQuestion> getCurrentListOfSurveyQuestions();

}
