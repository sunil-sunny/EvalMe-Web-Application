package com.group18.asdc.entities;

import java.util.List;

public interface IMultipleChoiceQuestion {

	public List<Option> getOptionList();

	public void setOptionList(List<Option> optionList);
}
