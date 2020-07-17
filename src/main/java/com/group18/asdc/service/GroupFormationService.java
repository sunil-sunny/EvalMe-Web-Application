package com.group18.asdc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.group18.asdc.entities.Answer;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.SurveyMetaData;
import com.group18.asdc.util.IQueryVariableToArrayList;

public interface GroupFormationService {

	public HashMap formGroupsForSurvey(Course course, SurveyAnswersService surveyAnswersService,
			SurveyService surveyService, IQueryVariableToArrayList queryVariableToArraylist);

	public HashMap<String, HashMap> fetchGroupDetails(List<String> userIdList, SurveyMetaData surveyMetaData,
			ArrayList<Answer> answerList);

}
