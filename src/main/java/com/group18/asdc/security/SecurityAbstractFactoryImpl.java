package com.group18.asdc.security;

public class SecurityAbstractFactoryImpl implements SecurityAbstractFactory{

    @Override
    public IPasswordEncryption getPasswordEncryption() {
        return new BCryptPasswordEncryption();
    }  
}