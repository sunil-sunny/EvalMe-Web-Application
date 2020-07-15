package com.group18.asdc.dao.test;

import com.group18.asdc.dao.AdminDao;
import com.group18.asdc.dao.CourseDetailsDao;
import com.group18.asdc.dao.CourseRolesDao;
import com.group18.asdc.dao.CreateQuestionDao;
import com.group18.asdc.dao.DeleteQuestionDao;
import com.group18.asdc.dao.SurveyDao;
import com.group18.asdc.dao.UserDao;
import com.group18.asdc.dao.ViewQuestionsDao;

public interface DaoTestAbstractFactory {
	
	public AdminDao getAdminDaoTest();
	
	public CourseDetailsDao getCourseDaoTest();
	
	public CourseRolesDao getCourseRolesDaoTest();
	
	public CreateQuestionDao getCreateQuestionDaoTest();
	
	public DeleteQuestionDao getDeleteQuestionDaoTest();
	
	public SurveyDao getSurveyDaoTest();
	
	public UserDao getUserDaoTest();
	
	public ViewQuestionsDao getViewQuestionsDaoTest();
}
