package com.group18.asdc.service.test;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.service.CreateQuestionService;

@SpringBootTest
public class CreateQuestionServiceImplTest {

	private final static CreateQuestionService theCreateQuestionServiceImplMock = TestConfig.getTestSingletonIntance()
			.getServiceTestAbstractFactory().getCreateQuestionServiceTest();

	@Test
	public void createNumericOrTextQuestionTest() {

		BasicQuestionData basicQuestionData = new BasicQuestionData();
		boolean isCreated = theCreateQuestionServiceImplMock.createNumericOrTextQuestion(basicQuestionData);
		assertTrue(isCreated);
	}

	@Test
	public void createMultipleQuestion() {
		MultipleChoiceQuestion theMultipleChoiceChoose = new MultipleChoiceQuestion();
		boolean isCreated = theCreateQuestionServiceImplMock.createMultipleQuestion(theMultipleChoiceChoose);
		assertTrue(isCreated);
	}
}
