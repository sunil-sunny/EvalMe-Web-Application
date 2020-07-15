package com.group18.asdc.service.test;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.DeleteQuestionDao;
import com.group18.asdc.service.DeleteQuestionService;

public class DeleteQuestionServiceImplMock implements DeleteQuestionService {

	private static final DeleteQuestionDao theDaoImplMock = TestConfig.getTestSingletonIntance()
			.getDaoTestAbstractFactory().getDeleteQuestionDaoTest();

	@Override
	public boolean deleteQuestion(int questionId) {
		return theDaoImplMock.deleteQuestion(questionId);
	}
}
