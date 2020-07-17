package com.group18.asdc.service.test;

import java.util.List;
import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.ViewQuestionsDao;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.ViewQuestionsService;

public class ViewQuestionsServiceImplMock implements ViewQuestionsService{

	private static final ViewQuestionsDao theViewQuestionsDao = TestConfig.
			getTestSingletonIntance().getDaoTestAbstractFactory().getViewQuestionsDaoTest();
	
	@Override
	public List<QuestionMetaData> getAllQuestions() {
		User user = new User();
		user.setBannerId("B00842470");
		return theViewQuestionsDao.getAllQuestions(user);
	}

	@Override
	public List<QuestionMetaData> getAllQuestionsSortByDate() {
		User user = new User();
		user.setBannerId("B00842470");
		return theViewQuestionsDao.getAllQuestionsSortByDate(user);
	}

	@Override
	public List<QuestionMetaData> getAllQuestionsSortByTitle() {
		User user = new User();
		user.setBannerId("B00842470");
		return theViewQuestionsDao.getAllQuestionsSortByTitle(user);
	}

	@Override
	public QuestionMetaData getQuestionById(int questionId) {
		return theViewQuestionsDao.getQuestionById(questionId);
	}
}