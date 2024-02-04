package com.example.SpringBootTMS.work39.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LoginValidator  implements ConstraintValidator<Login,String> {

    @Override
    public void initialize(Login constraintAnnotation) {
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        return login.matches("^[A-z\\d]+$");
    }
}
