package com.group18.asdc.groupformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.entities.Answer;
import com.group18.asdc.entities.ISurveyList;
import com.group18.asdc.entities.ISurveyMetaData;
import com.group18.asdc.entities.SurveyQuestion;

public class SurveyAdapter implements ISurveyList {
    private List questionList;
    private List userAnswerList;
    private List usersList;
    private Set<String> userSet;

    private Logger logger = Logger.getLogger(SurveyAdapter.class.getName());

    public SurveyAdapter(ISurveyMetaData surveyMetaData, List<Answer> answersList) {

        logger.log(Level.INFO, "Adapter - Tranforming model objects to list");
        ArrayList<SurveyQuestion> surveyQuestionList = (ArrayList) surveyMetaData.getSurveyQuestions();
        HashMap<String, Object> questionMap = null;
        questionList = new ArrayList<>();
        for (SurveyQuestion eachSurveyQuestion : surveyQuestionList) {
            questionMap = new HashMap<>();
            questionMap.put("QUESTION_ID", eachSurveyQuestion.getSurveyQuestionId());
            questionMap.put("QUESTION_TYPE",
                    eachSurveyQuestion.getQuestionData().getBasicQuestionData().getQuestionType());
            questionMap.put("QUESTION_LOGIC", eachSurveyQuestion.getLogicDetail());
            questionMap.put("QUESTION_OPTIONS", eachSurveyQuestion.getOptions().size());
            questionMap.put("QUESTION_PRIORITY", eachSurveyQuestion.getPriority());
            questionList.add(questionMap);
        }

        HashMap userAnswerMap = null;
        ArrayList<String> userAnswerOptionsList = null;
        userSet = new HashSet();
        userAnswerList = new ArrayList<>();
        for (Answer eachAnswer : answersList) {
            userSet.add(eachAnswer.getBannerId());
        }
        usersList = new ArrayList<>();
        for (String eachUser : userSet) {
            userAnswerMap = new HashMap<>();
            for (Answer eachAnswer : answersList) {
                if (eachUser.equals(eachAnswer.getBannerId())) {
                    userAnswerOptionsList = new ArrayList<>();
                    String userAnswers = eachAnswer.getAnswers();
                    userAnswerOptionsList = new ArrayList(Arrays.asList(userAnswers.split(",")));
                    userAnswerMap.put(eachAnswer.getSurveyQuestionId(), userAnswerOptionsList);
                }
            }
            userAnswerList.add(userAnswerMap);
            usersList.add(eachUser);
        }
    }

   
    public List getQuestionList() {
        return this.questionList;
    }

   
    public List getAnswerList() {
        return this.userAnswerList;
    }

   
    public List getUserList() {
        return this.usersList;
    }

}