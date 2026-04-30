package com.ahmedhassan.ecommerce.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.jspecify.annotations.NonNull;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidEnumConstraint implements ConstraintValidator<ValidEnum, String> {

    private Set<String> values;

    @Override
    public void initialize(@NonNull ValidEnum annotation) {
        values = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return values.contains(value);
    }
}
