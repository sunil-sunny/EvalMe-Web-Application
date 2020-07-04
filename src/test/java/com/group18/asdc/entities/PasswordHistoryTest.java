package com.group18.asdc.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PasswordHistoryTest {

	@Test
	public void constructorTest() {
		PasswordHistory passwordHistory = new PasswordHistory();
		assertEquals("", passwordHistory.getBannerID());
		assertEquals("", passwordHistory.getPassword());
		assertEquals(-1, passwordHistory.getID());
		assertNull(passwordHistory.getDate());
	}

	private PasswordHistory getDefaultPasswordHistory() {
		PasswordHistory passwordHistory = new PasswordHistory();
		passwordHistory.setID(1);
		passwordHistory.setBannerID("B00838575");
		passwordHistory.setDate(123456789l);
		passwordHistory.setPassword("password");
		return passwordHistory;
	}

	@Test
	public void getterSetterTest() {
		PasswordHistory passwordHistory = getDefaultPasswordHistory();
		assertEquals("B00838575", passwordHistory.getBannerID());
		assertEquals(1, passwordHistory.getID());
		assertEquals("password", passwordHistory.getPassword());
		assertEquals(123456789l, passwordHistory.getDate());
	}
}