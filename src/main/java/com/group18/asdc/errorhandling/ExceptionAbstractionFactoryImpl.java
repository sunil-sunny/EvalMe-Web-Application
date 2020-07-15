package com.group18.asdc.errorhandling;

public class ExceptionAbstractionFactoryImpl implements ExceptionAbstractFactory{

	@Override
	public FileProcessingException getFileProcessingException(String message) {
		return new FileProcessingException(message);
	}

	@Override
	public PasswordPolicyException getPasswordPolicyException(String message) {
		return new PasswordPolicyException(message);
	}

	@Override
	public PublishSurveyException getPublishSurveyException(String message) {
		return new PublishSurveyException(message);
	}

	@Override
	public QuestionExitsException getQuestionExitsException(String message) {
		return new QuestionExitsException(message);
	}

	@Override
	public SavingSurveyException getSavingSurveyException(String message) {
		return new SavingSurveyException(message);
	}

}
