package com.group18.asdc.service.test;

import java.util.ArrayList;
import java.util.HashMap;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.SurveyAnswerDao;
import com.group18.asdc.entities.Answer;
import com.group18.asdc.service.SurveyAnswersService;
import com.group18.asdc.util.IQueryVariableToArrayList;

public class SurveyAnswerServiceMockImpl implements SurveyAnswersService {

    @Override
    public ArrayList fetchAnswersForSurvey(Integer surveyId, IQueryVariableToArrayList queryVariableToArrayList) {
       ArrayList<Answer>  answersList = new ArrayList<>();
        SurveyAnswerDao surveyAnswerDao = TestConfig.getTestSingletonIntance().getDaoTestAbstractFactory().getSurveyAnswerDao();
        ArrayList<HashMap> resultList = surveyAnswerDao.fetchAnswersForSurvey(new ArrayList<>());
         for (HashMap eachAnswer : resultList) {
            Answer answer = new Answer((String) eachAnswer.get("answer"), (String) eachAnswer.get("bannerid"),
                    (Integer) eachAnswer.get("surveyquestionid"));
            answersList.add(answer);
        }
        return answersList;
    }
    
}