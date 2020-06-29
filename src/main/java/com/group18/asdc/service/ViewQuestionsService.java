package com.group18.asdc.service;

import java.util.List;

import com.group18.asdc.entities.QuestionMetaData;

public interface ViewQuestionsService {

	public List<QuestionMetaData> getAllQuestions();

	public List<QuestionMetaData> getAllQuestionsSortByDate();

	public List<QuestionMetaData> getAllQuestionsSortByTitle();

}
