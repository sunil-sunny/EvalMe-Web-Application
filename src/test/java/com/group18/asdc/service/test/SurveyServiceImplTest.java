package com.group18.asdc.service.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.errorhandling.QuestionExitsException;
import com.group18.asdc.errorhandling.SavingSurveyException;
import com.group18.asdc.errorhandling.SurveyAlreadyPublishedException;
import com.group18.asdc.service.SurveyService;

@SpringBootTest
public class SurveyServiceImplTest {

	@Test
	public void addQuestionToSurvey() {
		SurveyService surveyService = new SurveyServiceImplMock();
		boolean isAdded;
		try {
			isAdded = surveyService.addQuestionToSurvey(new QuestionMetaData());
		} catch (QuestionExitsException e) {
			isAdded = Boolean.FALSE;
		}
		assertTrue(isAdded);
	}

	@Test
	public void saveSurveyTest() {
		SurveyService surveyService = new SurveyServiceImplMock();
		boolean isSaved = Boolean.FALSE;
		try {
			isSaved = surveyService.saveSurvey(new SurveyMetaData());
			assertTrue(isSaved);
		} catch (SavingSurveyException e) {
			assertFalse(isSaved);
		}
	}

	@Test
	public void publishSurveyTest() {
		SurveyService surveyService = new SurveyServiceImplMock();
		boolean isPublished;
		try {
			isPublished = surveyService.publishSurvey();
		} catch (SurveyAlreadyPublishedException e) {
			isPublished = Boolean.FALSE;
		}
		assertTrue(isPublished);
	}

	@Test
	public void removeQuestionFromSurveyTest() {
		SurveyService surveyService = new SurveyServiceImplMock();
		boolean isRemoved = surveyService.removeQuestionFromSurvey(new QuestionMetaData());
		assertTrue(isRemoved);
	}

	@Test
	public void isSurveyPublishedForCourseTest() {
		SurveyService surveyService = new SurveyServiceImplMock();
		boolean isPublished = surveyService.isSurveyPublishedForCourse(new Course());
		assertFalse(isPublished);
	}

	@Test
	public void getSavedSurveyTest() {
		SurveyService surveyService = new SurveyServiceImplMock();
		SurveyMetaData surveyData = surveyService.getSavedSurvey(new Course());
		assertNotNull(surveyData);
	}

	@Test
	public void getCurrentSurveyTest() {
		SurveyService surveyService = new SurveyServiceImplMock();
		SurveyMetaData surveyData = surveyService.getCurrentSurvey();
		assertNotNull(surveyData);
	}
}
