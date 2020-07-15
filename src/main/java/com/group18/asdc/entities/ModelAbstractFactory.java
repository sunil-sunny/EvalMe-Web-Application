package com.group18.asdc.entities;

public interface ModelAbstractFactory {

	public IBasicQuestionData getBasicQuestionData();
	
	public IOption getOption();
	
	public ICourse getCourse();
	
	public IMultipleChoiceQuestion getMultipleChoiceQuestion();
	
	public IPasswordHistory getPasswordHistory();
	
	public IQuestionMetaData getQuestionMetaData();
	
	public ISurveyMetaData getSurveyMetaData();
	
	public IUserRegistartionDetails getIUserRegistartionDetails();
	
	public IUser getUser();
	
	public ISurveyQuestion getSurveyQuestion();
	
}
