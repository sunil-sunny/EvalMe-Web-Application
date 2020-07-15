package com.group18.asdc.service.test;

import com.group18.asdc.service.AdminService;
import com.group18.asdc.service.CourseDetailsService;
import com.group18.asdc.service.CourseRolesService;
import com.group18.asdc.service.CreateQuestionService;
import com.group18.asdc.service.DeleteQuestionService;
import com.group18.asdc.service.SurveyService;
import com.group18.asdc.service.UserService;

public interface ServiceTestAbstractFactory {
	
	public AdminService getAdminServiceTest();
	
	public CourseDetailsService getCourseDetailsServiceTest();
	
	public CourseRolesService getCourseRoleServiceTest();
	
	public CreateQuestionService getCreateQuestionServiceTest();
	
	public DeleteQuestionService getDeleteQuestionServiceTest();
	
	public SurveyService getSurveyServiceTest();
	
	public UserService getUserService();

}
