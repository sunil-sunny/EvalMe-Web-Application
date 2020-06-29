package com.group18.asdc.dao.test;

import java.util.ArrayList;
import java.util.List;

import com.group18.asdc.dao.DeleteQuestionDao;
import com.group18.asdc.entities.BasicQuestionData;

public class DeleteQuestionDaoImplMock implements DeleteQuestionDao {

	@Override
	public boolean deleteQuestion(int questionId) {
		List<BasicQuestionData> questionList=new ArrayList<BasicQuestionData>();
		questionList.add(new BasicQuestionData());
		questionList.add(new BasicQuestionData());
		questionList.remove(1);
		return true;
	}

}
