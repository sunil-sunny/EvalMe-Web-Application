package com.group18.asdc.entities;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends BasicQuestionData implements IMultipleChoiceQuestion {

	private List<Option> optionList = new ArrayList<Option>();

	@Override
	public List<Option> getOptionList() {
		return optionList;
	}

	@Override
	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}	
}
