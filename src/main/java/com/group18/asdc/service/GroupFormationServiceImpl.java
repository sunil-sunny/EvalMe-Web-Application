package com.group18.asdc.service;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.GroupFormationDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.SurveyGroups;

public class GroupFormationServiceImpl implements GroupFormationService {

	private static final GroupFormationDao theGroupFormationDao = SystemConfig.getSingletonInstance()
			.getDaoAbstractFactory().getGroupFormationDao();

	@Override
	public SurveyGroups getGroupFormationResults(Course course) {
		return theGroupFormationDao.getGroupFormationResults(course);
	}
}