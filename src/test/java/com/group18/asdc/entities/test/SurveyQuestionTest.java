package com.group18.asdc.entities.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.Option;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyQuestion;

@SpringBootTest
public class SurveyQuestionTest {

	@Test
	public void getLogicDetail() {
		SurveyQuestion surveyQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyQuestionTest();
		surveyQuestion.setLogicDetail("group dissimilar");
		assertTrue(surveyQuestion.getLogicDetail().equals("group dissimilar"));
	}

	@Test
	public void setLogicDetail() {
		SurveyQuestion surveyQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyQuestionTest();
		surveyQuestion.setLogicDetail("group dissimilar");
		assertTrue(surveyQuestion.getLogicDetail().equals("group dissimilar"));
	}

	@Test
	public void getLogicConstraint() {
		SurveyQuestion surveyQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyQuestionTest();
		surveyQuestion.setLogicConstraint(3);
		assertTrue(surveyQuestion.getLogicConstraint() == 3);
	}

	@Test
	public void setLogicConstraint() {
		SurveyQuestion surveyQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyQuestionTest();
		surveyQuestion.setLogicConstraint(3);
		assertTrue(surveyQuestion.getLogicConstraint() == 3);
	}

	@Test
	public void getSurveyQuestionId() {
		SurveyQuestion surveyQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyQuestionTest();
		surveyQuestion.setSurveyQuestionId(4);
		assertTrue(surveyQuestion.getSurveyQuestionId() == 4);
	}

	@Test
	public void setSurveyQuestionId() {
		SurveyQuestion surveyQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyQuestionTest();
		surveyQuestion.setSurveyQuestionId(4);
		assertTrue(surveyQuestion.getSurveyQuestionId() == 4);
	}

	@Test
	public void getQuestionData() {
		SurveyQuestion surveyQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyQuestionTest();
		QuestionMetaData questionMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getQuestionMetaDataTest();
		questionMetaData.setQuestionId(24);
		surveyQuestion.setQuestionData(questionMetaData);
		BasicQuestionData basicQuestionData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getBasicQuestionDataTest();
		basicQuestionData.setQuestionText("Describe an experience you had with Java");
		basicQuestionData.setQuestionTitle("Java and Data Structures");
		basicQuestionData.setQuestionType("freetext");
		questionMetaData.setBasicQuestionData(basicQuestionData);

		surveyQuestion.setQuestionData(questionMetaData);
		assertTrue(surveyQuestion.getQuestionData().equals(questionMetaData));
	}

	@Test
	public void setQuestionData() {
		SurveyQuestion surveyQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyQuestionTest();
		QuestionMetaData questionMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getQuestionMetaDataTest();
		questionMetaData.setQuestionId(24);
		surveyQuestion.setQuestionData(questionMetaData);
		BasicQuestionData basicQuestionData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getBasicQuestionDataTest();
		basicQuestionData.setQuestionText("Describe an experience you had with Java");
		basicQuestionData.setQuestionTitle("Java and Data Structures");
		basicQuestionData.setQuestionType("freetext");
		questionMetaData.setBasicQuestionData(basicQuestionData);

		surveyQuestion.setQuestionData(questionMetaData);
		assertTrue(surveyQuestion.getQuestionData().equals(questionMetaData));
	}

	@Test
	public void getPriority() {
		SurveyQuestion surveyQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyQuestionTest();
		surveyQuestion.setPriority(6);
		assertTrue(surveyQuestion.getPriority() == 6);
	}

	@Test
	public void setPriority() {
		SurveyQuestion surveyQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyQuestionTest();
		surveyQuestion.setPriority(6);
		assertTrue(surveyQuestion.getPriority() == 6);
	}

	@Test
	public void getOptions() {
		SurveyQuestion surveyQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyQuestionTest();
		Option option = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getOptionTest();
		List<Option> optionList = new ArrayList<Option>();
		option.setDisplayText("Beginner");
		option.setStoredData(1);
		optionList.add(option);
		surveyQuestion.setOptions(optionList);
		assertTrue(surveyQuestion.getOptions().equals(optionList));
	}

	@Test
	public void setOptions() {
		SurveyQuestion surveyQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyQuestionTest();
		Option option = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getOptionTest();
		List<Option> optionList = new ArrayList<Option>();
		option.setDisplayText("Beginner");
		option.setStoredData(1);
		optionList.add(option);
		surveyQuestion.setOptions(optionList);
		assertTrue(surveyQuestion.getOptions().equals(optionList));
	}
}