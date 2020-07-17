package com.group18.asdc.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.RegisterService;

@SpringBootTest
public class RegisterServiceTest {

	private static final RegisterService registerServiceMock = TestConfig.getTestSingletonIntance()
			.getServiceTestAbstractFactory().getRegisterService();

	@Test
	public void registeruserTest() {

		JSONObject json = registerServiceMock.registeruser(
				TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getIUserRegistartionDetailsTest());
		assertNotNull(json);
	}

	@Test
	public void registerStudentsTest() {
		boolean isRegistered = registerServiceMock.registerStudents(new ArrayList<User>());
		assertTrue(isRegistered);
	}
}
