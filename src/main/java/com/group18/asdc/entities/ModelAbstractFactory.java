package com.group18.asdc.entities;

public interface ModelAbstractFactory {

	public BasicQuestionData getBasicQuestionData();

	public Option getOption();

	public Course getCourse();

	public MultipleChoiceQuestion getMultipleChoiceQuestion();

	public PasswordHistory getPasswordHistory();

	public QuestionMetaData getQuestionMetaData();

	public SurveyMetaData getSurveyMetaData();

	public UserRegistartionDetails getIUserRegistartionDetails();

	public UserRegistartionDetails getIUserRegistartionDetails(User user);

	public User getUser();

	public SurveyQuestion getSurveyQuestion();

	public SurveyList getSurveyList();

	public SurveyGroups getSurveyGroups();

	public Group getGroup();
	
	public Answer getAnswer(String answer, String bannerId, Integer surveyQuestionId);

}
