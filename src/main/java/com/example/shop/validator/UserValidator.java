package com.example.shop.validator;

/**
 * The UserValidator is responsible for validation.
 */
public class UserValidator {
    private static final String LOGIN_REGEX = "^[A-Za-z0-9]{1}[\\w]{1,16}[A-Za-z0-9]{1}";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9]{1}[\\w.]{4,28}[A-Za-z0-9]{1}@gmail.com$";
    private static final String PASSWORD_REGEX = "^[\\w]{6,18}$";
    private static final String PHONE_REGEX = "375(17|25|29|33|44)([1-9]{1})([0-9]{6})$";

    /**
     * Checks if login is valid.
     *
     * @param login login
     * @return login is valid or not
     */
    public static boolean isLoginCorrect(String login) {
        return isStringCorrect(login, LOGIN_REGEX);
    }

    /**
     * Checks if email is valid.
     *
     * @param email email
     * @return email is valid or not
     */
    public static boolean isEmailCorrect(String email) {
        return isStringCorrect(email, EMAIL_REGEX);
    }

    /**
     * Checks if password is valid.
     *
     * @param password password
     * @return password is valid or not
     */
    public static boolean isPasswordCorrect(String password) {
        return isStringCorrect(password, PASSWORD_REGEX);
    }

    /**
     * Checks if phone is valid.
     *
     * @param phone phone
     * @return phone is valid or not
     */
    public static boolean isPhoneCorrect(String phone) {
        return isStringCorrect(phone, PHONE_REGEX);
    }

    private static boolean isStringCorrect(String line, String regex) {
        return line != null && line.matches(regex);
    }

}
