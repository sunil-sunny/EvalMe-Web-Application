package com.group18.asdc.service;

import java.util.List;
import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.ViewQuestionsDao;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.User;

public class ViewQuestionsServiceImpl implements ViewQuestionsService {

	private static final ViewQuestionsDao theViewQuestionsDao = SystemConfig.getSingletonInstance()
			.getDaoAbstractFactory().getViewQuestionsDao();

	@Override
	public List<QuestionMetaData> getAllQuestions() {
		UserService theUserService = SystemConfig.getSingletonInstance().getServiceAbstractFactory().getUserService(
				SystemConfig.getSingletonInstance().getUtilAbstractFactory().getQueryVariableToArrayList());
		User currentUser = theUserService.getCurrentUser();
		return theViewQuestionsDao.getAllQuestions(currentUser);
	}

	@Override
	public List<QuestionMetaData> getAllQuestionsSortByDate() {
		UserService theUserService = SystemConfig.getSingletonInstance().getServiceAbstractFactory().getUserService(
				SystemConfig.getSingletonInstance().getUtilAbstractFactory().getQueryVariableToArrayList());
		User currentUser = theUserService.getCurrentUser();
		return theViewQuestionsDao.getAllQuestionsSortByDate(currentUser);
	}

	@Override
	public List<QuestionMetaData> getAllQuestionsSortByTitle() {
		UserService theUserService = SystemConfig.getSingletonInstance().getServiceAbstractFactory().getUserService(
				SystemConfig.getSingletonInstance().getUtilAbstractFactory().getQueryVariableToArrayList());
		User currentUser = theUserService.getCurrentUser();
		return theViewQuestionsDao.getAllQuestionsSortByTitle(currentUser);
	}

	@Override
	public QuestionMetaData getQuestionById(int questionId) {
		QuestionMetaData theQuestionMetaData = theViewQuestionsDao.getQuestionById(questionId);
		return theQuestionMetaData;
	}
}