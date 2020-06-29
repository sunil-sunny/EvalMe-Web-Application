package com.group18.asdc.entities;

import java.sql.Timestamp;

public class QuestionMetaData extends BasicQuestionData {

	private int questionId;
	private Timestamp creationDateTime;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public Timestamp getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Timestamp creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
}
