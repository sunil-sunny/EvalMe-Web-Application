package com.group18.asdc.dao.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.group18.asdc.dao.ViewQuestionsDao;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.User;

@SpringBootTest
public class ViewQuestionsDaoImplTest {

	@Test
	public void getAllQuestionsTest() {
		ViewQuestionsDao theViewQuestionsDaoImplMock = new ViewQuestionsDaoImplMock();
		List<QuestionMetaData> theQuestionList = theViewQuestionsDaoImplMock.getAllQuestions(new User());
		boolean assertValue = false;
		if (theQuestionList.size() > 0) {
			assertValue = true;
		}
		assertTrue(assertValue);
	}

	@Test
	public void getAllQuestionsSortByDateTest() {
		ViewQuestionsDao theViewQuestionsDaoImplMock = new ViewQuestionsDaoImplMock();
		List<QuestionMetaData> theQuestionList = theViewQuestionsDaoImplMock.getAllQuestionsSortByDate(new User());
		boolean assertValue = false;
		if (theQuestionList.size() > 0) {
			assertValue = true;
		}
		assertTrue(assertValue);
	}

	@Test
	public void getAllQuestionsSortByTitleTest() {
		ViewQuestionsDao theViewQuestionsDaoImplMock = new ViewQuestionsDaoImplMock();
		List<QuestionMetaData> theQuestionList = theViewQuestionsDaoImplMock.getAllQuestionsSortByTitle(new User());
		boolean assertValue = false;
		if (theQuestionList.size() > 0) {
			assertValue = true;
		}
		assertTrue(assertValue);
	}

	@Test
	public void getQuestionByIdTest() {
		ViewQuestionsDao theViewQuestionsDaoImplMock = new ViewQuestionsDaoImplMock();
		QuestionMetaData question = theViewQuestionsDaoImplMock.getQuestionById(1);
		assertNotNull(question);
	}
}
