package com.group18.asdc.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomStringUtilsTest {

    @InjectMocks
    CustomStringUtils customStringUtils = new CustomStringUtils();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void LowerCaseCharacterstest() {

        assertEquals(2, customStringUtils.getLowerCaseCharactersCount("kaRTHIKK"));
        assertEquals(0, customStringUtils.getLowerCaseCharactersCount("KARTHIKK"));
        assertEquals(1, customStringUtils.getLowerCaseCharactersCount("k"));
        assertEquals(6, customStringUtils.getLowerCaseCharactersCount("karthi"));
    }

    @Test
    public void upperCaseCharactersCount() {

        assertEquals(6, customStringUtils.getUpperCaseCharactersCount("kaRTHIKK"));
        assertEquals(0, customStringUtils.getUpperCaseCharactersCount("karthikk"));
        assertEquals(1, customStringUtils.getUpperCaseCharactersCount("K"));
        assertEquals(6, customStringUtils.getUpperCaseCharactersCount("KARTHI"));
    }

    @Test
    public void specialCharactersCount() {

        assertEquals(2, customStringUtils.getSpecialCharactersCount("kaR@#THIKK"));
        assertEquals(0, customStringUtils.getSpecialCharactersCount("KARTHIKK"));
        assertEquals(1, customStringUtils.getSpecialCharactersCount("!"));
        assertEquals(6, customStringUtils.getSpecialCharactersCount("!@#$%^"));
    }

}