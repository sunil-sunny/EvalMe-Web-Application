package com.group18.asdc.service;

public interface ServiceAbstractFactory {

	public AdminService getAdminService();

	public CourseDetailsService getCourseDetailsService();

	public CourseRolesService getCourseRolesService();

	public CreateQuestionService getCreateQuestionService();

	public DeleteQuestionService getDeleteQuestionService();

	public EmailService getEmailService();

	public IJavaMailSenderConfiguration getJavaMailSenderConfiguration();

	public PasswordHistoryService getPasswordHistoryService();

	public RegisterService getRegisterService();

	public SubmitAnswerService getSubmitAnswerService();

	public UserService getUserService();

	public ViewQuestionsService getViewQuestionsService();
	
	public SurveyService getSurveyService();
	
	public GroupFormationService getGroupFormationService();

}
