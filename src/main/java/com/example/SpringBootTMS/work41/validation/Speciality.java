package com.example.SpringBootTMS.work41.validation;

import com.example.SpringBootTMS.work41.validation.SpecialityValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SpecialityValidation.class)
@Target({ElementType.PARAMETER, ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Speciality {
    String message() default "Speciality does not meet requirements.Use letters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
