package com.group18.asdc.dao.test;

import com.group18.asdc.dao.AdminDao;
import com.group18.asdc.dao.CourseDetailsDao;
import com.group18.asdc.dao.CourseRolesDao;
import com.group18.asdc.dao.CreateQuestionDao;
import com.group18.asdc.dao.DeleteQuestionDao;
import com.group18.asdc.dao.SurveyDao;
import com.group18.asdc.dao.UserDao;
import com.group18.asdc.dao.ViewQuestionsDao;

public class DaoTestAbstractFactoryImpl implements DaoTestAbstractFactory {

	@Override
	public AdminDao getAdminDaoTest() {
		return new AdminDaoImplMock();
	}

	@Override
	public CourseDetailsDao getCourseDaoTest() {
		return new CourseDaoImplMock();
	}

	@Override
	public CourseRolesDao getCourseRolesDaoTest() {
		return new CourseRolesDaoMock();
	}

	@Override
	public CreateQuestionDao getCreateQuestionDaoTest() {
		return new CreateQuestionsDaoImplMock();
	}

	@Override
	public DeleteQuestionDao getDeleteQuestionDaoTest() {
		return new DeleteQuestionDaoImplMock();
	}

	@Override
	public SurveyDao getSurveyDaoTest() {
		return new SurveyDaoImplMock();
	}

	@Override
	public UserDao getUserDaoTest() {
		return new UserDaoImplMock();
	}

	@Override
	public ViewQuestionsDao getViewQuestionsDaoTest() {
		return new ViewQuestionsDaoImplMock();
	}

}
