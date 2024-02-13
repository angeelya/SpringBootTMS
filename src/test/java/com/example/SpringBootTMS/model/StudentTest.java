package com.example.SpringBootTMS.model;

import jakarta.validation.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentTest {
    private static Validator validator;
    private Student student;

    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Test
    public void validationName() {
        String name = "Elena";
        student = new Student(name, 21, "Art");
        assertEquals(student.getName(), name);
        student.setName("");
        checkValidation("Name should be between 2 and 200");
    }


    @Test
    public void validationAge() {
        Integer age = 21;
        student = new Student("Elena", age, "Art");
        assertEquals(student.getAge(), age);
        student.setAge(-12);
        checkValidation("Age should be greater than 0");
    }

    @Test
    public void validationSpeciality() {
        String speciality = "Economy";
        student = new Student("Elena", 21, speciality);
        assertEquals(student.getSpeciality(), speciality);
        student.setSpeciality("A67");
        checkValidation("Speciality does not meet requirements.Use letters");
    }

    private void checkValidation(String expectedMessage) {
        Set<ConstraintViolation<Student>> valid = validator.validate(student);
        assertTrue(valid.size() > 0);
        String actualMessage = valid.stream().map(v -> v.getMessage()).collect(Collectors.joining());
        assertEquals(actualMessage, expectedMessage);
    }
}