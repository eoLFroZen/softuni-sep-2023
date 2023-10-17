package com.plannerapp.vallidation;

import com.plannerapp.model.annotation.StringDateInFuture;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class StringDateInFutureValidator implements ConstraintValidator<StringDateInFuture, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && !value.isBlank()) {
            LocalDate parse = LocalDate.parse(value);
            return parse.isAfter(LocalDate.now());
        }

        return false;
    }

    @Override
    public void initialize(StringDateInFuture constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
