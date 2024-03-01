package com.example.SpringBootTMS.work46.validation;

import com.example.SpringBootTMS.validation.Login;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccountValidation implements ConstraintValidator<Account,String> {
    @Override
    public void initialize(Account constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String account, ConstraintValidatorContext constraintValidatorContext) {
           return account.matches("^\\d{3}-\\d{3}$");
    }
}
