package com.group18.asdc.database;

public enum QuestionManagerDataBaseQueries {

	GET_QUESTION_BY_ID(
			"SELECT a.questionid,a.qtitle,a.question,a.datecreated,b.questiontypename FROM questions as a inner join questiontype as b on a.questiontypeid=b.questiontypeid where a.questionid=?;"),
	IS_QUESTION_TITLE("select * from questiontitle where qtitle=?;"),
	CREATE_QUESTION_TITLE("insert into questiontitle (qtitle) values (?);"),
	GET_QUESTION_TYPE_ID("SELECT * FROM questiontype where questiontypename=?;"),
	CREATE_QUESTION("insert into questions (bannerid,questiontypeid,qtitle,question,datecreated)  values (?,?,?,?,?);"),
	CREATE_OPTIONS("insert into options (questionid,optiontext,optionlinenumber) values (?,?,?);"),
	GET_QUESTION_ID(
			"SELECT questionid FROM questions where bannerid=? and questiontypeid=? and qtitle=? and question=? order by datecreated DESC limit 1;"),
	GET_ALL_QUESTIONS(
			"SELECT a.questionid,a.qtitle,a.question,a.datecreated,b.questiontypename FROM questions as a inner join questiontype as b on a.questiontypeid=b.questiontypeid where a.bannerid=?;"),
	GET_ALL_QUESTIONS_SORTEDBY_DATE(
			"SELECT a.questionid,a.qtitle,a.question,a.datecreated,b.questiontypename FROM questions as a inner join questiontype as b on a.questiontypeid=b.questiontypeid where a.bannerid=? order by a.datecreated DESC;"),
	GET_ALL_QUESTIONS_SORTEDBY_TITLE(
			"SELECT a.questionid,a.qtitle,a.question,a.datecreated,b.questiontypename FROM questions as a inner join questiontype as b on a.questiontypeid=b.questiontypeid where a.bannerid=? order by a.qtitle;"),

	DELETE_QUESTION("delete from questions where questionid=?;");

	private final String sqlQuery;
	
	private QuestionManagerDataBaseQueries(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	@Override
	public String toString() {
		return sqlQuery;
	}
}
