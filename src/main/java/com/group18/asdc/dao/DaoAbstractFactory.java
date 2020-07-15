package com.group18.asdc.dao;

public interface DaoAbstractFactory {

	public AdminDao getAdminDao();

	public CourseDetailsDao getCourseDetailsDao();

	public CourseRolesDao getCourseRolesDao();

	public CreateQuestionDao getCreateQuestionDao();

	public DeleteQuestionDao getDeleteQuestionDao();

	public GroupFormationDao getGroupFormationDao();

	public PasswordHistoryDao getPasswordHistoryDao();

	public RegisterDao getRegisterDao();

	public SubmitAnswerDao getSubmitAnswerDao();

	public SurveyDao getSurveyDao();

	public UserDao getUserDao();

	public ViewQuestionsDao getViewQuestionsDao();
}
