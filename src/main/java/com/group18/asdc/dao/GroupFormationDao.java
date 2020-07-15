package com.group18.asdc.dao;

import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.SurveyGroups;

public interface GroupFormationDao {
	
	public SurveyGroups getGroupFormationResults(Course course);
}
