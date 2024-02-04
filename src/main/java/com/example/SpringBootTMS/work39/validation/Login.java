package com.example.SpringBootTMS.work39.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LoginValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {
    String message() default "Login does not meet requirements.Use letters and numbers";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
