package com.group18.asdc.passwordpolicy;

import com.group18.asdc.dao.IPasswordPolicyDB;

public abstract class BasePasswordPolicyFactory implements IBasePasswordPolicyManager {

    private static BasePasswordPolicyFactory uniqueInstance = null;

    protected BasePasswordPolicyFactory() {

    }

    public static BasePasswordPolicyFactory instance(IPasswordPolicyDB passwordPolicyDB) {
        if (null == uniqueInstance) {
            uniqueInstance = new BasePasswordPolicyManager(passwordPolicyDB);
        }
        return uniqueInstance;
    }

    public static void setFactory(BasePasswordPolicyFactory f) {
        uniqueInstance = f;
    }

    public static void resetInstance(IPasswordPolicyDB passwordPolicyDB) {
        uniqueInstance = new BasePasswordPolicyManager(passwordPolicyDB);
    }

}