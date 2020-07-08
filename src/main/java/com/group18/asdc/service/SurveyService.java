package com.group18.asdc.service;

import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.errorhandling.QuestionExitsException;
import com.group18.asdc.errorhandling.SavingSurveyException;
import com.group18.asdc.errorhandling.SurveyAlreadyPublishedException;

public interface SurveyService {
	
	public boolean addQuestionToSurvey(QuestionMetaData theQuestionMetaData) throws QuestionExitsException;
	
	public SurveyMetaData getSavedSurvey(Course course);
	
	public boolean saveSurvey(SurveyMetaData surveyData) throws SavingSurveyException;
	
	public boolean publishSurvey() throws SurveyAlreadyPublishedException;	
	
	public boolean removeQuestionFromSurvey(QuestionMetaData theQuestionMetaData);
	
	public SurveyMetaData getCurrentSurvey();
	
	public boolean isSurveyPublishedForCourse(Course theCourse);

}
