package com.group18.asdc.passwordpolicy;

import com.group18.asdc.errorhandling.PasswordPolicyException;

public interface IPasswordPolicy {

    public void validate(String bannerId, String password) throws PasswordPolicyException;
    
}