package com.group18.asdc.entities.test;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.entities.User;

@SpringBootTest
public class SurveyMetaDataTest {

	@Test
	public void getSurveyId() {
		SurveyMetaData surveyMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyMetaDataTest();
		surveyMetaData.setSurveyId(1);
		assertTrue(surveyMetaData.getSurveyId() == 1);
	}

	@Test
	public void setSurveyId() {
		SurveyMetaData surveyMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyMetaDataTest();
		surveyMetaData.setSurveyId(1);
		assertTrue(surveyMetaData.getSurveyId() == 1);
	}

	@Test
	public void getSurveyQuestions() {
		SurveyQuestion surveyQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyQuestionTest();
		surveyQuestion.setSurveyQuestionId(256);
		surveyQuestion.setLogicConstraint(1);
		surveyQuestion.setLogicDetail("group dissimilar");
		surveyQuestion.setPriority(1);
		QuestionMetaData questionMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getQuestionMetaDataTest();
		questionMetaData.setQuestionId(69);
		surveyQuestion.setQuestionData(questionMetaData);
		SurveyMetaData surveyMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyMetaDataTest();
		List<SurveyQuestion> surveyQuestionList = new ArrayList<SurveyQuestion>();
		surveyQuestionList.add(surveyQuestion);
		surveyMetaData.setSurveyQuestions(surveyQuestionList);
	}

	@Test
	public void setSurveyQuestions() {
		SurveyQuestion surveyQuestion = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyQuestionTest();
		surveyQuestion.setSurveyQuestionId(256);
		surveyQuestion.setLogicConstraint(1);
		surveyQuestion.setLogicDetail("group dissimilar");
		surveyQuestion.setPriority(1);
		QuestionMetaData questionMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getQuestionMetaDataTest();
		questionMetaData.setQuestionId(69);
		surveyQuestion.setQuestionData(questionMetaData);
		SurveyMetaData surveyMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyMetaDataTest();
		List<SurveyQuestion> surveyQuestionList = new ArrayList<SurveyQuestion>();
		surveyQuestionList.add(surveyQuestion);
		surveyMetaData.setSurveyQuestions(surveyQuestionList);
	}

	@Test
	public void isPublishedStatus() {
		SurveyMetaData surveyMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyMetaDataTest();
		surveyMetaData.setPublishedStatus(true);
		assertTrue(surveyMetaData.isPublishedStatus());
	}

	@Test
	public void setPublishedStatus() {
		SurveyMetaData surveyMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyMetaDataTest();
		surveyMetaData.setPublishedStatus(true);
		assertTrue(surveyMetaData.isPublishedStatus());
	}

	@Test
	public void getGroupSize() {
		SurveyMetaData surveyMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyMetaDataTest();
		surveyMetaData.setGroupSize(4);
		assertTrue(surveyMetaData.getGroupSize() == 4);
	}

	@Test
	public void setGroupSize() {
		SurveyMetaData surveyMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyMetaDataTest();
		surveyMetaData.setGroupSize(4);
		assertTrue(surveyMetaData.getGroupSize() == 4);
	}

	@Test
	public void getTheCourse() {
		SurveyMetaData surveyMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyMetaDataTest();
		Course course = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest();
		course.setCourseId(1995);
		course.setCourseName("Java and Data Structures");
		User user = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		user.setBannerId("B00842470");
		course.setInstructorName(user);
		surveyMetaData.setTheCourse(course);
		assertTrue(surveyMetaData.getTheCourse().equals(course));
	}

	@Test
	public void setTheCourse() {
		SurveyMetaData surveyMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getSurveyMetaDataTest();
		Course course = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest();
		course.setCourseId(1995);
		course.setCourseName("Java and Data Structures");
		User user = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getUserTest();
		user.setBannerId("B00842470");
		course.setInstructorName(user);
		surveyMetaData.setTheCourse(course);
		assertTrue(surveyMetaData.getTheCourse().equals(course));
	}
}
