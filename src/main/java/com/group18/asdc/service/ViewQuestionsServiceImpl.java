package com.group18.asdc.service;

import java.util.List;
import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.ViewQuestionsDao;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.User;

public class ViewQuestionsServiceImpl implements ViewQuestionsService {

	@Override
	public List<QuestionMetaData> getAllQuestions() {
		ViewQuestionsDao theViewQuestionsDao = SystemConfig.getSingletonInstance().getTheViewQuestionsDao();
		UserService theUserService = SystemConfig.getSingletonInstance().getTheUserService();
		User currentUser = theUserService.getCurrentUser();
		return theViewQuestionsDao.getAllQuestions(currentUser);
	}

	@Override
	public List<QuestionMetaData> getAllQuestionsSortByDate() {
		ViewQuestionsDao theViewQuestionsDao = SystemConfig.getSingletonInstance().getTheViewQuestionsDao();
		UserService theUserService = SystemConfig.getSingletonInstance().getTheUserService();
		User currentUser = theUserService.getCurrentUser();
		return theViewQuestionsDao.getAllQuestionsSortByDate(currentUser);
	}

	@Override
	public List<QuestionMetaData> getAllQuestionsSortByTitle() {
		ViewQuestionsDao theViewQuestionsDao = SystemConfig.getSingletonInstance().getTheViewQuestionsDao();
		UserService theUserService = SystemConfig.getSingletonInstance().getTheUserService();
		User currentUser = theUserService.getCurrentUser();
		return theViewQuestionsDao.getAllQuestionsSortByTitle(currentUser);
	}

	@Override
	public QuestionMetaData getQuestionById(int questionId) {
		ViewQuestionsDao theViewQuestionsDao = SystemConfig.getSingletonInstance().getTheViewQuestionsDao();
		QuestionMetaData theQuestionMetaData=theViewQuestionsDao.getQuestionById(questionId);
		return theQuestionMetaData;
	}
}
