package com.group18.asdc.entities;

public class Answer {

    private String answer;
    private Integer surveyQuestionId;
    private String bannerId;
    private Integer answerId;

    public Answer() {
        answer = null;
        surveyQuestionId = null;
        bannerId = null;
        answerId = null;
    }

    public Answer(String answer, String bannerId, Integer surveyQuestionId, Integer answerId) {
        this.answer = answer;
        this.bannerId = bannerId;
        this.surveyQuestionId = surveyQuestionId;
        this.answerId = answerId;
    }

    public Answer(String answer, String bannerId, Integer surveyQuestionId) {
        this(answer, bannerId, surveyQuestionId, null);
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public void setSurveyQuestionId(Integer surveyQuestionId) {
        this.surveyQuestionId = surveyQuestionId;
    }

    public String getAnswers() {
        return this.answer;
    }

    public String getBannerId() {
        return this.bannerId;
    }

    public Integer getSurveyQuestionId() {
        return this.surveyQuestionId;
    }

	public String getAnswer() {
		return answer;
	}

	public Integer getAnswerId() {
		return answerId;
	}
}