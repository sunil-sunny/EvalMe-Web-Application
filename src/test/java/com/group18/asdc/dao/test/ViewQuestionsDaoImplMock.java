package com.group18.asdc.dao.test;

import java.util.ArrayList;
import java.util.List;
import com.group18.asdc.dao.ViewQuestionsDao;
import com.group18.asdc.entities.QuestionMetaData;
import com.group18.asdc.entities.User;

public class ViewQuestionsDaoImplMock implements ViewQuestionsDao {

	private static List<QuestionMetaData> questionList = new ArrayList<QuestionMetaData>();

	public ViewQuestionsDaoImplMock() {
		QuestionMetaData theQuestionMetaData = new QuestionMetaData();
		QuestionMetaData theQuestionMetaData2 = new QuestionMetaData();
		ViewQuestionsDaoImplMock.questionList.add(theQuestionMetaData);
		ViewQuestionsDaoImplMock.questionList.add(theQuestionMetaData2);
	}

	@Override
	public List<QuestionMetaData> getAllQuestions(User currentUser) {
		return ViewQuestionsDaoImplMock.questionList;
	}

	@Override
	public List<QuestionMetaData> getAllQuestionsSortByDate(User currentUser) {
		ViewQuestionsDaoImplMock.questionList.size();
		return ViewQuestionsDaoImplMock.questionList;
	}

	@Override
	public List<QuestionMetaData> getAllQuestionsSortByTitle(User currentUser) {
		ViewQuestionsDaoImplMock.questionList.size();
		return ViewQuestionsDaoImplMock.questionList;
	}
}
