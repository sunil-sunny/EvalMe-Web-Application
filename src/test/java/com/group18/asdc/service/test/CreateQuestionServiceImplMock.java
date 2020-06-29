package com.group18.asdc.service.test;

import com.group18.asdc.dao.CreateQuestionDao;
import com.group18.asdc.dao.test.CreateQuestionsDaoImplMock;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.CreateQuestionService;

public class CreateQuestionServiceImplMock implements CreateQuestionService {

	@Override
	public boolean createNumericOrTextQuestion(BasicQuestionData theBasicQuestionData) {

		CreateQuestionsDaoImplMock theCreateQuestionsDaoImplMock = new CreateQuestionsDaoImplMock();
		User theCurrentUser = new User();
		return theCreateQuestionsDaoImplMock.createNumericOrTextQuestion(theBasicQuestionData, theCurrentUser);
	}

	@Override
	public boolean createMultipleQuestion(MultipleChoiceQuestion theMultipleChoiceChoose) {
		CreateQuestionsDaoImplMock theCreateQuestionsDaoImplMock = new CreateQuestionsDaoImplMock();
		User theCurrentUser = new User();
		return theCreateQuestionsDaoImplMock.createMultipleChoiceQuestion(theMultipleChoiceChoose, theCurrentUser);
	}

}
