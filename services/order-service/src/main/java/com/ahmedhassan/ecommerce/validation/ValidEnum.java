package com.ahmedhassan.ecommerce.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidEnumConstraint.class})
@Target(ElementType.FIELD)
@NotNull
public @interface ValidEnum {
    Class<? extends Enum<?>> enumClass();
    String message() default "Invalid value specified";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
