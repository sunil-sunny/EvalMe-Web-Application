package com.group18.asdc.dao;

import java.util.List;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.SurveyQuestion;

public interface SurveyDao {

	public List<SurveyQuestion> getAllSavedSurveyQuestions(Course course);

	public boolean saveSurveyQuestions(List<SurveyQuestion> allSurveyQuestions);
}
