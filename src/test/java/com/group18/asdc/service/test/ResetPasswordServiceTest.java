package com.group18.asdc.service.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group18.asdc.entities.PasswordHistory;
import com.group18.asdc.entities.User;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.passwordpolicy.BasePasswordPolicyFactory;
import com.group18.asdc.passwordpolicy.PasswordPolicyFactory;
import com.group18.asdc.security.IPasswordEncryption;
import com.group18.asdc.service.PasswordHistoryService;
import com.group18.asdc.service.ResetPasswordService;
import com.group18.asdc.service.ResetPasswordServiceImpl;
import com.group18.asdc.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ResetPasswordServiceTest {

	@Mock
	UserService userService;

	@Mock
	PasswordHistoryService passwordHistoryService;

	@Mock
	BasePasswordPolicyFactory basePasswordPolicyManager;

	@Mock
	PasswordPolicyFactory passwordPolicyManager;

	@Mock
	IPasswordEncryption passwordEncryption;

	@InjectMocks
	ResetPasswordService resetPasswordService = new ResetPasswordServiceImpl();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	private void getDefaultUserObj(User userObj) {
		userObj.setBannerId("B00838575");
		userObj.setEmail("kr630601@dal.ca");
		userObj.setFirstName("Karthikk");
		userObj.setLastName("Tamil");
		userObj.setPassword("karthikk");
	}

	@Test
	public void resetPasswordTest() throws PasswordPolicyException {
		String bannerId = "B00838575";
		String password = "password";

		doAnswer(invocation -> {
			String arg0 = invocation.getArgument(0);
			User arg1 = invocation.getArgument(1);
			getDefaultUserObj(arg1);
			assertEquals("B00838575", arg0);
			return null;
		}).when(userService).loadUserWithBannerId(isA(String.class), isA(User.class));

		doAnswer(invocation -> {
			String arg0 = invocation.getArgument(0);
			assertEquals("password", arg0);
			return null;
		}).when(basePasswordPolicyManager).validatePassword(isA(String.class));

		doAnswer(invocation -> {
			String arg0 = invocation.getArgument(0);
			assertEquals("B00838575", arg0);
			String arg1 = invocation.getArgument(1);
			assertEquals("password", arg1);
			return null;
		}).when(passwordPolicyManager).validatePassword(isA(String.class), isA(String.class));

		when(userService.updatePassword(isA(User.class), isA(IPasswordEncryption.class))).thenReturn(Boolean.TRUE);

		when(passwordHistoryService.insertPassword(isA(PasswordHistory.class), isA(IPasswordEncryption.class)))
				.thenReturn(Boolean.TRUE);

		resetPasswordService.resetPassword(userService, bannerId, password, passwordHistoryService,
				basePasswordPolicyManager, passwordPolicyManager, passwordEncryption);

		verify(userService, times(1)).loadUserWithBannerId(isA(String.class), isA(User.class));
		verify(basePasswordPolicyManager, times(1)).validatePassword("password");
		verify(passwordPolicyManager, times(1)).validatePassword("B00838575", "password");
		verify(userService, times(1)).updatePassword(isA(User.class), isA(IPasswordEncryption.class));
		verify(passwordHistoryService, times(1)).insertPassword(isA(PasswordHistory.class),
				isA(IPasswordEncryption.class));

	}

	@Test
	public void resetPasswordPasswordErrorTest() throws PasswordPolicyException {
		String bannerId = "B00838575";
		String password = "password";

		doAnswer(invocation -> {
			String arg0 = invocation.getArgument(0);
			User arg1 = invocation.getArgument(1);
			getDefaultUserObj(arg1);
			assertEquals("B00838575", arg0);
			return null;
		}).when(userService).loadUserWithBannerId(isA(String.class), isA(User.class));

		doThrow(new PasswordPolicyException("Does not match")).when(basePasswordPolicyManager)
				.validatePassword(isA(String.class));

		doAnswer(invocation -> {
			String arg0 = invocation.getArgument(0);
			assertEquals("B00838575", arg0);
			String arg1 = invocation.getArgument(1);
			assertEquals("password", arg1);
			return null;
		}).when(passwordPolicyManager).validatePassword(isA(String.class), isA(String.class));

		when(userService.updatePassword(isA(User.class), isA(IPasswordEncryption.class))).thenReturn(Boolean.TRUE);

		when(passwordHistoryService.insertPassword(isA(PasswordHistory.class), isA(IPasswordEncryption.class)))
				.thenReturn(Boolean.TRUE);

		resetPasswordService.resetPassword(userService, bannerId, password, passwordHistoryService,
				basePasswordPolicyManager, passwordPolicyManager, passwordEncryption);

		verify(userService, times(1)).loadUserWithBannerId(isA(String.class), isA(User.class));
		verify(basePasswordPolicyManager, times(1)).validatePassword("password");
		verify(passwordPolicyManager, times(0)).validatePassword("B00838575", "password");
		verify(userService, times(0)).updatePassword(isA(User.class), isA(IPasswordEncryption.class));
		verify(passwordHistoryService, times(0)).insertPassword(isA(PasswordHistory.class),
				isA(IPasswordEncryption.class));
	}


	@Test
	public void resetPasswordUpdatePasswordErrorTest() throws PasswordPolicyException {
		String bannerId = "B00838575";
		String password = "password";

		doAnswer(invocation -> {
			String arg0 = invocation.getArgument(0);
			User arg1 = invocation.getArgument(1);
			getDefaultUserObj(arg1);
			assertEquals("B00838575", arg0);
			return null;
		}).when(userService).loadUserWithBannerId(isA(String.class), isA(User.class));

		doAnswer(invocation -> {
			String arg0 = invocation.getArgument(0);
			assertEquals("password", arg0);
			return null;
		}).when(basePasswordPolicyManager).validatePassword(isA(String.class));

		doAnswer(invocation -> {
			String arg0 = invocation.getArgument(0);
			assertEquals("B00838575", arg0);
			String arg1 = invocation.getArgument(1);
			assertEquals("password", arg1);
			return null;
		}).when(passwordPolicyManager).validatePassword(isA(String.class), isA(String.class));

		when(userService.updatePassword(isA(User.class), isA(IPasswordEncryption.class))).thenReturn(Boolean.FALSE);

		when(passwordHistoryService.insertPassword(isA(PasswordHistory.class), isA(IPasswordEncryption.class)))
				.thenReturn(Boolean.TRUE);

		resetPasswordService.resetPassword(userService, bannerId, password, passwordHistoryService,
				basePasswordPolicyManager, passwordPolicyManager, passwordEncryption);

		verify(userService, times(1)).loadUserWithBannerId(isA(String.class), isA(User.class));
		verify(basePasswordPolicyManager, times(1)).validatePassword("password");
		verify(passwordPolicyManager, times(1)).validatePassword("B00838575", "password");
		verify(userService, times(1)).updatePassword(isA(User.class), isA(IPasswordEncryption.class));
		verify(passwordHistoryService, times(0)).insertPassword(isA(PasswordHistory.class),
				isA(IPasswordEncryption.class));
	}

}