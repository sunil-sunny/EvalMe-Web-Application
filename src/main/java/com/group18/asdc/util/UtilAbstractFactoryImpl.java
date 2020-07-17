package com.group18.asdc.util;

public class UtilAbstractFactoryImpl implements UtilAbstractFactory  {

    @Override
    public IQueryVariableToArrayList getQueryVariableToArrayList() {
        return new QueryVariableToArraylist();
    }

    @Override
    public IRandomStringGenerator getRandomStringGenerator() {
        return new RandomStringGenerator();
    }

    @Override
    public ICustomStringUtils getCustomStringUtils() {
        return new CustomStringUtils();
    }
    
}