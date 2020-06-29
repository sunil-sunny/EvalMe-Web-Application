package com.group18.asdc.util;

import java.util.ArrayList;

public class QueryVariableToArraylist implements IQueryVariableToArrayList {

    @Override
    public ArrayList<Object> convertQueryVariablesToArrayList(Object... objects) {
        ArrayList<Object> valueList = new ArrayList<Object>();
        for (Object eachValue : objects) {
            valueList.add(eachValue);
        }

        return valueList;
    }
    
}