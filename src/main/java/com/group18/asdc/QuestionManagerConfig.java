package com.group18.asdc;

import com.group18.asdc.dao.CreateQuestionDao;
import com.group18.asdc.dao.DeleteQuestionDao;
import com.group18.asdc.dao.DeleteQuestionDaoImpl;
import com.group18.asdc.dao.ViewQuestionsDao;
import com.group18.asdc.dao.ViewQuestionsDaoImpl;
import com.group18.asdc.service.CreateQuestionService;
import com.group18.asdc.service.CreateQuestionServiceImpl;
import com.group18.asdc.service.DeleteQuestionService;
import com.group18.asdc.service.DeleteQuestionServiceImpl;
import com.group18.asdc.service.ViewQuestionsService;
import com.group18.asdc.service.ViewQuestionsServiceImpl;

public class QuestionManagerConfig {

	private static QuestionManagerConfig singletonInstance = null;
	private CreateQuestionService theCreateQuestionService;
	private ViewQuestionsService theViewQuestionsService;
	private DeleteQuestionService theDeleteQuestionService;
	private CreateQuestionDao theCreateQuestionDao;
	private ViewQuestionsDao theViewQuestionsDao;
	private DeleteQuestionDao theDeleteQuestionDao;

	private QuestionManagerConfig() {
		this.theCreateQuestionService = new CreateQuestionServiceImpl();
		this.theViewQuestionsService = new ViewQuestionsServiceImpl();
		this.theDeleteQuestionService = new DeleteQuestionServiceImpl();
		this.theViewQuestionsDao = new ViewQuestionsDaoImpl();
		this.theDeleteQuestionDao = new DeleteQuestionDaoImpl();

	}

	public static QuestionManagerConfig getSingletonInstance() {
		if (null == singletonInstance) {
			singletonInstance = new QuestionManagerConfig();
		}
		return singletonInstance;
	}

	public CreateQuestionService getTheCreateQuestionService() {
		return theCreateQuestionService;
	}

	public void setTheCreateQuestionService(CreateQuestionService theCreateQuestionService) {
		this.theCreateQuestionService = theCreateQuestionService;
	}

	public CreateQuestionDao getTheCreateQuestionDao() {
		return theCreateQuestionDao;
	}

	public DeleteQuestionService getTheDeleteQuestionService() {
		return theDeleteQuestionService;
	}

	public void setTheDeleteQuestionService(DeleteQuestionService theDeleteQuestionService) {
		this.theDeleteQuestionService = theDeleteQuestionService;
	}

	public DeleteQuestionDao getTheDeleteQuestionDao() {
		return theDeleteQuestionDao;
	}

	public void setTheDeleteQuestionDao(DeleteQuestionDao theDeleteQuestionDao) {
		this.theDeleteQuestionDao = theDeleteQuestionDao;
	}

	public void setTheCreateQuestionDao(CreateQuestionDao theCreateQuestionDao) {
		this.theCreateQuestionDao = theCreateQuestionDao;
	}

	public ViewQuestionsService getTheViewQuestionsService() {
		return theViewQuestionsService;
	}

	public void setTheViewQuestionsService(ViewQuestionsService theViewQuestionsService) {
		this.theViewQuestionsService = theViewQuestionsService;
	}

	public ViewQuestionsDao getTheViewQuestionsDao() {
		return theViewQuestionsDao;
	}

	public void setTheViewQuestionsDao(ViewQuestionsDao theViewQuestionsDao) {
		this.theViewQuestionsDao = theViewQuestionsDao;
	}
}
