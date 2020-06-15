package com.group18.asdc.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SQLQueriesTest {

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkEnumValues()
    {
        
        assertEquals("select * from user where bannerid = ? and password = ?", SQLQueries.USER_AUTH_BY_EMAIL_PASSWORD.toString());
    }
    
}