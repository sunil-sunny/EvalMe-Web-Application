package com.group18.asdc.dao.test;

import java.util.ArrayList;
import java.util.List;
import com.group18.asdc.dao.CreateQuestionDao;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.User;

public class CreateQuestionsDaoImplMock implements CreateQuestionDao {

	private static List<String> titles = new ArrayList<String>();
	private static List<String> types = new ArrayList<String>();
	private static List<BasicQuestionData> numberOrTextQuestions = new ArrayList<BasicQuestionData>();
	private static List<MultipleChoiceQuestion> multipleQuestions = new ArrayList<MultipleChoiceQuestion>();

	public CreateQuestionsDaoImplMock() {
		CreateQuestionsDaoImplMock.titles.add("java");
		CreateQuestionsDaoImplMock.titles.add("python");
		CreateQuestionsDaoImplMock.types.add("freetext");
		CreateQuestionsDaoImplMock.types.add("multiple-choice-choose-one");
		CreateQuestionsDaoImplMock.types.add("multiple-choice-choose-many");
		CreateQuestionsDaoImplMock.types.add("numericquestions");
	}

	@Override
	public boolean createNumericOrTextQuestion(BasicQuestionData theBasicQuestionData, User theUser) {
		CreateQuestionsDaoImplMock.numberOrTextQuestions.add(theBasicQuestionData);
		return Boolean.TRUE;
	}

	@Override
	public int getIdForQuestionType(String questionType) {
		int index = CreateQuestionsDaoImplMock.types.indexOf(questionType);
		return index;
	}

	@Override
	public boolean createMultipleChoiceQuestion(MultipleChoiceQuestion theMultipleChoiceChoose, User theUser) {
		CreateQuestionsDaoImplMock.multipleQuestions.add(theMultipleChoiceChoose);
		return Boolean.TRUE;
	}

	@Override
	public boolean isQuestionExists(BasicQuestionData theBasicQuestionData) {
		String questionTitle = theBasicQuestionData.getQuestionTitle();
		if (null == questionTitle) {
			return Boolean.TRUE;
		}
		return Boolean.TRUE;
	}
}
