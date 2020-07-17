package com.group18.asdc.entities.test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.Option;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MultipleChoiceQuestionTest {

	@Test
	public void getOptionList() {
		Option option = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getOptionTest();
		option.setStoredData(1);
		option.setDisplayText("Beginner");
		Option anotherOption = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getOptionTest();
		anotherOption.setStoredData(2);
		anotherOption.setDisplayText("Proficient");
		List<Option> optionList = new ArrayList<Option>();
		optionList.add(option);
		optionList.add(anotherOption);
		MultipleChoiceQuestion getMultipleChoiceQuestion = TestConfig.getTestSingletonIntance()
				.getModelTestAbstractFactory().getMultipleChoiceQuestionTest();
		getMultipleChoiceQuestion.setOptionList(optionList);
		assertTrue(getMultipleChoiceQuestion.getOptionList().equals(optionList));
	}

	@Test
	public void setOptionList() {
		Option option = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getOptionTest();
		option.setStoredData(1);
		option.setDisplayText("Beginner");
		Option anotherOption = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getOptionTest();
		anotherOption.setStoredData(2);
		anotherOption.setDisplayText("Proficient");
		List<Option> anotherOptionList = new ArrayList<Option>();
		anotherOptionList.add(option);
		anotherOptionList.add(anotherOption);
		MultipleChoiceQuestion setMultipleChoiceQuestion = TestConfig.getTestSingletonIntance()
				.getModelTestAbstractFactory().getMultipleChoiceQuestionTest();
		setMultipleChoiceQuestion.setOptionList(anotherOptionList);
		assertTrue(setMultipleChoiceQuestion.getOptionList().equals(anotherOptionList));
	}
}