package com.group18.asdc.entities;

import java.sql.Timestamp;

public class QuestionMetaData implements IQuestionMetaData {

	private int questionId;
	private Timestamp creationDateTime;
	private BasicQuestionData basicQuestionData;
	private String sample;

	@Override
	public String getSample() {
		return sample;
	}

	@Override
	public void setSample(String sample) {
		this.sample = sample;
	}

	@Override
	public int getQuestionId() {
		return questionId;
	}

	@Override
	public BasicQuestionData getBasicQuestionData() {
		return basicQuestionData;
	}

	@Override
	public void setBasicQuestionData(BasicQuestionData basicQuestionData) {
		this.basicQuestionData = basicQuestionData;
	}

	@Override
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	@Override
	public Timestamp getCreationDateTime() {
		return creationDateTime;
	}

	@Override
	public void setCreationDateTime(Timestamp creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	@Override
	public String toString() {
		return "QuestionMetaData [questionId=" + questionId + ", creationDateTime=" + creationDateTime
				+ ", basicQuestionData=" + basicQuestionData + ", sample=" + sample + "]";
	}
}
