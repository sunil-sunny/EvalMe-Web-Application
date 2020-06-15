package com.group18.asdc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CommonUtil {

    private static CommonUtil commonUtilObj = null;

    public static final HashMap<String, String> roleVsLandingPage = new HashMap<String, String>();

    public static enum userRoles {
        ADMIN, GUEST
    };

    static {
        roleVsLandingPage.put(userRoles.ADMIN.name(), "/adminhome");
        roleVsLandingPage.put(userRoles.GUEST.name(), "/coursepage");
    }

    private CommonUtil() {
    }

    /**
     * 
     * @return
     */
    public static CommonUtil getInstance() {
        if (commonUtilObj == null) {
            commonUtilObj = new CommonUtil();
        }
        //
        return commonUtilObj;
    }

    /**
     * 
     * @param objects
     * @return
     */
    public ArrayList<Object> convertQueryVariablesToArrayList(Object... objects) {
        ArrayList<Object> valueList = new ArrayList<Object>();
        for (Object eachValue : objects) {
            valueList.add(eachValue);
        }

        return valueList;
    }

    /**
     * 
     */
    public String generateResetPassword() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        return generatedString;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}