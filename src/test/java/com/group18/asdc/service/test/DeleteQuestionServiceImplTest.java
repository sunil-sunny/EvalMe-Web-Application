package com.group18.asdc.service.test;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.group18.asdc.service.DeleteQuestionService;

@SpringBootTest
public class DeleteQuestionServiceImplTest {

	@Test
	public void deleteQuestionTest() {
		DeleteQuestionService theDeleteQuestionServiceImplMock = new DeleteQuestionServiceImplMock();
		boolean isDeleted=theDeleteQuestionServiceImplMock.deleteQuestion(1);
		assertTrue(isDeleted);
	}
}