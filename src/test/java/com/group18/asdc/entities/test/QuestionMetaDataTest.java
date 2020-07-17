package com.group18.asdc.entities.test;

import java.sql.Timestamp;
import java.util.Date;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.QuestionMetaData;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class QuestionMetaDataTest {

	@Test
	public void getQuestionId() {
		QuestionMetaData getQuestionIdData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getQuestionMetaDataTest();
		getQuestionIdData.setQuestionId(256);
		assertTrue(getQuestionIdData.getQuestionId() == 256);
	}

	@Test
	public void setQuestionId() {
		QuestionMetaData getQuestionIdData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getQuestionMetaDataTest();
		getQuestionIdData.setQuestionId(256);
		assertTrue(getQuestionIdData.getQuestionId() == 256);
	}

	@Test
	public void getBasicQuestionData() {
		BasicQuestionData getBasicQuestionData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getBasicQuestionDataTest();
		getBasicQuestionData.setQuestionText("Describe an experience you had when working with Java.");
		getBasicQuestionData.setQuestionTitle("Java and Data Structures");
		getBasicQuestionData.setQuestionType("freetext");
		QuestionMetaData questionMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getQuestionMetaDataTest();
		questionMetaData.setBasicQuestionData(getBasicQuestionData);
		assertTrue(questionMetaData.getBasicQuestionData().equals(getBasicQuestionData));
	}

	@Test
	public void setBasicQuestionData() {
		BasicQuestionData setBasicQuestionData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getBasicQuestionDataTest();
		setBasicQuestionData.setQuestionText("Describe an experience you had when working with Java.");
		setBasicQuestionData.setQuestionTitle("Java and Data Structures");
		setBasicQuestionData.setQuestionType("freetext");
		QuestionMetaData questionMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getQuestionMetaDataTest();
		questionMetaData.setBasicQuestionData(setBasicQuestionData);
		assertTrue(questionMetaData.getBasicQuestionData().equals(setBasicQuestionData));
	}

	@Test
	public void getCreationDateTime() {
		Date date = new Date();
		long datetime = date.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		QuestionMetaData questionMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getQuestionMetaDataTest();
		questionMetaData.setCreationDateTime(timestamp);
	}

	@Test
	public void setCreationDateTime() {
		Date date = new Date();
		long datetime = date.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		QuestionMetaData questionMetaData = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getQuestionMetaDataTest();
		questionMetaData.setCreationDateTime(timestamp);
	}

}
