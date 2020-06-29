package com.group18.asdc.util;

public class JavaMailSenderConfigurationMock implements IJavaMailSenderConfiguration {

    @Override
    public String getEmail() {

        return "abcd@gmail.com";
    }

    @Override
    public String getPassword() {

        return "password";
    }

}