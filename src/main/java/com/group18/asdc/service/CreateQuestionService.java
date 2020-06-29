package com.group18.asdc.service;

import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.MultipleChoiceQuestion;

public interface CreateQuestionService {

	public boolean createNumericOrTextQuestion(BasicQuestionData theBasicQuestionData);

	public boolean createMultipleQuestion(MultipleChoiceQuestion theMultipleChoiceChooseOne);

}
