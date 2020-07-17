package com.group18.asdc.database;

public enum SurveyDataBaseQueries {

	GET_ALL_SAVED_QUESTIONS(
			"select a.surveyid,a.courseid,b.surveyquestionid,b.questionid,c.logicname,b.logicvalue,a.groupsize,a.state,b.priority from survey as a inner join survey_questions as b on a.surveyid=b.surveyid inner join groupingoptions as c on b.logicid=c.logicid where a.courseid=?;"),

	DELETE_ALL_SAVED_QUESTIONS("delete from survey_questions where surveyid=?;"),
	IS_SURVEY_EXISTS("SELECT * FROM survey where courseid=?;"),
	CREATE_SURVEY("insert into survey(courseid,state) value (?,?);"),
	SAVE_SURVEY_QUESTION(
			"insert into survey_questions (surveyid,questionid,datequestionadded,logicid,logicvalue,priority) values (?,?,?,?,?,?);"),
	UPDATE_GROUP_SIZE("update survey set groupsize=? where surveyid=?;"),
	IS_SURVEY_PUBLISHED("select state from survey where courseid=?;"),
	PUBLICH_SURVEY("update survey set state=? where surveyid=?;"),
	GET_SURVEYQUESTION_OPTIONS("select s.surveyquestionid, o.questionid, o.optiontext, o.optionlinenumber from survey_questions s, options o where s.questionid = o.questionid and s.surveyquestionid=? order by o.optionlinenumber;"),
	GET_SURVEYQUESTION_DATA("select s.surveyquestionid, q.questionid, q.qtitle, q.question, q.datecreated, qt.questiontypename from survey_questions s, questions q, questiontype qt where s.questionid = q.questionid and q.questiontypeid = qt.questiontypeid and s.surveyquestionid=?;"),
	GET_SURVEY_ANSWERS_DATA("select answer, answers.surveyquestionid, bannerid from survey_questions inner join answers on survey_questions.surveyquestionid = answers.surveyquestionid where surveyid = ?");
	
	private final String sqlQuery;

	private SurveyDataBaseQueries(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	@Override
	public String toString() {
		return sqlQuery;
	}
}
