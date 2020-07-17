package com.group18.asdc.groupformation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GroupFormationTest {

    private ArrayList userList = new ArrayList<>();
    private String[] users = { "B00838575", "B00838576", "B00838577", "B00838578", "B00838579", "B00838574" };
    Float[][] distanceMatrix = new Float[6][6];

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        for (Integer rowIter = 0; rowIter < 6; rowIter++) {
            for (Integer columnIter = 0; columnIter < 6; columnIter++) {

                distanceMatrix[rowIter][columnIter] = (float) columnIter;

            }
        }
        userList = (ArrayList) Arrays.asList(users);
    }

    @Test
    public void formGroupsTest() {

        GroupFormation groupFormation = new GroupFormation(distanceMatrix, userList, 3);
        ArrayList<ArrayList> resultList = groupFormation.formGroups();

        assertEquals(2, resultList.size());
        assertEquals(3, resultList.get(0).size());
        assertEquals(3, resultList.get(1).size());

    }

    @Test
    public void formGroupsTest2() {

        GroupFormation groupFormation = new GroupFormation(distanceMatrix, userList, 4);
        ArrayList<ArrayList> resultList = groupFormation.formGroups();

        assertEquals(2, resultList.size());
        assertEquals(4, resultList.get(0).size());
        assertEquals(2, resultList.get(1).size());

    }

    @Test
    public void formGroupsTest3() {

        GroupFormation groupFormation = new GroupFormation(distanceMatrix, userList, 6);
        ArrayList<ArrayList> resultList = groupFormation.formGroups();

        assertEquals(1, resultList.size());
        assertEquals(6, resultList.get(0).size());

    }

    @Test
    public void formGroupsTestError() {

        GroupFormation groupFormation = new GroupFormation(distanceMatrix, userList, 10);
        ArrayList<ArrayList> resultList = groupFormation.formGroups();

        assertEquals(1, resultList.size());
        assertEquals(6, resultList.get(0).size());

    }
}