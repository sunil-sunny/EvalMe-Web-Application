package com.group18.asdc.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.CreateQuestionDao;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.User;

public class CreateQuestionServiceImpl implements CreateQuestionService {

	private static final CreateQuestionDao theCreateQuestionDao = SystemConfig.getSingletonInstance()
			.getDaoAbstractFactory().getCreateQuestionDao();
	private Logger log = Logger.getLogger(CreateQuestionServiceImpl.class.getName());

	@Override
	public boolean createNumericOrTextQuestion(BasicQuestionData theBasicQuestionData) {

		UserService theUserService = SystemConfig.getSingletonInstance().getServiceAbstractFactory().getUserService(
				SystemConfig.getSingletonInstance().getUtilAbstractFactory().getQueryVariableToArrayList());
		User theUser = theUserService.getCurrentUser();
		boolean isQuestionExist = theCreateQuestionDao.isQuestionExists(theBasicQuestionData);
		if (isQuestionExist) {
			log.log(Level.WARNING,
					"Question with text " + theBasicQuestionData.getQuestionText() + " is already exists");
			return Boolean.FALSE;
		} else {
			return theCreateQuestionDao.createNumericOrTextQuestion(theBasicQuestionData, theUser);
		}
	}

	@Override
	public boolean createMultipleQuestion(MultipleChoiceQuestion theMultipleChoiceChoose) {

		UserService theUserService = SystemConfig.getSingletonInstance().getServiceAbstractFactory().getUserService(
				SystemConfig.getSingletonInstance().getUtilAbstractFactory().getQueryVariableToArrayList());
		User theUser = theUserService.getCurrentUser();
		BasicQuestionData theBasicQuestionData = SystemConfig.getSingletonInstance().getModelAbstractFactory()
				.getBasicQuestionData();
		theBasicQuestionData.setQuestionTitle(theMultipleChoiceChoose.getQuestionTitle());
		theBasicQuestionData.setQuestionText(theMultipleChoiceChoose.getQuestionText());
		theBasicQuestionData.setQuestionType(theMultipleChoiceChoose.getQuestionType());
		boolean isQuestionExist = theCreateQuestionDao.isQuestionExists(theBasicQuestionData);
		if (isQuestionExist) {
			log.log(Level.WARNING,
					"Question with text " + theBasicQuestionData.getQuestionText() + " is already exists");
			return Boolean.FALSE;
		} else {
			return theCreateQuestionDao.createMultipleChoiceQuestion(theMultipleChoiceChoose, theUser);
		}
	}
}
