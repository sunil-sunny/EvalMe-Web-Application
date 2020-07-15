package com.group18.asdc.entities;

import java.sql.Timestamp;

public interface IQuestionMetaData {

	public String getSample();

	public void setSample(String sample);

	public int getQuestionId();

	public BasicQuestionData getBasicQuestionData();

	public void setBasicQuestionData(BasicQuestionData basicQuestionData);

	public void setQuestionId(int questionId);

	public Timestamp getCreationDateTime();

	public void setCreationDateTime(Timestamp creationDateTime);
}
