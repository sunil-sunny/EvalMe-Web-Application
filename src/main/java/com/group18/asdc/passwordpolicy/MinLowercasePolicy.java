package com.group18.asdc.passwordpolicy;

import com.group18.asdc.errorhandling.PasswordPolicyException;
import com.group18.asdc.util.ICustomStringUtils;

public class MinLowercasePolicy implements IBasePasswordPolicy {

    private Integer minLowerCase = null;
    private ICustomStringUtils customStringUtils = null;

    public MinLowercasePolicy() {

    }

    public MinLowercasePolicy(String minLowerCase, ICustomStringUtils customStringUtils) {

        this.minLowerCase = Integer.parseInt(minLowerCase);
        this.customStringUtils = customStringUtils;
    }

    @Override
    public void validate(String password) throws PasswordPolicyException {

        Integer lowerCaseCharsCount = customStringUtils.getLowerCaseCharactersCount(password);
        if (lowerCaseCharsCount < this.minLowerCase) {
            throw new PasswordPolicyException(
                    "Password does not contain " + minLowerCase + " of lower case characters");
        }
    }

}