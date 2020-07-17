package com.group18.asdc.entities;

public class ModelAbstractFactoryImpl implements ModelAbstractFactory {

	@Override
	public BasicQuestionData getBasicQuestionData() {
		return new BasicQuestionData();
	}

	@Override
	public Option getOption() {
		return new Option();
	}

	@Override
	public Course getCourse() {
		return new Course();
	}

	@Override
	public MultipleChoiceQuestion getMultipleChoiceQuestion() {
		return new MultipleChoiceQuestion();
	}

	@Override
	public PasswordHistory getPasswordHistory() {
		return new PasswordHistory();
	}

	@Override
	public QuestionMetaData getQuestionMetaData() {
		return new QuestionMetaData();
	}

	@Override
	public SurveyMetaData getSurveyMetaData() {
		return new SurveyMetaData();
	}

	@Override
	public UserRegistartionDetails getIUserRegistartionDetails() {
		return new UserRegistartionDetails();
	}

	@Override
	public User getUser() {
		return new User();
	}

	@Override
	public SurveyQuestion getSurveyQuestion() {
		return new SurveyQuestion();
	}

	@Override
	public SurveyList getSurveyList() {

		return new SurveyList();
	}

	@Override
	public SurveyGroups getSurveyGroups() {

		return new SurveyGroups();
	}

	@Override
	public Group getGroup() {
		return new Group();
	}

	@Override
	public UserRegistartionDetails getIUserRegistartionDetails(User user) {
		return new UserRegistartionDetails(user);
	}

	@Override
	public Answer getAnswer(String answer, String bannerId, Integer surveyQuestionId) {
		return new Answer(answer, bannerId, surveyQuestionId);
	}
}
