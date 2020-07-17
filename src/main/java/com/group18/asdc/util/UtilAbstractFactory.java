package com.group18.asdc.util;

public interface UtilAbstractFactory {

    public IQueryVariableToArrayList getQueryVariableToArrayList();

    public IRandomStringGenerator getRandomStringGenerator();

    public ICustomStringUtils getCustomStringUtils();
    
}