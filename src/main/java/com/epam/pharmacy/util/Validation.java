package com.epam.pharmacy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static final String PASSWORD_REGEX = "^.{4,8}$";

    public static boolean validatePassword(String password){
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}
