package com.group18.asdc.service;

import java.util.List;
import com.group18.asdc.QuestionManagerConfig;
import com.group18.asdc.ProfileManagementConfig;
import com.group18.asdc.dao.ViewQuestionsDao;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.User;

public class ViewQuestionsServiceImpl implements ViewQuestionsService {

	@Override
	public List<QuestionMetaData> getAllQuestions() {
		ViewQuestionsDao theViewQuestionsDao = QuestionManagerConfig.getSingletonInstance().getTheViewQuestionsDao();
		UserService theUserService = ProfileManagementConfig.getSingletonInstance().getTheUserService();
		User currentUser = theUserService.getCurrentUser();
		return theViewQuestionsDao.getAllQuestions(currentUser);
	}

	@Override
	public List<QuestionMetaData> getAllQuestionsSortByDate() {
		ViewQuestionsDao theViewQuestionsDao = QuestionManagerConfig.getSingletonInstance().getTheViewQuestionsDao();
		UserService theUserService = ProfileManagementConfig.getSingletonInstance().getTheUserService();
		User currentUser = theUserService.getCurrentUser();
		return theViewQuestionsDao.getAllQuestionsSortByDate(currentUser);
	}

	@Override
	public List<QuestionMetaData> getAllQuestionsSortByTitle() {
		ViewQuestionsDao theViewQuestionsDao = QuestionManagerConfig.getSingletonInstance().getTheViewQuestionsDao();
		UserService theUserService = ProfileManagementConfig.getSingletonInstance().getTheUserService();
		User currentUser = theUserService.getCurrentUser();
		return theViewQuestionsDao.getAllQuestionsSortByTitle(currentUser);
	}

	@Override
	public QuestionMetaData getQuestionById(int questionId) {
		ViewQuestionsDao theViewQuestionsDao = QuestionManagerConfig.getSingletonInstance().getTheViewQuestionsDao();
		QuestionMetaData theQuestionMetaData = theViewQuestionsDao.getQuestionById(questionId);
		return theQuestionMetaData;
	}
}
