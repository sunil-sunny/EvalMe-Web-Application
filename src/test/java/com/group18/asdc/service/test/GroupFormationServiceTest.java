package com.group18.asdc.service.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashMap;

import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.ISurveyList;
import com.group18.asdc.groupformation.IGroupFormationBuilder;
import com.group18.asdc.groupformation.IGroupFormationDirector;
import com.group18.asdc.service.GroupFormationService;
import com.group18.asdc.service.GroupFormationServiceImpl;
import com.group18.asdc.service.SurveyAnswersService;
import com.group18.asdc.service.SurveyService;
import com.group18.asdc.util.IQueryVariableToArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GroupFormationServiceTest {

    @InjectMocks
    GroupFormationService groupFormationService = new GroupFormationServiceImpl();

    @Mock
    SurveyAnswersService surveyAnswersService = new SurveyAnswerServiceMockImpl();

    SurveyService surveyService = new SurveyServiceImplMock();

    @Mock
    IQueryVariableToArrayList queryVariableToArrayList;

    @Mock
    ISurveyList survey;

    @Mock
    IGroupFormationBuilder groupBuilder;

    @Mock
    IGroupFormationDirector director;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void formGroupsTest() {

        Course course = new Course();

        HashMap resultMap = groupFormationService.formGroupsForSurvey(course, surveyAnswersService, surveyService,
                queryVariableToArrayList);

        assertNotNull(resultMap);
        verify(surveyAnswersService, times(1)).fetchAnswersForSurvey(isA(Integer.class),
                isA(IQueryVariableToArrayList.class));

    }
}