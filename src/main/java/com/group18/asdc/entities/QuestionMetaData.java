package com.group18.asdc.entities;

import java.sql.Timestamp;

public class QuestionMetaData {

	private int questionId;
	private Timestamp creationDateTime;
	private BasicQuestionData basicQuestionData;
	private String sample;

	public String getSample() {
		return sample;
	}

	public void setSample(String sample) {
		this.sample = sample;
	}

	public int getQuestionId() {
		return questionId;
	}

	public BasicQuestionData getBasicQuestionData() {
		return basicQuestionData;
	}

	public void setBasicQuestionData(BasicQuestionData basicQuestionData) {
		this.basicQuestionData = basicQuestionData;
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

	@Override
	public String toString() {
		return "QuestionMetaData [questionId=" + questionId + ", creationDateTime=" + creationDateTime
				+ ", basicQuestionData=" + basicQuestionData + ", sample=" + sample + "]";
	}
}
