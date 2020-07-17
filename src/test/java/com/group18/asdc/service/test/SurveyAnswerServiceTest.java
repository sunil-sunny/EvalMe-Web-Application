package com.group18.asdc.service.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.SurveyAnswerDao;
import com.group18.asdc.dao.test.SurveyAnswerDaoMock;
import com.group18.asdc.service.SurveyAnswerServiceImpl;
import com.group18.asdc.service.SurveyAnswersService;
import com.group18.asdc.util.IQueryVariableToArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SurveyAnswerServiceTest {

    @InjectMocks
    SurveyAnswersService surveyAnswerService = new SurveyAnswerServiceImpl();
    
    @Mock
    SurveyAnswerDao surveyAnswerDao;

    @Mock
    IQueryVariableToArrayList queryVariableToArrayList;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testFetchAnswersErrorCase()
    {
        Integer surveyId = 1;
        ArrayList valueList = new ArrayList<>();
        valueList.add(1);
        when(queryVariableToArrayList.convertQueryVariablesToArrayList(isA(Integer.class))).thenReturn(valueList);
        when(surveyAnswerDao.fetchAnswersForSurvey(isA(ArrayList.class))).thenReturn(new ArrayList<>());
        ArrayList resultList = surveyAnswerService.fetchAnswersForSurvey(surveyId,queryVariableToArrayList);

        assertEquals( 0 , resultList.size());
    }

    @Test
    public void testFetchAnswers()
    {
        SurveyAnswerDao surveyAnswerDaoTest = TestConfig.getTestSingletonIntance().getDaoTestAbstractFactory().getSurveyAnswerDao();
        Integer surveyId = 1;
        ArrayList valueList = new ArrayList<>();
        valueList.add(1);
        when(queryVariableToArrayList.convertQueryVariablesToArrayList(isA(Integer.class))).thenReturn(valueList);
        when(surveyAnswerDao.fetchAnswersForSurvey(isA(ArrayList.class))).thenReturn(surveyAnswerDaoTest.fetchAnswersForSurvey(valueList));
        ArrayList resultList = surveyAnswerService.fetchAnswersForSurvey(surveyId,queryVariableToArrayList);
        assertEquals( 3 , resultList.size());
    }
    
}