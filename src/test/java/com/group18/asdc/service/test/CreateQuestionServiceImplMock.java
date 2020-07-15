package com.group18.asdc.service.test;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.CreateQuestionDao;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.CreateQuestionService;

public class CreateQuestionServiceImplMock implements CreateQuestionService {

	private final static CreateQuestionDao theCreateQuestionsDaoImplMock = TestConfig.getTestSingletonIntance()
			.getDaoTestAbstractFactory().getCreateQuestionDaoTest();

	@Override
	public boolean createNumericOrTextQuestion(BasicQuestionData theBasicQuestionData) {

		User theCurrentUser = new User();
		return theCreateQuestionsDaoImplMock.createNumericOrTextQuestion(theBasicQuestionData, theCurrentUser);
	}

	@Override
	public boolean createMultipleQuestion(MultipleChoiceQuestion theMultipleChoiceChoose) {

		User theCurrentUser = new User();
		return theCreateQuestionsDaoImplMock.createMultipleChoiceQuestion(theMultipleChoiceChoose, theCurrentUser);
	}
}