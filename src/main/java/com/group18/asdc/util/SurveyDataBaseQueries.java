package com.group18.asdc.util;

public class SurveyDataBaseQueries {

	private static final String getAllSavedQuestionsQuery = "select a.surveyid,a.courseid,b.surveyquestionid,b.questionid,c.name,b.Xvalue from survey as a \r\n"
			+ "inner join survey_questions as b on a.surveyid=b.surveyid\r\n"
			+ "inner join groupingoptions as c on b.logicid=c.logicid where a.courseid=?;";

	public static String getGetallsavedquestionsQuery() {
		return getAllSavedQuestionsQuery;
	}
}
