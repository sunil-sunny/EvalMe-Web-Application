package com.group18.asdc.dao.test;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.DeleteQuestionDao;

@SpringBootTest
public class DeleteQuestionDaoImplTest {

	private static final DeleteQuestionDao theDaoImplMock = TestConfig.getTestSingletonIntance()
			.getDaoTestAbstractFactory().getDeleteQuestionDaoTest();

	@Test
	public void deleteQuestionTest() {
		boolean isDeleted = theDaoImplMock.deleteQuestion(1);
		assertTrue(isDeleted);
	}
}
