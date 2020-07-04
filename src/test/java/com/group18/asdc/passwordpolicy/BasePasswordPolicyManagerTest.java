package com.group18.asdc.passwordpolicy;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.HashMap;

import com.group18.asdc.database.IPasswordPolicyDB;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.util.ICustomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasePasswordPolicyManagerTest {

	@Mock
	IPasswordPolicyDB passwordPolicyDB;

	@Mock
	ICustomStringUtils customStringUtils;

	@InjectMocks
	BasePasswordPolicyManager passwordPolicyManager = new BasePasswordPolicyManager(passwordPolicyDB);

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	private ArrayList getEnabledList() {
		ArrayList resultList = new ArrayList<>();
		String[] policyArray = { "MinLength", "MaxLength", "MinLowercase", "MinUppercase", "MinSpecialCharacter",
		"CharactersNotAllowed" };
		HashMap valueMap = new HashMap<>();
		valueMap.put("MinLength", "6");
		valueMap.put("MaxLength", "15");
		valueMap.put("MinLowercase", "1");
		valueMap.put("MinUppercase", "1");
		valueMap.put("MinSpecialCharacter", "1");
		valueMap.put("CharactersNotAllowed", "@$");
		for (String eachValue : policyArray) {
			HashMap map = new HashMap<>();
			map.put("POLICY_NAME", eachValue);
			map.put("POLICY_VALUE", valueMap.get(eachValue));
			resultList.add(map);
		}
		return resultList;
	}

	@Test
	public void testRespectivePolicyMethodCalls() throws PasswordPolicyException {
		when(passwordPolicyDB.loadBasePoliciesFromDB()).thenReturn(getEnabledList());
		when(customStringUtils.containsAnyCharacter(isA(String.class), isA(String.class))).thenReturn(Boolean.FALSE);
		when(customStringUtils.getLowerCaseCharactersCount(isA(String.class))).thenReturn(2);
		when(customStringUtils.getUpperCaseCharactersCount(isA(String.class))).thenReturn(2);
		when(customStringUtils.getSpecialCharactersCount(isA(String.class))).thenReturn(2);
		passwordPolicyManager.validatePassword("Karthikk&");
	}

	private ArrayList getDisabledList() {
		ArrayList resultList = new ArrayList<>();
		return resultList;
	}

	@Test
	public void testDisabledPolicies() throws PasswordPolicyException {
		when(passwordPolicyDB.loadBasePoliciesFromDB()).thenReturn(getDisabledList());
		when(customStringUtils.containsAnyCharacter(isA(String.class), isA(String.class))).thenReturn(Boolean.FALSE);
		when(customStringUtils.getLowerCaseCharactersCount(isA(String.class))).thenReturn(2);
		when(customStringUtils.getUpperCaseCharactersCount(isA(String.class))).thenReturn(2);
		when(customStringUtils.getSpecialCharactersCount(isA(String.class))).thenReturn(2);
		passwordPolicyManager.validatePassword("karthikk");
		verify(passwordPolicyDB, times(1)).loadBasePoliciesFromDB();
		verify(passwordPolicyDB, times(0)).loadPoliciesFromDB();
		verify(customStringUtils, times(0)).getLowerCaseCharactersCount("karthikk");
		verify(customStringUtils, times(0)).containsAnyCharacter("karthikk", "@$");
		verify(customStringUtils, times(0)).getUpperCaseCharactersCount("karthikk");
		verify(customStringUtils, times(0)).getSpecialCharactersCount("karthikk");
	}
}