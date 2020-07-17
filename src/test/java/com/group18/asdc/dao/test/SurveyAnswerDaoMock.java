package com.group18.asdc.dao.test;

import java.util.ArrayList;
import java.util.HashMap;

import com.group18.asdc.dao.SurveyAnswerDao;

public class SurveyAnswerDaoMock implements SurveyAnswerDao {

    @Override
    public ArrayList fetchAnswersForSurvey(ArrayList valueList) {
        ArrayList resultList = new ArrayList<>();
        HashMap resultMap = new HashMap<>();
        resultMap.put("answer", "1,2");
        resultMap.put("bannerid", "B00838575");
        resultMap.put("surveyquestionid", 1);
        resultList.add(resultMap);
        resultMap = new HashMap<>();
        resultMap.put("answer", "Hardworking");
        resultMap.put("bannerid", "B00838575");
        resultMap.put("surveyquestionid", 2);
        resultList.add(resultMap);
        resultMap = new HashMap<>();
        resultMap.put("answer", "5");
        resultMap.put("bannerid", "B00838575");
        resultMap.put("surveyquestionid", 3);
        resultList.add(resultMap);

        return resultList;
    }
    
}