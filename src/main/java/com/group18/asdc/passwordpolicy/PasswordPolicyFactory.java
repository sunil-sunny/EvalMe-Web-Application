package com.group18.asdc.passwordpolicy;

import com.group18.asdc.dao.IPasswordPolicyDB;

public abstract class PasswordPolicyFactory implements IPasswordPolicyManager {

    private static PasswordPolicyFactory uniqueInstance = null;

    protected PasswordPolicyFactory() {

    }

    public static PasswordPolicyFactory instance(IPasswordPolicyDB passwordPolicyDB) {
        if (null == uniqueInstance) {
            uniqueInstance = new PasswordPolicyManager(passwordPolicyDB);
        }
        return uniqueInstance;
    }

    public static void setFactory(PasswordPolicyFactory f) {
        uniqueInstance = f;
    }

    public static void resetInstance(IPasswordPolicyDB passwordPolicyDB) {
        uniqueInstance = new PasswordPolicyManager(passwordPolicyDB);
    }
}