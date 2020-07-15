package com.group18.asdc.entities;

public class ModelAbstractFactoryImpl implements ModelAbstractFactory {

	@Override
	public IBasicQuestionData getBasicQuestionData() {
		return new BasicQuestionData();
	}

	@Override
	public IOption getOption() {
		return new Option();
	}

	@Override
	public ICourse getCourse() {
		return new Course();
	}

	@Override
	public IMultipleChoiceQuestion getMultipleChoiceQuestion() {
		return new MultipleChoiceQuestion();
	}

	@Override
	public IPasswordHistory getPasswordHistory() {
		return new PasswordHistory();
	}

	@Override
	public IQuestionMetaData getQuestionMetaData() {
		return new QuestionMetaData();
	}

	@Override
	public ISurveyMetaData getSurveyMetaData() {
		return new SurveyMetaData();
	}

	@Override
	public IUserRegistartionDetails getIUserRegistartionDetails() {
		return new UserRegistartionDetails();
	}

	@Override
	public IUser getUser() {
		return new User();
	}

	@Override
	public ISurveyQuestion getSurveyQuestion() {
		return new SurveyQuestion();
	}
}
