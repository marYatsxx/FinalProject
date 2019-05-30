package com.epam.pharmacy;

import com.epam.pharmacy.util.Validator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class ValidationTest {
    private static final String VALID_PASSWORD = "12esg54w";
    private static final String INVALID_SYMBOLS_PASSWORD = "as1+/&";
    private static final String TOO_SHORT_PASSWORD = "as1";
    private static final String TOO_LONG_PASSWORD = "as14897654FDBBghjvk";

    private static final String VALID_DATE = "05/23";
    private static final String INVALID_MONTH = "25/22";
    private static final String INVALID_YEAR = "05/12";
    private static final String INVALID_DATE_FORMAT = "december/13";

    private static final String VALID_AMOUNT = "23.58";
    private static final String NEGATIVE_AMOUNT = "-23.58";
    private static final String ZERO_AMOUNT = "0";
    private static final String DOUBLE_ZERO_AMOUNT = "5fyu40";

    private String password;
    private boolean result;
    private String date;
    private String amount;

    public ValidationTest(String password, String date, String amount, boolean result){
        this.password = password;
        this.date = date;
        this.amount = amount;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> dataForTest(){
        return Arrays.asList( new Object[][]{
                {VALID_PASSWORD, VALID_DATE, VALID_AMOUNT, true},
                {INVALID_SYMBOLS_PASSWORD, INVALID_MONTH, NEGATIVE_AMOUNT, false},
                {TOO_LONG_PASSWORD, INVALID_YEAR, ZERO_AMOUNT, false},
                {TOO_SHORT_PASSWORD, INVALID_DATE_FORMAT,DOUBLE_ZERO_AMOUNT , false}
        });
    }

    @Test
    public void testValidatePassword() {
        //given
        //when
        boolean actual = Validator.validatePassword(password);
        //then
        Assert.assertEquals(this.result, actual);
    }

    @Test
    public void testValidateValidityPeriod() {
        //given
        //when
        boolean actual = Validator.validateValidityPeriod(date);
        //then
        Assert.assertEquals(this.result, actual);
    }

    @Test
    public void testCheckAmount() {
        //given
        //when
        boolean actual = Validator.checkAmount(amount);
        //then
        Assert.assertEquals(this.result, actual);
    }
}
