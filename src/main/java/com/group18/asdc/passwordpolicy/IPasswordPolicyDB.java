package com.group18.asdc.passwordpolicy;

import java.util.ArrayList;

public interface IPasswordPolicyDB {
    
    public ArrayList loadBasePoliciesFromDB();

    public ArrayList loadPoliciesFromDB();
}