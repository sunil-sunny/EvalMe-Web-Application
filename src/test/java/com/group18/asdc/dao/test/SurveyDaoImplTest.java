package com.group18.asdc.dao.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.SurveyDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.errorhandling.PublishSurveyException;
import com.group18.asdc.errorhandling.SavingSurveyException;

@SpringBootTest
public class SurveyDaoImplTest {

	private static final SurveyDao theSurveyDao = TestConfig.getTestSingletonIntance().getDaoTestAbstractFactory()
			.getSurveyDaoTest();

	@Test
	public void getSavedSurveyTest() {
		SurveyMetaData surveyData = theSurveyDao.getSavedSurvey(new Course());
		assertNotNull(surveyData);
	}

	@Test
	public void saveSurvey() throws SavingSurveyException {
		boolean isSaved = theSurveyDao.saveSurvey(new SurveyMetaData());
		assertTrue(isSaved);
	}

	@Test
	public void isSurveyExists() {
		boolean isExists = theSurveyDao.isSurveyExists(new Course());
		assertTrue(isExists);
	}

	@Test
	public void createSurvey() {
		int surveyId = theSurveyDao.createSurvey(new Course());
		assertNotEquals(0, surveyId);
	}

	@Test
	public void publishSurveyTest() {
		boolean isPublished;
		try {
			isPublished = theSurveyDao.publishSurvey(new SurveyMetaData());
		} catch (PublishSurveyException e) {
			isPublished = Boolean.FALSE;
		}
		assertTrue(isPublished);
	}

	@Test
	public void isSurveyPublished() {
		boolean isPublished = theSurveyDao.isSurveyPublished(new Course());
		assertFalse(isPublished);
	}

}
