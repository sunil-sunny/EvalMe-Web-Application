package com.group18.asdc.dao.test;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.RegisterDao;
import com.group18.asdc.entities.UserRegistartionDetails;

@SpringBootTest
public class RegisterDaoTest {

	private static final RegisterDao registerDaoMock = TestConfig.getTestSingletonIntance().getDaoTestAbstractFactory()
			.getRegisterDaoTest();

	@Test
	public void registeruserTest() {

		boolean isRegistered = registerDaoMock.registeruser(new UserRegistartionDetails());
		assertTrue(isRegistered);
	}

	@Test
	public void checkUserWithEmailTest() {

		boolean isChecked = registerDaoMock.checkUserWithEmail("james@dal.ca");
		assertTrue(isChecked);

	}

	public void checkUserWithBannerId() {

		boolean isChecked = registerDaoMock.checkUserWithBannerId("B00832114");
		assertTrue(isChecked);
	}
}
