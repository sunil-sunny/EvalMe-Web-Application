package com.group18.asdc.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.InstanceOf;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommonUtilTest {

    @InjectMocks
    CommonUtil commonUtil;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void convertQueryToArrayListTest()
    {
        String email = "email", password = "password" , query = "query";
        //
        assertEquals( ArrayList.class ,commonUtil.getInstance().convertQueryVariablesToArrayList(email,password,query).getClass());
        assertEquals(3,commonUtil.getInstance().convertQueryVariablesToArrayList(email,password,query).size());
        assertEquals(2,commonUtil.getInstance().convertQueryVariablesToArrayList(email,password).size());
        assertEquals(1,commonUtil.getInstance().convertQueryVariablesToArrayList(email).size());
    }    

    @Test
    public void generateResetPasswordTest()
    {
        assertEquals( String.class ,commonUtil.getInstance().generateResetPassword().getClass());
    }

    @Test
    public void getInstanceTest()
    {
        assertNotNull(CommonUtil.getInstance());
        assertEquals(CommonUtil.class, CommonUtil.getInstance().getClass());
    }

}