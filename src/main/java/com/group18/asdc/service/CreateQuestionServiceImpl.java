package com.group18.asdc.service;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.CreateQuestionDao;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.User;

public class CreateQuestionServiceImpl implements CreateQuestionService {

	@Override
	public boolean createNumericOrTextQuestion(BasicQuestionData theBasicQuestionData) {
		CreateQuestionDao theCreateQuestionDao = SystemConfig.getSingletonInstance().getTheCreateQuestionDao();
		UserService theUserService = SystemConfig.getSingletonInstance().getTheUserService();
		User theUser = theUserService.getCurrentUser();
		boolean isQuestionExist = theCreateQuestionDao.isQuestionExists(theBasicQuestionData);
		if (isQuestionExist) {
			return false;
		} else {
			return theCreateQuestionDao.createNumericOrTextQuestion(theBasicQuestionData, theUser);
		}
	}

	@Override
	public boolean createMultipleQuestion(MultipleChoiceQuestion theMultipleChoiceChoose) {
		CreateQuestionDao theCreateQuestionDao = SystemConfig.getSingletonInstance().getTheCreateQuestionDao();
		UserService theUserService = SystemConfig.getSingletonInstance().getTheUserService();
		User theUser = theUserService.getCurrentUser();
		BasicQuestionData theBasicQuestionData = new BasicQuestionData();
		theBasicQuestionData.setQuestionTitle(theMultipleChoiceChoose.getQuestionTitle());
		theBasicQuestionData.setQuestionText(theMultipleChoiceChoose.getQuestionText());
		theBasicQuestionData.setQuestionType(theMultipleChoiceChoose.getQuestionType());
		boolean isQuestionExist = theCreateQuestionDao.isQuestionExists(theBasicQuestionData);
		if (isQuestionExist) {
			return false;
		} else {
			return theCreateQuestionDao.createMultipleChoiceQuestion(theMultipleChoiceChoose, theUser);
		}
	}
}
