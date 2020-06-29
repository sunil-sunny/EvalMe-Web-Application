package com.group18.asdc.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QueryVariableToArrayListTest {

    @InjectMocks
    QueryVariableToArraylist queryVariableToArrayList;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testVariableToArrayList() {
        String email = "email", password = "password", query = "query";

        assertEquals(ArrayList.class,
                queryVariableToArrayList.convertQueryVariablesToArrayList(email, password, query).getClass());
        assertEquals(3, queryVariableToArrayList.convertQueryVariablesToArrayList(email, password, query).size());
        assertEquals(2, queryVariableToArrayList.convertQueryVariablesToArrayList(email, password).size());
        assertEquals(1, queryVariableToArrayList.convertQueryVariablesToArrayList(email).size());

    }
}