package com.group18.asdc.dao.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.group18.asdc.dao.SurveyDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.errorhandling.SavingSurveyException;

@SpringBootTest
public class SurveyDaoImplTest {

	@Test
	public void getSavedSurveyTest() {
		SurveyDao theSurveyDao = new SurveyDaoImplMock();
		SurveyMetaData surveyData = theSurveyDao.getSavedSurvey(new Course());
		assertNotNull(surveyData);
	}

	@Test
	public void saveSurvey() throws SavingSurveyException {
		SurveyDao theSurveyDao = new SurveyDaoImplMock();
		boolean isSaved = theSurveyDao.saveSurvey(new SurveyMetaData());
		assertTrue(isSaved);
	}

	@Test
	public void isSurveyExists() {
		SurveyDao theSurveyDao = new SurveyDaoImplMock();
		boolean isExists = theSurveyDao.isSurveyExists(new Course());
		assertTrue(isExists);
	}

	@Test
	public void createSurvey() {
		SurveyDao theSurveyDao = new SurveyDaoImplMock();
		int surveyId = theSurveyDao.createSurvey(new Course());
		assertNotEquals(0, surveyId);
	}

	@Test
	public void publishSurveyTest() {
		SurveyDao theSurveyDao = new SurveyDaoImplMock();
		boolean isPublished = theSurveyDao.publishSurvey(new SurveyMetaData());
		assertTrue(isPublished);
	}

	@Test
	public void isSurveyPublished() {
		SurveyDao theSurveyDao = new SurveyDaoImplMock();
		boolean isPublished = theSurveyDao.isSurveyPublished(new Course());
		assertFalse(isPublished);
	}

}
