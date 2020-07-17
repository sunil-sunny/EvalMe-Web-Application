package com.group18.asdc.entities.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.entities.Answer;

@SpringBootTest
public class AnswerTest {

	@Test
	public void setAnswer() {
		Answer answer = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getAnswerTest();
		answer.setAnswer("Beginner");
		assertTrue(answer.getAnswers().equals("Beginner"));
	}

	@Test
	public void getAnswers() {
		Answer answer = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getAnswerTest();
		answer.setAnswer("Beginner");
		assertTrue(answer.getAnswers().equals("Beginner"));
	}

	@Test
	public void setBannerId() {
		Answer answer = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getAnswerTest();
		answer.setBannerId("B00842470");
		assertTrue(answer.getBannerId().equals("B00842470"));
	}

	@Test
	public void getBannerId() {
		Answer answer = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getAnswerTest();
		answer.setBannerId("B00842470");
		assertTrue(answer.getBannerId().equals("B00842470"));
	}

	@Test
	public void setAnswerId() {
		Answer answer = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getAnswerTest();
		answer.setAnswerId(4);
		assertTrue(answer.getAnswerId() == 4);
	}

	@Test
	public void getAnswerId() {
		Answer answer = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getAnswerTest();
		answer.setAnswerId(4);
		assertTrue(answer.getAnswerId() == 4);
	}

	@Test
	public void setSurveyQuestionId() {
		Answer answer = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getAnswerTest();
		answer.setSurveyQuestionId(4);
		assertTrue(answer.getSurveyQuestionId() == 4);
	}

	@Test
	public void getSurveyQuestionId() {
		Answer answer = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getAnswerTest();
		answer.setSurveyQuestionId(4);
		assertTrue(answer.getSurveyQuestionId() == 4);
	}
}
