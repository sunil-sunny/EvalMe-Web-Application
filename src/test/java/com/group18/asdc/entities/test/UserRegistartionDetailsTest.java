package com.group18.asdc.entities.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.entities.UserRegistartionDetails;

@SpringBootTest
public class UserRegistartionDetailsTest {

	@Test
	public void getFirstname() {
		UserRegistartionDetails userDetails = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getUserRegistartionDetailsTest();
		userDetails.setFirstname("Luke");
		assertTrue(userDetails.getFirstname().equals("Luke"));
	}

	@Test
	public void setFirstname() {
		UserRegistartionDetails userDetails = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getIUserRegistartionDetailsTest();
		userDetails.setFirstname("Luke");
		assertTrue(userDetails.getFirstname().equals("Luke"));
	}

	@Test
	public void getLastname() {
		UserRegistartionDetails userDetails = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getIUserRegistartionDetailsTest();
		userDetails.setLastname("Skywalker");
		assertTrue(userDetails.getLastname().equals("Skywalker"));
	}

	@Test
	public void setLastname() {
		UserRegistartionDetails userDetails = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getIUserRegistartionDetailsTest();
		userDetails.setLastname("Skywalker");
		assertTrue(userDetails.getLastname().equals("Skywalker"));
	}

	@Test
	public void getBannerid() {
		UserRegistartionDetails userDetails = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getIUserRegistartionDetailsTest();
		userDetails.setBannerid("B00842470");
		assertTrue(userDetails.getBannerid().equals("B00842470"));
	}

	@Test
	public void setBannerid() {
		UserRegistartionDetails userDetails = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getIUserRegistartionDetailsTest();
		userDetails.setBannerid("B00842470");
		assertTrue(userDetails.getBannerid().equals("B00842470"));
	}

	@Test
	public void getEmailid() {
		UserRegistartionDetails userDetails = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getIUserRegistartionDetailsTest();
		userDetails.setEmailid("lukeskywalker@dal.ca");
		assertTrue(userDetails.getEmailid().equals("lukeskywalker@dal.ca"));
	}

	@Test
	public void setEmailid() {
		UserRegistartionDetails userDetails = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getIUserRegistartionDetailsTest();
		userDetails.setEmailid("lukeskywalker@dal.ca");
		assertTrue(userDetails.getEmailid().equals("lukeskywalker@dal.ca"));
	}

	@Test
	public void getPassword() {
		UserRegistartionDetails userDetails = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getIUserRegistartionDetailsTest();
		userDetails.setPassword("passwordLuke123");
		assertTrue(userDetails.getPassword().equals("passwordLuke123"));
	}

	@Test
	public void setPassword() {
		UserRegistartionDetails userDetails = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getIUserRegistartionDetailsTest();
		userDetails.setPassword("passwordLuke123");
		assertTrue(userDetails.getPassword().equals("passwordLuke123"));
	}

	@Test
	public void getConfirmpassword() {
		UserRegistartionDetails userDetails = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getIUserRegistartionDetailsTest();
		userDetails.setConfirmpassword("passwordLuke123");
		assertTrue(userDetails.getConfirmpassword().equals("passwordLuke123"));
	}

	@Test
	public void setConfirmpassword() {
		UserRegistartionDetails userDetails = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getIUserRegistartionDetailsTest();
		userDetails.setConfirmpassword("passwordLuke123");
		assertTrue(userDetails.getConfirmpassword().equals("passwordLuke123"));
	}
}
