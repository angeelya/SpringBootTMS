package com.example.SpringBootTMS.work46.validation;

import com.example.SpringBootTMS.validation.LoginValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
@Documented
@Constraint(validatedBy = AccountValidation.class)
@Target({ElementType.PARAMETER, ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Account {
    String message() default "Account does not meet requirements.Example XXX-XXX";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
