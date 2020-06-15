package com.group18.asdc.service;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.CreateQuestionDao;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.FreeTextQuestion;
import com.group18.asdc.entities.MultipleChoiceChooseMore;
import com.group18.asdc.entities.MultipleChoiceChooseOne;
import com.group18.asdc.entities.NumericQuestion;
import com.group18.asdc.entities.User;

public class CreateQuestionServiceImpl implements CreateQuestionService {

	@Override
	public boolean createMultipleChoiceChooseMoreQuestion(MultipleChoiceChooseMore theMultipleChoiceChooseMore) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createMultipleChoiceChooseOneQuestion(MultipleChoiceChooseOne theMultipleChoiceChooseOne) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createNumericQuestion(BasicQuestionData theBasicQuestionData) {

		CreateQuestionDao theCreateQuestionDao = SystemConfig.getSingletonInstance().getTheCreateQuestionDao();
		UserService theUserService=SystemConfig.getSingletonInstance().getTheUserService();
		User theUser=theUserService.getCurrentUser();
		boolean isQuestiontitleExists = theCreateQuestionDao.isQuestionTitleExists(theBasicQuestionData);

		if (!isQuestiontitleExists) {
			boolean isQuestionTitleCreated = theCreateQuestionDao.createQuestionTitle(theBasicQuestionData);
			if (isQuestionTitleCreated) {
				isQuestiontitleExists = true;
			}
		}
		
		if(isQuestiontitleExists) {
			NumericQuestion theNumericQuestion=new NumericQuestion();
			theNumericQuestion.setQuestionText(theBasicQuestionData.getQuestionText());
			theNumericQuestion.setQuestionTitle(theBasicQuestionData.getQuestionTitle());
			theNumericQuestion.setQuestionType(theBasicQuestionData.getQuestionType());
			return theCreateQuestionDao.createNumericQuestion(theNumericQuestion, theUser);	
		}

		return false;
	}

	@Override
	public boolean createFreeTextQuestion(BasicQuestionData theBasicQuestionData) {
		CreateQuestionDao theCreateQuestionDao = SystemConfig.getSingletonInstance().getTheCreateQuestionDao();
		UserService theUserService=SystemConfig.getSingletonInstance().getTheUserService();
		User theUser=theUserService.getCurrentUser();
		boolean isQuestiontitleExists = theCreateQuestionDao.isQuestionTitleExists(theBasicQuestionData);

		if (!isQuestiontitleExists) {
			boolean isQuestionTitleCreated = theCreateQuestionDao.createQuestionTitle(theBasicQuestionData);
			if (isQuestionTitleCreated) {
				isQuestiontitleExists = true;
			}
		}
		
		if(isQuestiontitleExists) {
			FreeTextQuestion theFreeTextQuestion=new FreeTextQuestion();
			theFreeTextQuestion.setQuestionText(theBasicQuestionData.getQuestionText());
			theFreeTextQuestion.setQuestionTitle(theBasicQuestionData.getQuestionTitle());
			theFreeTextQuestion.setQuestionType(theBasicQuestionData.getQuestionType());
			return theCreateQuestionDao.createFreeTextQuestion(theFreeTextQuestion, theUser);	
		}

		return false;
	}

}
