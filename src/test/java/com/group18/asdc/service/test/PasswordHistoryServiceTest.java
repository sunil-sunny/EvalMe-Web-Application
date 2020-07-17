package com.group18.asdc.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.HashMap;

import com.group18.asdc.TestConfig;
import com.group18.asdc.dao.PasswordHistoryDao;
import com.group18.asdc.entities.PasswordHistory;
import com.group18.asdc.security.IPasswordEncryption;
import com.group18.asdc.service.PasswordHistoryService;
import com.group18.asdc.service.PasswordHistoryServiceImpl;
import com.group18.asdc.util.IQueryVariableToArrayList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PasswordHistoryServiceTest {

	@Mock
	IQueryVariableToArrayList queryVariableToArraylist;

	@InjectMocks
	PasswordHistoryService passwordHistoryService = new PasswordHistoryServiceImpl(queryVariableToArraylist);

	@Mock
	PasswordHistoryDao passwordHistoryDao;

	@Mock
	IPasswordEncryption passwordEncryption;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	private PasswordHistory getDefaultPasswordHistory() {
		PasswordHistory passwordHistory = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory()
				.getPasswordHistoryTest();
		passwordHistory.setBannerID("B00838575");
		passwordHistory.setDate(123456789l);
		passwordHistory.setPassword("password");
		return passwordHistory;
	}

	@Test
	public void insertPasswordHistoryTest() {
		ArrayList valuesList = new ArrayList();
		valuesList.add("B00838575");
		valuesList.add("encryptedPassword");
		valuesList.add(123456789l);
		when(passwordEncryption.encryptPassword("password")).thenReturn("encryptedPassword");
		when(queryVariableToArraylist.convertQueryVariablesToArrayList(isA(String.class), isA(String.class),
				isA(Long.class))).thenReturn(valuesList);
		when(passwordHistoryDao.insertPasswordHistory(isA(ArrayList.class))).thenReturn(1);
		PasswordHistory passwordHistory = getDefaultPasswordHistory();
		assertEquals(1, passwordHistoryService.insertPassword(passwordHistory, passwordEncryption));
		verify(passwordHistoryDao, times(1)).insertPasswordHistory(valuesList);
		verify(queryVariableToArraylist, times(1)).convertQueryVariablesToArrayList("B00838575", "encryptedPassword",
				123456789l);
	}

	private ArrayList getDefaultPasswordHistoryDao() {
		ArrayList resultList = new ArrayList<>();
		HashMap map = new HashMap<>();
		map.put("bannerid", "B00838575");
		map.put("password", "encryptedPassword");
		resultList.add(map);
		return resultList;
	}

	@Test
	public void getPasswordHistoryTest() {
		ArrayList valuesList = new ArrayList();
		valuesList.add("B00838575");
		valuesList.add(5);
		when(queryVariableToArraylist.convertQueryVariablesToArrayList(isA(String.class), isA(Integer.class)))
				.thenReturn(valuesList);
		when(passwordHistoryDao.getPasswordHistory(isA(ArrayList.class))).thenReturn(getDefaultPasswordHistoryDao());
		ArrayList resultList = passwordHistoryService.getPasswordHistory("B00838575", 5);
		verify(passwordHistoryDao, times(1)).getPasswordHistory(valuesList);
		verify(queryVariableToArraylist, times(1)).convertQueryVariablesToArrayList("B00838575", 5);
		PasswordHistory resultHistory = (PasswordHistory) resultList.get(0);
		assertEquals("encryptedPassword", resultHistory.getPassword());
		assertEquals("B00838575", resultHistory.getBannerID());
	}
}
