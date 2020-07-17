package com.group18.asdc.service.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.errorhandling.PublishSurveyException;
import com.group18.asdc.errorhandling.QuestionExitsException;
import com.group18.asdc.errorhandling.SavingSurveyException;
import com.group18.asdc.service.SurveyService;

@SpringBootTest
public class SurveyServiceImplTest {

	private final static SurveyService surveyService = TestConfig.getTestSingletonIntance()
			.getServiceTestAbstractFactory().getSurveyServiceTest();

	@Test
	public void addQuestionToSurvey() {

		boolean isAdded;
		try {
			isAdded = surveyService.addQuestionToSurvey(
					TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getQuestionMetaDataTest());
		} catch (QuestionExitsException e) {
			isAdded = Boolean.FALSE;
		}
		assertTrue(isAdded);
	}

	@Test
	public void saveSurveyTest() {
		boolean isSaved = Boolean.FALSE;
		try {
			isSaved = surveyService.saveSurvey(
					TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getSurveyMetaDataTest());
			assertTrue(isSaved);
		} catch (SavingSurveyException e) {
			assertFalse(isSaved);
		}
	}

	@Test
	public void publishSurveyTest() {
		boolean isPublished;
		try {
			isPublished = surveyService.publishSurvey();
		} catch (PublishSurveyException e) {
			isPublished = Boolean.FALSE;
		}
		assertTrue(isPublished);
	}

	@Test
	public void removeQuestionFromSurveyTest() {
		boolean isRemoved = surveyService.removeQuestionFromSurvey(
				TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getQuestionMetaDataTest());
		assertTrue(isRemoved);
	}

	@Test
	public void isSurveyPublishedForCourseTest() {
		boolean isPublished = surveyService.isSurveyPublishedForCourse(
				TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest());
		assertFalse(isPublished);
	}

	@Test
	public void getSavedSurveyTest() {
		SurveyMetaData surveyData = surveyService
				.getSavedSurvey(TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getCourseTest());
		assertNotNull(surveyData);
	}

	@Test
	public void getCurrentSurveyTest() {
		SurveyMetaData surveyData = surveyService.getCurrentSurvey();
		assertNotNull(surveyData);
	}
}
