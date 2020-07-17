package com.group18.asdc.passwordpolicy;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.group18.asdc.TestConfig;
import com.group18.asdc.entities.PasswordHistory;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.security.IPasswordEncryption;
import com.group18.asdc.service.PasswordHistoryService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PasswordPolicyManagerTest {

    @Mock
    PasswordHistoryService passwordHistoryService;
    
    @Mock
	IPasswordEncryption passwordEncryption;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    private ArrayList returnPasswordHistoryList() {
        ArrayList resulList = new ArrayList<>();
        PasswordHistory passwordHistory = new PasswordHistory();
        passwordHistory.setPassword("encrypted");
        resulList.add(passwordHistory);
        passwordHistory = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getPasswordHistoryTest();
        passwordHistory.setPassword("encrypted");
        resulList.add(passwordHistory);
        passwordHistory = TestConfig.getTestSingletonIntance().getModelTestAbstractFactory().getPasswordHistoryTest();
        passwordHistory.setPassword("encrypted");
        resulList.add(passwordHistory);
        return resulList;
    }

    @Test
    public void validateHistoricalPasswordTest() throws PasswordPolicyException {
        when(passwordHistoryService.getPasswordHistory(isA(String.class), isA(Integer.class)))
                .thenReturn(returnPasswordHistoryList());
        when(passwordEncryption.matches(isA(String.class), isA(String.class))).thenReturn(Boolean.FALSE);
        HistoryConstraintPolicy policyObj = new HistoryConstraintPolicy("5", passwordHistoryService,
                passwordEncryption);
        policyObj.validate("B00838575", "karthi@76");
        verify(passwordHistoryService, times(1)).getPasswordHistory("B00838575", 5);
        verify(passwordEncryption, times(3)).matches("karthi@76", "encrypted");
    }

    @Test(expected = PasswordPolicyException.class)
    public void validateHistoricalPasswordErrorTest() throws PasswordPolicyException {
        when(passwordHistoryService.getPasswordHistory(isA(String.class), isA(Integer.class)))
                .thenReturn(returnPasswordHistoryList());
        when(passwordEncryption.matches(isA(String.class), isA(String.class))).thenReturn(Boolean.TRUE);
        HistoryConstraintPolicy policyObj = new HistoryConstraintPolicy("5", passwordHistoryService,
                passwordEncryption);
        policyObj.validate("B00838575", "karthi@76");
    }

}