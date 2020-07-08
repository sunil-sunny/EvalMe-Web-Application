package com.group18.asdc.util;

public enum SurveyDataBaseQueries {

	GET_ALL_SAVED_QUESTIONS(
			"select a.surveyid,a.courseid,b.surveyquestionid,b.questionid,c.logicname,b.logicvalue,a.groupsize,a.state from survey as a inner join survey_questions\r\n"
					+ "as b on a.surveyid=b.surveyid inner join groupingoptions as c on b.logicid=c.logicid where a.courseid=?;"),

	DELETE_ALL_SAVED_QUESTIONS("delete from survey_questions where surveyid=?;"),
	IS_SURVEY_EXISTS("SELECT * FROM CSCI5308_18_DEVINT.survey where courseid=?;"),
	CREATE_SURVEY("insert into survey(courseid,state) value (?,?);"),
	SAVE_SURVEY_QUESTION(
			"insert into survey_questions (surveyid,questionid,datequestionadded,logicid,logicvalue) values \r\n"
					+ "(?,?,?,?,?);"),

	UPDARE_GROUP_SIZE("update survey set groupsize=? where surveyid=?;");
	private final String sqlQuery;

	private SurveyDataBaseQueries(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	@Override
	public String toString() {
		return sqlQuery;
	}
}
