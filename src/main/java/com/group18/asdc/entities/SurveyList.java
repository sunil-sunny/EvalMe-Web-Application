package com.group18.asdc.entities;

import java.util.List;

public class SurveyList implements ISurveyList {
	private List questionList;
	private List answerList;
	private List userList;

	public List getQuestionList() {
		return this.questionList;
	}

	public List getAnswerList() {
		return this.answerList;
	}

	public List getUserList() {
		return this.userList;
	}
}