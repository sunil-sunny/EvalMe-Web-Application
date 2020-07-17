package com.group18.asdc.entities.test;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.group18.asdc.TestConfig;
import com.group18.asdc.entities.BasicQuestionData;

@SpringBootTest
public class BasicQuestionDataTest {

	@Test
	public void getQuestionTitle() {
		BasicQuestionData basicQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getBasicQuestionDataTest();

		basicQuestion.setQuestionTitle("Java and Data Structures");
		assertTrue(basicQuestion.getQuestionTitle().equals("Java and Data Structures"));
	}

	@Test
	public void setQuestionTitle() {
		BasicQuestionData basicQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getBasicQuestionDataTest();

		basicQuestion.setQuestionTitle("Java and Data Structures");
		assertTrue(basicQuestion.getQuestionTitle().equals("Java and Data Structures"));
	}

	@Test
	public void getQuestionText() {
		BasicQuestionData basicQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getBasicQuestionDataTest();
		basicQuestion.setQuestionText("How proficient are you in Java, on a scale of 1 to 10?");
		assertTrue(basicQuestion.getQuestionText().equals("How proficient are you in Java, on a scale of 1 to 10?"));
	}

	@Test
	public void setQuestionText() {
		BasicQuestionData basicQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getBasicQuestionDataTest();
		basicQuestion.setQuestionText("How proficient are you in Java, on a scale of 1 to 10?");
		assertTrue(basicQuestion.getQuestionText().equals("How proficient are you in Java, on a scale of 1 to 10?"));
	}

	@Test
	public void getQuestionType() {
		BasicQuestionData basicQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getBasicQuestionDataTest();
		basicQuestion.setQuestionType("freetext");
		assertTrue(basicQuestion.getQuestionType().equals("freetext"));
	}

	@Test
	public void setQuestionType() {
		BasicQuestionData basicQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getBasicQuestionDataTest();
		basicQuestion.setQuestionType("freetext");
		assertTrue(basicQuestion.getQuestionType().equals("freetext"));
	}
}