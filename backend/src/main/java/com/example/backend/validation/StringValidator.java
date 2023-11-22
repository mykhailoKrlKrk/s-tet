package com.example.backend.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class StringValidator implements ConstraintValidator<Formatter, String> {
    public static final String COMMENT_PATTERN =
            "^(?!\\s+$)(?![\\p{P}\\s]+$)(?!\\d+$)(?!(.)\\1*$)(?!.*[!&@*#]+)(?!.*\\d)\\S.*\\S$";

    @Override
    public boolean isValid(String comment, ConstraintValidatorContext constraintValidatorContext) {
        return comment != null && Pattern.compile(COMMENT_PATTERN).matcher(comment).matches();
    }
}
