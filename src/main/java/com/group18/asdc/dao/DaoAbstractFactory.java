package com.group18.asdc.dao;

public interface DaoAbstractFactory {

	public AdminDao getAdminDao();

	public CourseDetailsDao getCourseDetailsDao();

	public CourseRolesDao getCourseRolesDao();

	public CreateQuestionDao getCreateQuestionDao();

	public DeleteQuestionDao getDeleteQuestionDao();

	public PasswordHistoryDao getPasswordHistoryDao();

	public RegisterDao getRegisterDao();

	public SurveyDao getSurveyDao();

	public UserDao getUserDao();

	public ViewQuestionsDao getViewQuestionsDao();

	public IPasswordPolicyDB getPasswordPolicyDB();

	public SurveyAnswerDao getSurveyAnswerDao();
}
