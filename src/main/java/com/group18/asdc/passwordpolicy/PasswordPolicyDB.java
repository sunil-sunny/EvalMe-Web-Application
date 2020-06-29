package com.group18.asdc.passwordpolicy;

import java.util.ArrayList;

import com.group18.asdc.database.SQLMethods;
import com.group18.asdc.database.SQLQueries;

public class PasswordPolicyDB implements IPasswordPolicyDB {

    @Override
    public ArrayList loadBasePoliciesFromDB() {

        SQLMethods sqlImplementation = null;
        ArrayList policiesList = new ArrayList<>();
        try {
            sqlImplementation = new SQLMethods();
            policiesList = sqlImplementation.selectQuery(SQLQueries.GET_BASEPASSWORD_POLICIES.toString(),
                    new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlImplementation != null) {
                sqlImplementation.cleanup();
            }
        }

        return policiesList;
    }

    @Override
    public ArrayList loadPoliciesFromDB() {

        SQLMethods sqlImplementation = null;
        ArrayList policiesList = new ArrayList<>();
        try {
            sqlImplementation = new SQLMethods();
            policiesList = sqlImplementation.selectQuery(SQLQueries.GET_HISTORYPASSWORD_POLICIES.toString(),
                    new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlImplementation != null) {
                sqlImplementation.cleanup();
            }
        }

        return policiesList;
    }

}