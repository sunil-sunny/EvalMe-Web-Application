package com.group18.asdc.dao;

import java.util.List;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.User;

public interface ViewQuestionsDao {

	public List<QuestionMetaData> getAllQuestions(User currentUser);

	public List<QuestionMetaData> getAllQuestionsSortByDate(User currentUser);

	public List<QuestionMetaData> getAllQuestionsSortByTitle(User currentUser);
	
	public QuestionMetaData getQuestionById(int questionId);
}
