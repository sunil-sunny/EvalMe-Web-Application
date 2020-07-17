package com.group18.asdc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.SurveyAnswerDao;
import com.group18.asdc.entities.Answer;
import com.group18.asdc.util.IQueryVariableToArrayList;

public class SurveyAnswerServiceImpl implements SurveyAnswersService {

    private Logger logger = Logger.getLogger(SurveyAnswersService.class.getName());
    private SurveyAnswerDao surveyAnswerDao;

    public SurveyAnswerServiceImpl() {
        surveyAnswerDao = SystemConfig.getSingletonInstance().getDaoAbstractFactory().getSurveyAnswerDao();
    }

    @Override
    public ArrayList fetchAnswersForSurvey(Integer surveyId, IQueryVariableToArrayList queryVariableToArrayList) {
        logger.log(Level.INFO, "Fetching answers for the survey=" + surveyId);
        ArrayList<Answer> answersList = new ArrayList();
        ArrayList<Object> valuesList = queryVariableToArrayList.convertQueryVariablesToArrayList(surveyId);
        ArrayList<HashMap> resultList = surveyAnswerDao.fetchAnswersForSurvey(valuesList);
        for (HashMap eachAnswer : resultList) {
            Answer answer = new Answer((String) eachAnswer.get("answer"), (String) eachAnswer.get("bannerid"),
                    (Integer) eachAnswer.get("surveyquestionid"));
            answersList.add(answer);
        }
        return answersList;
    }
}