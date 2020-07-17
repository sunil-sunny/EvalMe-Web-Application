package com.group18.asdc.entities.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.TestConfig;
import com.group18.asdc.handlingformsubmission.ResetPassword;

@SpringBootTest
public class ResetPaswordTest {

	@Test
	public void getgeneratedPassword() {
		ResetPassword resetPassword = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getResetPasswordTest();
		resetPassword.setgeneratedPassword("hd94g#69dn2r");
		assertTrue(resetPassword.getgeneratedPassword().equals("hd94g#69dn2r"));
	}

	@Test
	public void getnewPassword() {
		ResetPassword resetPassword = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getResetPasswordTest();
		resetPassword.setgeneratedPassword("hd94g#69dn2r");
		assertTrue(resetPassword.getgeneratedPassword().equals("hd94g#69dn2r"));
	}

	@Test
	public void getconfirmNewPassword() {
		ResetPassword resetPassword = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getResetPasswordTest();
		resetPassword.setconfirmNewPassword("hd94g#69dn2r");
		assertTrue(resetPassword.getconfirmNewPassword().equals("hd94g#69dn2r"));
	}

	@Test
	public void setconfirmNewPassword() {
		ResetPassword resetPassword = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getResetPasswordTest();
		resetPassword.setconfirmNewPassword("hd94g#69dn2r");
		assertTrue(resetPassword.getconfirmNewPassword().equals("hd94g#69dn2r"));
	}

	@Test
	public void getbannerId() {
		ResetPassword resetPassword = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getResetPasswordTest();
		resetPassword.setbannerId("B00842470");
		assertTrue(resetPassword.getbannerId().equals("B00842470"));
	}

	@Test
	public void setgeneratedPassword() {
		ResetPassword resetPassword = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getResetPasswordTest();
		resetPassword.setgeneratedPassword("hd94g#69dn2r");
		assertTrue(resetPassword.getgeneratedPassword().equals("hd94g#69dn2r"));
	}

	@Test
	public void setnewPassword() {
		ResetPassword resetPassword = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getResetPasswordTest();
		resetPassword.setgeneratedPassword("hd94g#69dn2r");
		assertTrue(resetPassword.getgeneratedPassword().equals("hd94g#69dn2r"));
	}

	@Test
	public void setbannerId() {
		ResetPassword resetPassword = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getResetPasswordTest();
		resetPassword.setbannerId("B00842470");
		assertTrue(resetPassword.getbannerId().equals("B00842470"));
	}

}
