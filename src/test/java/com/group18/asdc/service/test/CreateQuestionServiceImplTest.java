package com.group18.asdc.service.test;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.MultipleChoiceQuestion;

@SpringBootTest
public class CreateQuestionServiceImplTest {

	@Test
	public void createNumericOrTextQuestionTest() {
		CreateQuestionServiceImplMock theCreateQuestionServiceImplMock = new CreateQuestionServiceImplMock();
		BasicQuestionData basicQuestionData = new BasicQuestionData();
		boolean isCreated = theCreateQuestionServiceImplMock.createNumericOrTextQuestion(basicQuestionData);
		assertTrue(isCreated);
	}

	@Test
	public void createMultipleQuestion() {
		CreateQuestionServiceImplMock theCreateQuestionServiceImplMock = new CreateQuestionServiceImplMock();
		MultipleChoiceQuestion theMultipleChoiceChoose = new MultipleChoiceQuestion();
		boolean isCreated = theCreateQuestionServiceImplMock.createMultipleQuestion(theMultipleChoiceChoose);
		assertTrue(isCreated);
	}

}
