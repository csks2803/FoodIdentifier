package com.food.identifier.other.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

/**
 * Created by taras on 12/9/2017.
 */

public class Utility {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", CASE_INSENSITIVE);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})");

    public static boolean emailValidate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean passwordValidate(String emailStr) {
        Matcher matcher = PASSWORD_PATTERN.matcher(emailStr);
        return matcher.matches();
    }
}
