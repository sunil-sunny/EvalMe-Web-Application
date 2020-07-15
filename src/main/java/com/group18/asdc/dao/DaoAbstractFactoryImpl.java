package com.group18.asdc.dao;

public class DaoAbstractFactoryImpl implements DaoAbstractFactory {

	@Override
	public AdminDao getAdminDao() {
		return new AdminDaoImpl();
	}

	@Override
	public CourseDetailsDao getCourseDetailsDao() {
		return new CourseDetailsDaoImpl();
	}

	@Override
	public CourseRolesDao getCourseRolesDao() {
		return new CourseRolesDaoImpl();
	}

	@Override
	public CreateQuestionDao getCreateQuestionDao() {
		return new CreateQuestionDaoImpl();
	}

	@Override
	public DeleteQuestionDao getDeleteQuestionDao() {
		return new DeleteQuestionDaoImpl();
	}

	@Override
	public GroupFormationDao getGroupFormationDao() {
		return new GroupFormationDaoImpl();
	}

	@Override
	public PasswordHistoryDao getPasswordHistoryDao() {
		return new PasswordHistoryDaoImpl();
	}

	@Override
	public RegisterDao getRegisterDao() {
		return new RegisterDaoImpl();
	}

	@Override
	public SubmitAnswerDao getSubmitAnswerDao() {
		return new SubmitAnswerDaoImpl();
	}

	@Override
	public SurveyDao getSurveyDao() {
		return new SurveyDaoImpl();
	}

	@Override
	public UserDao getUserDao() {
		return new UserDaoImpl();
	}

	@Override
	public ViewQuestionsDao getViewQuestionsDao() {
		return new ViewQuestionsDaoImpl();
	}
}
