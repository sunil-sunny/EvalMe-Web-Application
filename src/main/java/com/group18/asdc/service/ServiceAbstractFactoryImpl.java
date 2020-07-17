package com.group18.asdc.service;

import com.group18.asdc.util.IQueryVariableToArrayList;

public class ServiceAbstractFactoryImpl implements ServiceAbstractFactory {

	@Override
	public AdminService getAdminService() {
		return new AdminServiceImpl();
	}

	@Override
	public CourseDetailsService getCourseDetailsService() {
		return new CourseDetailsServiceImpl();
	}

	@Override
	public CourseRolesService getCourseRolesService() {
		return new CourseRolesServiceImpl();
	}

	@Override
	public CreateQuestionService getCreateQuestionService() {
		return new CreateQuestionServiceImpl();
	}

	@Override
	public DeleteQuestionService getDeleteQuestionService() {
		return new DeleteQuestionServiceImpl();
	}

	@Override
	public EmailService getEmailService() {
		return new EmailServiceImpl(getJavaMailSenderConfiguration());
	}

	@Override
	public IJavaMailSenderConfiguration getJavaMailSenderConfiguration() {
		return new DefaultMailSenderConfiguration();
	}

	@Override
	public PasswordHistoryService getPasswordHistoryService(IQueryVariableToArrayList queryVariableToArrayList) {		
		return new PasswordHistoryServiceImpl(queryVariableToArrayList);
	}

	@Override
	public RegisterService getRegisterService() {
		return new RegisterServiceImpl();
	}
	
	@Override
	public UserService getUserService(IQueryVariableToArrayList queryVariableToArrayList) {
		return new UserServiceImpl(queryVariableToArrayList);
	}

	@Override
	public ViewQuestionsService getViewQuestionsService() {
		return new ViewQuestionsServiceImpl();
	}

	@Override
	public SurveyService getSurveyService() {
		return new SurveyServiceImpl();
	}

	@Override
	public GroupFormationService getGroupFormationService() {
		return new GroupFormationServiceImpl();
	}

	@Override
	public SurveyAnswersService getSurveyAnswersService() {
		return new SurveyAnswerServiceImpl();
	}

	@Override
	public ResetPasswordService getResetPasswordService() {
		return new ResetPasswordServiceImpl();
	}

	
}
