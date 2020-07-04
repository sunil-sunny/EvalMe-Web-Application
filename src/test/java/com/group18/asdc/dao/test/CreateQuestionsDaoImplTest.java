package com.group18.asdc.dao.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.group18.asdc.dao.CreateQuestionDao;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.User;

@SpringBootTest
public class CreateQuestionsDaoImplTest {

	@Test
	public void createNumericOrTextQuestionTest() {
		BasicQuestionData theBasicQuestionData = new BasicQuestionData();
		User theUser = new User();
		CreateQuestionDao theCreateQuestionsDaoImplMock = new CreateQuestionsDaoImplMock();
		boolean isQuestionCreated = theCreateQuestionsDaoImplMock.createNumericOrTextQuestion(theBasicQuestionData,
				theUser);
		assertTrue(isQuestionCreated);
	}

	@Test
	public void createMultipleChoiceQuestionTest() {
		MultipleChoiceQuestion theMultipleChoiceQuestion = new MultipleChoiceQuestion();
		User theUser = new User();
		CreateQuestionDao theCreateQuestionsDaoImplMock = new CreateQuestionsDaoImplMock();
		boolean isQuestionCreated = theCreateQuestionsDaoImplMock
				.createMultipleChoiceQuestion(theMultipleChoiceQuestion, theUser);
		assertTrue(isQuestionCreated);
	}

	@Test
	public void getIdForQuestionTypeTest() {
		CreateQuestionDao theCreateQuestionsDaoImplMock = new CreateQuestionsDaoImplMock();
		int id = theCreateQuestionsDaoImplMock.getIdForQuestionType("freetext");
		boolean gotQuestionTitle = false;
		if (id > 0) {
			gotQuestionTitle = true;
		}
		assertFalse(gotQuestionTitle);
	}

	@Test
	public void isQuestionExsistTest() {
		BasicQuestionData theBasicQuestionData = new BasicQuestionData();
		CreateQuestionDao theCreateQuestionsDaoImplMock = new CreateQuestionsDaoImplMock();
		boolean isQuestionExists = theCreateQuestionsDaoImplMock.isQuestionExists(theBasicQuestionData);
		assertTrue(isQuestionExists);
	}
}
