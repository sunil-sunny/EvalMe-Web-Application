package com.group18.asdc.passwordpolicy;

import java.util.ArrayList;

import com.group18.asdc.entities.PasswordHistory;
import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.security.IPasswordEncryption;
import com.group18.asdc.service.PasswordHistoryService;

public class HistoryConstraintPolicy implements IPasswordPolicy {

    private Integer numberOfHistoryRecords = null;
    private PasswordHistoryService passwordHistoryService;
    private IPasswordEncryption passwordEncryption;

    public HistoryConstraintPolicy(String numberOfHistoryRecords, PasswordHistoryService passwordHistoryService,
            IPasswordEncryption passwordEncryption) {
        this.numberOfHistoryRecords = Integer.parseInt(numberOfHistoryRecords);
        this.passwordHistoryService = passwordHistoryService;
        this.passwordEncryption = passwordEncryption;
    }

    @Override
    public void validate(String bannerId, String password) throws PasswordPolicyException {

        ArrayList<PasswordHistory> passwordHistoryList = passwordHistoryService.getPasswordHistory(bannerId,
                numberOfHistoryRecords);
        for (PasswordHistory eachPasswordHistory : passwordHistoryList) {
            String eachPassword = eachPasswordHistory.getPassword();
            if (passwordEncryption.matches(password, eachPassword)) {
                throw new PasswordPolicyException(
                        "Password matches with one of the old " + numberOfHistoryRecords + " passwords");
            }
        }

    }

}