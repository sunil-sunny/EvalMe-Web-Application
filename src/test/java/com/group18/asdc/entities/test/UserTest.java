package com.group18.asdc.entities.test;

import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.passwordpolicy.BasePasswordPolicyManagerMock;
import com.group18.asdc.passwordpolicy.CharsNotAllowedPolicy;
import com.group18.asdc.passwordpolicy.MaxlengthPolicy;
import com.group18.asdc.passwordpolicy.MinLowercasePolicy;
import com.group18.asdc.passwordpolicy.MinSpecialcharPolicy;
import com.group18.asdc.passwordpolicy.MinUppercasePolicy;
import com.group18.asdc.passwordpolicy.MinlengthPolicy;
import com.group18.asdc.security.IPasswordEncryption;
import com.group18.asdc.service.PasswordHistoryService;
import com.group18.asdc.util.CustomStringUtils;
import com.group18.asdc.util.ICustomStringUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

	ICustomStringUtils customStringUtils = new CustomStringUtils();

	@Mock
	PasswordHistoryService passwordHistoryService;

	@Mock
	IPasswordEncryption passwordEncryption;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = PasswordPolicyException.class)
	public void validateMinLengthPasswordErrorTest() throws PasswordPolicyException {
		BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new MinlengthPolicy("10"));
		obj.validatePassword("karthikk");
	}

	@Test
	public void validateMinLengthPasswordTest() throws PasswordPolicyException {
		BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new MinlengthPolicy("8"));
		obj.validatePassword("karthikk");
	}

	@Test(expected = PasswordPolicyException.class)
	public void validateMaxLengthPasswordErrorTest() throws PasswordPolicyException {
		BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new MaxlengthPolicy("15"));
		obj.validatePassword("karthikkkarthikk");
	}

	@Test
	public void validateMaxLengthPasswordTest() throws PasswordPolicyException {
		BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(new MaxlengthPolicy("12"));
		obj.validatePassword("karthikk");
	}

	@Test(expected = PasswordPolicyException.class)
	public void validateMinLengthUpperPasswordErrorTest() throws PasswordPolicyException {
		BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(
				new MinUppercasePolicy("5", customStringUtils));
		obj.validatePassword("KArthikk");
	}

	@Test
	public void validateMinLengthUpperPasswordTest() throws PasswordPolicyException {
		BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(
				new MinUppercasePolicy("5", customStringUtils));
		obj.validatePassword("KARTHIKK");
	}

	@Test(expected = PasswordPolicyException.class)
	public void validateMinLengthlowerPasswordErrorTest() throws PasswordPolicyException {
		BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(
				new MinLowercasePolicy("8", customStringUtils));
		obj.validatePassword("KARTHIKK");
	}

	@Test
	public void validateMinLengthlowerPasswordTest() throws PasswordPolicyException {
		BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(
				new MinLowercasePolicy("8", customStringUtils));
		obj.validatePassword("karthikk");
	}

	@Test(expected = PasswordPolicyException.class)
	public void validateMinLengthSpecialCharsPasswordErrorTest() throws PasswordPolicyException {
		BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(
				new MinSpecialcharPolicy("3", customStringUtils));
		obj.validatePassword("k@#arthikk");
	}

	@Test
	public void validateMinLengthSpecialCharsPasswordTest() throws PasswordPolicyException {
		BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(
				new MinSpecialcharPolicy("3", customStringUtils));
		obj.validatePassword("k@#$arthikk");
	}

	@Test(expected = PasswordPolicyException.class)
	public void validateCharsNotAllowedPasswordTest() throws PasswordPolicyException {
		BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(
				new CharsNotAllowedPolicy("@!#", customStringUtils));
		obj.validatePassword("kar@thikk");
	}

	@Test
	public void validateCharsNotAllowedPasswordErrorTest() throws PasswordPolicyException {
		BasePasswordPolicyManagerMock obj = new BasePasswordPolicyManagerMock(
				new CharsNotAllowedPolicy("@!#", customStringUtils));
		obj.validatePassword("kar*()thikk");
	}

}