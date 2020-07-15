package com.group18.asdc.service;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.DeleteQuestionDao;

public class DeleteQuestionServiceImpl implements DeleteQuestionService {

	private static final DeleteQuestionDao theDeleteQuestionDao = SystemConfig.getSingletonInstance()
			.getDaoAbstractFactory().getDeleteQuestionDao();

	@Override
	public boolean deleteQuestion(int questionId) {
		return theDeleteQuestionDao.deleteQuestion(questionId);
	}
}
