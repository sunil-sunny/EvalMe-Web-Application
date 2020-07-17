package com.group18.asdc.service;

import com.group18.asdc.util.IQueryVariableToArrayList;

public interface ServiceAbstractFactory {

	public AdminService getAdminService();

	public CourseDetailsService getCourseDetailsService();

	public CourseRolesService getCourseRolesService();

	public CreateQuestionService getCreateQuestionService();

	public DeleteQuestionService getDeleteQuestionService();

	public EmailService getEmailService();

	public IJavaMailSenderConfiguration getJavaMailSenderConfiguration();

	public PasswordHistoryService getPasswordHistoryService(IQueryVariableToArrayList queryVariableToArrayList);

	public RegisterService getRegisterService();

	public UserService getUserService(IQueryVariableToArrayList queryVariableToArrayList);

	public ViewQuestionsService getViewQuestionsService();
	
	public SurveyService getSurveyService();
	
	public GroupFormationService getGroupFormationService();

	public SurveyAnswersService getSurveyAnswersService();

	public ResetPasswordService getResetPasswordService();

}
