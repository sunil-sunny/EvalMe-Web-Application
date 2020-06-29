package com.group18.asdc.service;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.DeleteQuestionDao;

public class DeleteQuestionServiceImpl implements DeleteQuestionService {

	@Override
	public boolean deleteQuestion(int questionId) {

		DeleteQuestionDao theDeleteQuestionDao = SystemConfig.getSingletonInstance().getTheDeleteQuestionDao();
		return theDeleteQuestionDao.deleteQuestion(questionId);
	}
}
