package com.group18.asdc;

import com.group18.asdc.dao.SurveyDao;
import com.group18.asdc.dao.SurveyDaoImpl;
import com.group18.asdc.service.SurveyService;
import com.group18.asdc.service.SurveyServiceImpl;

public class SurveyConfig {

	private static SurveyConfig singletonInstance = null;
	private SurveyService theSurveyService;
	private SurveyDao theSurveyDao;

	private SurveyConfig() {
		this.theSurveyService = new SurveyServiceImpl();
		this.theSurveyDao = new SurveyDaoImpl();
	}

	public static SurveyConfig getSingletonInstance() {
		if (null == singletonInstance) {
			singletonInstance = new SurveyConfig();
		}
		return singletonInstance;
	}

	public SurveyService getTheSurveyService() {
		return theSurveyService;
	}

	public void setTheSurveyService(SurveyService theSurveyService) {
		this.theSurveyService = theSurveyService;
	}

	public SurveyDao getTheSurveyDao() {
		return theSurveyDao;
	}

	public void setTheSurveyDao(SurveyDao theSurveyDao) {
		this.theSurveyDao = theSurveyDao;
	}
}
