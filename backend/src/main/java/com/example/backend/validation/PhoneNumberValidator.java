package com.example.backend.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    public static final String PHONE_NUMBER_PATTERN = "\\(\\d{3}\\) \\d{3}-\\d{4}";

    @Override
    public boolean isValid(String number, ConstraintValidatorContext constraintValidatorContext) {
        return number != null && Pattern.compile(PHONE_NUMBER_PATTERN).matcher(number).matches();
    }
}
