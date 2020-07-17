package com.group18.asdc.service;

import java.util.ArrayList;

import com.group18.asdc.util.IQueryVariableToArrayList;

public interface SurveyAnswersService {

    public ArrayList fetchAnswersForSurvey(Integer surveyId, IQueryVariableToArrayList queryVariableToArrayList);
    
}