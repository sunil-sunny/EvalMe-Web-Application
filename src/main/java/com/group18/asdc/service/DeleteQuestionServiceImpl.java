package com.group18.asdc.service;

import com.group18.asdc.QuestionManagerConfig;
import com.group18.asdc.dao.DeleteQuestionDao;

public class DeleteQuestionServiceImpl implements DeleteQuestionService {

	@Override
	public boolean deleteQuestion(int questionId) {

		DeleteQuestionDao theDeleteQuestionDao = QuestionManagerConfig.getSingletonInstance().getTheDeleteQuestionDao();
		return theDeleteQuestionDao.deleteQuestion(questionId);
	}
}
