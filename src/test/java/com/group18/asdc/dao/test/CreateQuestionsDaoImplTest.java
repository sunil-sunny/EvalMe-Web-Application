package com.group18.asdc.dao.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.CreateQuestionDao;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.User;

@SpringBootTest
public class CreateQuestionsDaoImplTest {

	private static final CreateQuestionDao theCreateQuestionsDaoImplMock = TestConfig.getTestSingletonIntance()
			.getDaoTestAbstractFactory().getCreateQuestionDaoTest();

	@Test
	public void createNumericOrTextQuestionTest() {
		BasicQuestionData theBasicQuestionData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getBasicQuestionDataTest();
		User theUser = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		boolean isQuestionCreated = theCreateQuestionsDaoImplMock.createNumericOrTextQuestion(theBasicQuestionData,
				theUser);
		assertTrue(isQuestionCreated);
	}

	@Test
	public void createMultipleChoiceQuestionTest() {
		MultipleChoiceQuestion theMultipleChoiceQuestion = TestConfig.getTestSingletonIntance()
				.getModelTestAbstractFactory().getMultipleChoiceQuestionTest();
		User theUser = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		boolean isQuestionCreated = theCreateQuestionsDaoImplMock
				.createMultipleChoiceQuestion(theMultipleChoiceQuestion, theUser);
		assertTrue(isQuestionCreated);
	}

	@Test
	public void getIdForQuestionTypeTest() {
		int id = theCreateQuestionsDaoImplMock.getIdForQuestionType("freetext");
		boolean gotQuestionTitle;
		if (id > 0) {
			gotQuestionTitle = Boolean.TRUE;
		} else {
			gotQuestionTitle = Boolean.FALSE;
		}
		assertFalse(gotQuestionTitle);
	}

	@Test
	public void isQuestionExsistTest() {
		BasicQuestionData theBasicQuestionData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getBasicQuestionDataTest();
		boolean isQuestionExists = theCreateQuestionsDaoImplMock.isQuestionExists(theBasicQuestionData);
		assertTrue(isQuestionExists);
	}
}
