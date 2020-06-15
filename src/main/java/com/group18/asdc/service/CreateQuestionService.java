package com.group18.asdc.service;

import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.MultipleChoiceChooseMore;
import com.group18.asdc.entities.MultipleChoiceChooseOne;

public interface CreateQuestionService {

	public boolean createNumericQuestion(BasicQuestionData theBasicQuestionData);

	public boolean createFreeTextQuestion(BasicQuestionData theBasicQuestionData);

	public boolean createMultipleChoiceChooseMoreQuestion(MultipleChoiceChooseMore theMultipleChoiceChooseMore);

	public boolean createMultipleChoiceChooseOneQuestion(MultipleChoiceChooseOne theMultipleChoiceChooseOne);

}
