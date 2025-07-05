package com.manage.employees.utils.annotations.validator;

import com.manage.employees.utils.annotations.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashMap;
import java.util.Map;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !value.isBlank() && value.length() >= 8 && validatePassword(value);
    }

    private boolean validatePassword(String value) {
        boolean isUppercasePresent = false;
        boolean isSpecialCharPresent = false;
        Map<Character, Integer> chars = new HashMap<>();

        for (int i = 0; i < 26; i++) {
            char uppercaseChar = (char) ('A' + i);
            chars.put(uppercaseChar, i);
        }
        chars.put('/', 26);
        chars.put('@', 27);
        chars.put('.', 28);
        chars.put(',', 29);
        chars.put(';', 30);

        for (int i = 0; i < value.length(); i++) {
            if (chars.containsKey(value.charAt(i))) {
                if (chars.get(value.charAt(i)) < 26) {
                    isUppercasePresent = true;
                } else {
                    isSpecialCharPresent = true;
                }
            }
        }

        return isUppercasePresent & isSpecialCharPresent;
    }
}
