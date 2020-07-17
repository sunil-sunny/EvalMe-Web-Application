package com.group18.asdc.groupformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.entities.QuestionType;
import com.group18.asdc.util.ConstantStringUtil;
import org.apache.commons.text.similarity.CosineDistance;

public class ComputeDistance implements IComputeDistance {

    private List<HashMap> userAnswerList = null;
    private List<HashMap> questionsList = null;
    private Float[][][] intermediateDistanceMatrix = null;
    private Float[][] distanceMatrix = null;
    private Logger logger = Logger.getLogger(ComputeDistance.class.getName());

    public ComputeDistance(List<HashMap> userAnswerList, List<HashMap> questionsList) {
        this.userAnswerList = userAnswerList;
        this.questionsList = questionsList;
        this.intermediateDistanceMatrix = new Float[userAnswerList.size()][userAnswerList.size()][questionsList.size()];
        this.distanceMatrix = new Float[userAnswerList.size()][userAnswerList.size()];
    }

    public Float[][] compute() {
        int questionsIterator = 0;
        logger.log(Level.INFO, "Computing distance matrix for the survey");
        for (HashMap eachQuestionMap : questionsList) {
            Integer questionId = (Integer) eachQuestionMap.get("QUESTION_ID");
            String questionType = (String) eachQuestionMap.get("QUESTION_TYPE");
            String questionLogic = (String) eachQuestionMap.get("QUESTION_LOGIC");
            Integer numberOfOptions = (Integer) eachQuestionMap.get("QUESTION_OPTIONS");
            Integer priority = (Integer) eachQuestionMap.get("QUESTION_PRIORITY");
            Integer currentUserIterator = 0;
            for (HashMap currentUserMap : userAnswerList) {
                ArrayList currentUserAnswer = (ArrayList) currentUserMap.get(questionId);
                int otherUserIterator = 0;
                for (HashMap otherUserMap : userAnswerList) {
                    ArrayList otherUserAnswer = (ArrayList) otherUserMap.get(questionId);
                    Float distance = 0.0f;
                    if (questionType.equals(QuestionType.MULTIPLE_CHOOSE_ONE.toString())) {
                        if (currentUserAnswer.get(0).equals(otherUserAnswer.get(0))) {
                            distance = 0.0f;
                        } else {
                            distance = 1.0f;
                        }
                        if (questionLogic.equals(ConstantStringUtil.GROUP_DISIMILAR.toString())) {
                            distance = 1.0f - distance;
                        }
                    } else if (questionType.equals(QuestionType.MULTIPLE_CHOOSE_MORE.toString())) {
                        Integer optionsMatched = 0;
                        for (Object eachOption : currentUserAnswer) {
                            eachOption = (String) eachOption;
                            if (otherUserAnswer.contains(eachOption)) {
                                optionsMatched++;
                            }
                        }
                        Integer totalItems = currentUserAnswer.size() + otherUserAnswer.size();
                        Integer unmatchedItems = totalItems - (2 * optionsMatched);
                        Float unmatchedNormalized = (float) unmatchedItems / (float) numberOfOptions;
                        if (questionLogic.equals(ConstantStringUtil.GROUP_SIMILAR.toString())) {
                            distance = unmatchedNormalized;
                        } else {
                            distance = 1.0f - unmatchedNormalized;
                        }
                    } else if (questionType.equals(QuestionType.NUMERIC_TYPE.toString())) {
                        distance = Math.abs(Integer.valueOf((String) currentUserAnswer.get(0))
                                - Integer.valueOf((String) otherUserAnswer.get(0))) / 10.0f;

                        if (questionLogic.equals(ConstantStringUtil.GROUP_DISIMILAR.toString())) {
                            distance = 1.0f - distance;
                        } else if (questionLogic.equals(ConstantStringUtil.GREATER_THAN.toString())
                                || questionLogic.equals(ConstantStringUtil.LESS_THAN.toString())) {
                            distance = 0f;
                        }

                    } else if (questionType.equals(QuestionType.FREE_TEXT.toString())) {
                        Double cosineDistanceOfText = new CosineDistance().apply((String) otherUserAnswer.get(0),
                                (String) currentUserAnswer.get(0));
                        if (questionLogic.equals(ConstantStringUtil.GROUP_SIMILAR.toString())) {
                            distance = 1.0f - cosineDistanceOfText.floatValue();
                        } else if (questionLogic.equals(ConstantStringUtil.GROUP_DISIMILAR.toString())) {
                            distance = cosineDistanceOfText.floatValue();
                        }
                    }
                    intermediateDistanceMatrix[currentUserIterator][otherUserIterator][questionsIterator] = (11
                            - priority) * distance;
                    otherUserIterator++;
                }
                currentUserIterator++;
            }
            questionsIterator++;
        }
        logger.log(Level.INFO, "Intermediate distance list computed");
        processDistanceMatrix();
        return distanceMatrix;
    }

    private void processDistanceMatrix() {
        for (Integer rowIter = 0; rowIter < userAnswerList.size(); rowIter++) {
            for (Integer columnIter = 0; columnIter < userAnswerList.size(); columnIter++) {
                Float[] questionsDistanceArray = intermediateDistanceMatrix[rowIter][columnIter];
                List<Float> questionsDistanceList = Arrays.asList(questionsDistanceArray);
                distanceMatrix[rowIter][columnIter] = (float) questionsDistanceList.stream()
                        .mapToDouble(Float::doubleValue).sum();
            }
        }
        logger.log(Level.INFO, "Computed distance list");
    }

}