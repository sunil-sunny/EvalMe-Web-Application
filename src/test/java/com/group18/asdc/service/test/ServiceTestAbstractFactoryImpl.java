package com.group18.asdc.service.test;

import com.group18.asdc.service.AdminService;
import com.group18.asdc.service.CourseDetailsService;
import com.group18.asdc.service.CourseRolesService;
import com.group18.asdc.service.CreateQuestionService;
import com.group18.asdc.service.DeleteQuestionService;
import com.group18.asdc.service.RegisterService;
import com.group18.asdc.service.SurveyService;
import com.group18.asdc.service.UserService;
import com.group18.asdc.service.ViewQuestionsService;

public class ServiceTestAbstractFactoryImpl implements ServiceTestAbstractFactory {

	@Override
	public AdminService getAdminServiceTest() {
		return new AdminServiceImplMock();
	}

	@Override
	public CourseDetailsService getCourseDetailsServiceTest() {
		return new CourseServiceImplMock();
	}

	@Override
	public CourseRolesService getCourseRoleServiceTest() {
		return new CourseRolesServiceMock();
	}

	@Override
	public CreateQuestionService getCreateQuestionServiceTest() {
		return new CreateQuestionServiceImplMock();
	}

	@Override
	public DeleteQuestionService getDeleteQuestionServiceTest() {
		return new DeleteQuestionServiceImplMock();
	}

	@Override
	public SurveyService getSurveyServiceTest() {
		return new SurveyServiceImplMock();
	}

	@Override
	public UserService getUserService() {
		return new UserServiceImplMock();
	}

	@Override
	public RegisterService getRegisterService() {
		return new RegisterServiceMock();
	}

	@Override
	public ViewQuestionsService getViewQuestionsService() {
		return new ViewQuestionsServiceImplMock();
	}
}
