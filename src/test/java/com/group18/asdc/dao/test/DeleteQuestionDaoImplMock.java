package com.group18.asdc.dao.test;

import java.util.ArrayList;
import java.util.List;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.DeleteQuestionDao;
import com.group18.asdc.entities.BasicQuestionData;

public class DeleteQuestionDaoImplMock implements DeleteQuestionDao {

	@Override
	public boolean deleteQuestion(int questionId) {
		List<BasicQuestionData> questionList = new ArrayList<BasicQuestionData>();
		questionList.add(TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getBasicQuestionDataTest());
		questionList.add(TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getBasicQuestionDataTest());
		questionList.remove(1);
		return Boolean.TRUE;
	}
}
