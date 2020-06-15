package com.group18.asdc.entities;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceChooseOne extends BasicQuestionData{

	private List<Option> optionList=new ArrayList<Option>();

	public List<Option> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}

}
