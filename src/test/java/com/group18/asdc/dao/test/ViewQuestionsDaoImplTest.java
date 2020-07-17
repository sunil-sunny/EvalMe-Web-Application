package com.group18.asdc.dao.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.ViewQuestionsDao;
import com.group18.asdc.entities.QuestionMetaData;

@SpringBootTest
public class ViewQuestionsDaoImplTest {

	private static final ViewQuestionsDao theViewQuestionsDaoImplMock = TestConfig.getTestSingletonIntance()
			.getDaoTestAbstractFactory().getViewQuestionsDaoTest();

	@Test
	public void getAllQuestionsTest() {

		List<QuestionMetaData> theQuestionList = theViewQuestionsDaoImplMock
				.getAllQuestions(TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest());
		boolean assertValue = Boolean.FALSE;
		if (theQuestionList.size() > 0) {
			assertValue = Boolean.TRUE;
		}
		assertTrue(assertValue);
	}

	@Test
	public void getAllQuestionsSortByDateTest() {
		List<QuestionMetaData> theQuestionList = theViewQuestionsDaoImplMock.getAllQuestionsSortByDate(
				TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest());
		boolean assertValue = Boolean.FALSE;
		if (theQuestionList.size() > 0) {
			assertValue = Boolean.TRUE;
		}
		assertTrue(assertValue);
	}

	@Test
	public void getAllQuestionsSortByTitleTest() {
		List<QuestionMetaData> theQuestionList = theViewQuestionsDaoImplMock.getAllQuestionsSortByTitle(
				TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest());
		boolean assertValue = Boolean.FALSE;
		if (theQuestionList.size() > 0) {
			assertValue = Boolean.TRUE;
		}
		assertTrue(assertValue);
	}

	@Test
	public void getQuestionByIdTest() {
		QuestionMetaData question = theViewQuestionsDaoImplMock.getQuestionById(1);
		assertNotNull(question);
	}
}
