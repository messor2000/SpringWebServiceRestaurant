package epam.project.spring.validator;

import java.util.regex.Pattern;

/**
 * @author Aleksandr Ovcharenko
 */
public class DataValidator {
    private static final String REGEX_CHECK_FOR_EMAIL = "^([\\w\\-\\.]+)@([\\w\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    private static final String REGEX_CHECK_FOR_PASSWORD = "^([\\wа-яА-Я]{7,20})$";
    private static final String REGEX_CHECK_FOR_LOGIN = "^[a-zA-Zа-яА-Я\\\\s]{2,20}$";

    private static final Integer MIN_PURSE_AMOUNT = 0;

    public static boolean validateLogin(String login) {
        return Pattern.matches(REGEX_CHECK_FOR_EMAIL, login);
    }

    public static boolean validatePassword(String password) {
        return Pattern.matches(REGEX_CHECK_FOR_PASSWORD, password);
    }

    public static boolean validateName(String name) {
        return Pattern.matches(REGEX_CHECK_FOR_LOGIN, name);
    }

    public static boolean validatePurseAmount(Integer amount) {
        return amount >= MIN_PURSE_AMOUNT;
    }
}
