package com.group18.asdc.util;

public class QuestionManagerDataBaseQueries {

	public static final String getQuestionById = "SELECT a.questionid,a.qtitle,a.question,a.datecreated,b.questiontypename FROM questions as a inner join\r\n"
			+ "questiontype as b on a.questiontypeid=b.questiontypeid where a.questionid=?;";

}
