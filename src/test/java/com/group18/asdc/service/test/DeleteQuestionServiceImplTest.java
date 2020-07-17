package com.group18.asdc.service.test;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.service.DeleteQuestionService;

@SpringBootTest
public class DeleteQuestionServiceImplTest {

	private static final DeleteQuestionService theDeleteQuestionServiceImplMock = TestConfig.getTestSingletonIntance()
			.getServiceTestAbstractFactory().getDeleteQuestionServiceTest();

	@Test
	public void deleteQuestionTest() {

		boolean isDeleted = theDeleteQuestionServiceImplMock.deleteQuestion(1);
		assertTrue(isDeleted);
	}
}