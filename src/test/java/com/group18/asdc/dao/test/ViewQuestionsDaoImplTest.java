package com.group18.asdc.dao.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.entities.QuestionMetaData;

@SpringBootTest
public class ViewQuestionsDaoImplTest {


	@Test
	public void getAllQuestionsTest() {
		
		ViewQuestionsDaoImplMock theViewQuestionsDaoImplMock=new ViewQuestionsDaoImplMock();
		List<QuestionMetaData> theQuestionList=theViewQuestionsDaoImplMock.getAllQuestions();
		boolean assertValue=false;
		if(theQuestionList.size()>0) {
			assertValue=true;
		}
		assertTrue(assertValue);
		
	}

	@Test
	public void getAllQuestionsSortByDateTest() {
		ViewQuestionsDaoImplMock theViewQuestionsDaoImplMock=new ViewQuestionsDaoImplMock();
		List<QuestionMetaData> theQuestionList=theViewQuestionsDaoImplMock.getAllQuestionsSortByDate();
		boolean assertValue=false;
		if(theQuestionList.size()>0) {
			assertValue=true;
		}
		assertTrue(assertValue);
		
	}

	
	@Test
	public void getAllQuestionsSortByTitleTest() {
		ViewQuestionsDaoImplMock theViewQuestionsDaoImplMock=new ViewQuestionsDaoImplMock();
		List<QuestionMetaData> theQuestionList=theViewQuestionsDaoImplMock.getAllQuestionsSortByTitle();
		boolean assertValue=false;
		if(theQuestionList.size()>0) {
			assertValue=true;
		}
		assertTrue(assertValue);
		
	}

}
