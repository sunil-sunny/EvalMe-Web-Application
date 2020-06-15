package com.group18.asdc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;

import com.group18.asdc.dao.UserDao;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.UserService;
import com.group18.asdc.service.UserServiceImpl;
import com.group18.asdc.util.CommonUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    UserService userService = new UserServiceImpl();

    @Mock
    UserDao userDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    private User getDefaultUserObj() {

        User userObj = new User();
        //
        userObj.setBannerId("B00838575");
        userObj.setEmail("kr630601@dal.ca");
        userObj.setFirstName("Karthikk");
        userObj.setLastName("Tamil");
        userObj.setPassword("karthikk");

        return userObj;

    }

    private void getDefaultUserObj(User userObj) {

        userObj.setBannerId("B00838575");
        userObj.setEmail("kr630601@dal.ca");
        userObj.setFirstName("Karthikk");
        userObj.setLastName("Tamil");
        userObj.setPassword("karthikk");
    }

    @Test
    public void loadUserWithBannerIdTest() {
        User userObj = new User();
        //

        doAnswer(invocation -> {
            ArrayList arg0 = invocation.getArgument(0);
            User arg1 = invocation.getArgument(1);

            getDefaultUserObj(arg1);
            assertEquals("B00838575", arg0.get(0));
            return null;
        }).when(userDao).loadUserWithBannerId(isA(ArrayList.class), isA(User.class));

        String bannerId = "B00838575";
        userService.loadUserWithBannerId(bannerId, userObj);
        //
        assertEquals("B00838575", userObj.getBannerId());
        assertEquals("kr630601@dal.ca", userObj.getEmail());
        assertEquals("Karthikk", userObj.getFirstName());
        assertEquals("Tamil", userObj.getLastName());
        ArrayList checkList = new ArrayList<>();
        checkList.add(bannerId);

        verify(userDao, times(1)).loadUserWithBannerId(checkList, userObj);

        ;
    }

    @Test
    public void loadUserWithBannerIdUnavailableTest() {
        User userObj = new User();
        //
        doAnswer(invocation -> {
            ArrayList arg0 = invocation.getArgument(0);
            User arg1 = invocation.getArgument(1);
            assertEquals("B00838575", arg0.get(0));
            return null;
        }).when(userDao).loadUserWithBannerId(isA(ArrayList.class), isA(User.class));

        String bannerId = "B00838575";
        userService.loadUserWithBannerId(bannerId, userObj);
        //
        assertNull(userObj.getEmail());
        assertNull(userObj.getBannerId());
        ArrayList checkList = new ArrayList<>();
        checkList.add(bannerId);
        verify(userDao, times(1)).loadUserWithBannerId(checkList, userObj);
        ;
    }

    @Test
    public void checkUpdatePassword() {
        when(userDao.updatePassword(isA(ArrayList.class), isA(ArrayList.class))).thenReturn(Boolean.TRUE);

        User userObj = getDefaultUserObj();
        //
        assertEquals(Boolean.TRUE, userService.updatePassword(userObj));
        //
        ArrayList<Object> criteriaList = new ArrayList<Object>();
        criteriaList.add("B00838575");
        ArrayList<Object> valuesList = new ArrayList<Object>();
        valuesList.add("karthikk");
        //
        verify(userDao, times(1)).updatePassword(criteriaList, valuesList);
    }

}