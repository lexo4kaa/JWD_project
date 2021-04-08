package com.example.final_project_shop.validator;

public class UserValidator {
    private static final String LOGIN_REGEX = "^[A-Za-z0-9_-]{3,18}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9_-]{3,30}@(gmail|yandex|mail)\\.(com|ru|by)$";
    private static final String PASSWORD_REGEX = "^[A-Za-z0-9_-]{6,18}$";
    private static final String PHONE_REGEX = "375(17|25|29|33|44)([1-9]{1})([0-9]{6})$";

    public static boolean isLoginCorrect(String login) {
        return isStringCorrect(login, LOGIN_REGEX);
    }

    public static boolean isEmailCorrect(String email) {
        return isStringCorrect(email, EMAIL_REGEX);
    }

    public static boolean isPasswordCorrect(String password) {
        return isStringCorrect(password, PASSWORD_REGEX);
    }

    public static boolean isPhoneCorrect(String phone) {
        return isStringCorrect(phone, PHONE_REGEX);
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean isStringCorrect = false;
        if (line != null) {
            isStringCorrect = line.matches(regex);
        }
        return isStringCorrect;
    }

}
