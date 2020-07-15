package com.group18.asdc.errorhandling;

public interface ExceptionAbstractFactory {

	public FileProcessingException getFileProcessingException(String message);

	public PasswordPolicyException getPasswordPolicyException(String message);

	public PublishSurveyException getPublishSurveyException(String message);

	public QuestionExitsException getQuestionExitsException(String message);

	public SavingSurveyException getSavingSurveyException(String message);

}
