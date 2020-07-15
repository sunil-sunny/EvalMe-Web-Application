package com.group18.asdc.service;

import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.SurveyGroups;

public interface GroupFormationService {
	
	public SurveyGroups getGroupFormationResults(Course course);

}
