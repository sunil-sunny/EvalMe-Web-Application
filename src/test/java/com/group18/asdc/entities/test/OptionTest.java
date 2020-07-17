package com.group18.asdc.entities.test;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.group18.asdc.TestConfig;
import com.group18.asdc.entities.Option;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class OptionTest{

	@Test
	public void getDisplayText() {
		Option option = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getOptionTest();
		option.setDisplayText("Beginner");
		assertTrue(option.getDisplayText().equals("Beginner"));
	}

	@Test
	public void setDisplayText() {
		Option option = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getOptionTest();
		option.setDisplayText("Beginner");
		assertTrue(option.getDisplayText().equals("Beginner"));
	}

	@Test
	public void getStoredData() {
		Option option = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getOptionTest();
		option.setStoredData(1);
		assertTrue(option.getStoredData()==1);
	}

	@Test
	public void setStoredData() {
		Option option = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getOptionTest();
		option.setStoredData(1);
		assertTrue(option.getStoredData()==1);
	}
}
