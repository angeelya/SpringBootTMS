package com.example.SpringBootTMS.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SpecialityValidation implements ConstraintValidator<Speciality,String> {

    @Override
    public void initialize(Speciality speciality) {
    }

    @Override
    public boolean isValid(String speciality, ConstraintValidatorContext constraintValidatorContext) {
        return speciality.matches("^[A-z]+$");
    }
}
