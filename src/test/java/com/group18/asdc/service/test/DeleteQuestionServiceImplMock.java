package com.group18.asdc.service.test;

import com.group18.asdc.dao.test.DeleteQuestionDaoImplMock;
import com.group18.asdc.service.DeleteQuestionService;

public class DeleteQuestionServiceImplMock implements DeleteQuestionService {

	@Override
	public boolean deleteQuestion(int questionId) {
		DeleteQuestionDaoImplMock theDaoImplMock=new DeleteQuestionDaoImplMock();
		return theDaoImplMock.deleteQuestion(questionId);
	}

}
