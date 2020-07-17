package com.group18.asdc.entities.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.group18.asdc.TestConfig;
import com.group18.asdc.entities.PasswordHistory;

@SpringBootTest
public class PasswordHistoryTest {

	@Test
	public void constructorTest() {
		PasswordHistory passwordHistory = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getPasswordHistoryTest();
		assertEquals("", passwordHistory.getBannerID());
		assertEquals("", passwordHistory.getPassword());
		assertEquals(-1, passwordHistory.getID());
		assertNull(passwordHistory.getDate());
	}

	@Test
	public void getterSetterTest() {
		PasswordHistory passwordHistory = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getPasswordHistoryTest();
		passwordHistory.setID(1);
		passwordHistory.setBannerID("B00838575");
		passwordHistory.setDate(123456789l);
		passwordHistory.setPassword("password");
		assertEquals("B00838575", passwordHistory.getBannerID());
		assertEquals(1, passwordHistory.getID());
		assertEquals("password", passwordHistory.getPassword());
		assertEquals(123456789l, passwordHistory.getDate());
	}
}