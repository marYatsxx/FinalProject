package com.epam.pharmacy.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String PASSWORD_REGEX = "^[\\w]{4,8}$";
    private static final String VALIDITY_REGEX = "[0-9]{2}/[0-9]{2}";
    private static final String LETTERS_REGEX = "[\\D&&[^\\.]]";
    private static final String AMOUNT_REGEX = "[0-9]{1,4}(\\.[0-9]{2})?";

    public static boolean validatePassword(String password){
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public static boolean validateValidityPeriod(String validityPeriod){
        Pattern pattern = Pattern.compile(VALIDITY_REGEX);
        Matcher matcher = pattern.matcher(validityPeriod);
        if(matcher.find()){
            String[] period = validityPeriod.split("/");
            int month = Integer.parseInt(period[0]);
            int year = Integer.parseInt(period[1]);
            return month > 0 && month <= 12 && year >= (LocalDate.now().getYear()%100);
        }
        return false;
    }

    public static boolean checkAmount(String amount){
        Pattern pattern = Pattern.compile(LETTERS_REGEX);
        Matcher matcher = pattern.matcher(amount);
        if(!matcher.find()){
            pattern = Pattern.compile(AMOUNT_REGEX);
            matcher = pattern.matcher(amount);
            if(matcher.find()){
                BigDecimal sum = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_DOWN);
                return sum.compareTo(new BigDecimal(0.0))== 1;
            }
        }
        return false;
    }
}
