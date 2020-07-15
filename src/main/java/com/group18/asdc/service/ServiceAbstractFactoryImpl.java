package com.group18.asdc.service;

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
		return null;
	}

	@Override
	public IJavaMailSenderConfiguration getJavaMailSenderConfiguration() {
		
		return null;
	}

	@Override
	public PasswordHistoryService getPasswordHistoryService() {
		
		return null;
	}

	@Override
	public RegisterService getRegisterService() {
		return new RegisterServiceImpl();
	}

	@Override
	public SubmitAnswerService getSubmitAnswerService() {
		return new SubmitAnswerServiceImpl();
	}
	
	@Override
	public UserService getUserService() {
		return null;
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
}
