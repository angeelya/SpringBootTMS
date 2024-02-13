package com.example.SpringBootTMS.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsersAppTest {
    private static Validator validator;
    private UsersApp usersApp;

    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Test
    public void validationLoginTest() {
        String login = "Irina";
        usersApp = new UsersApp(1L, login, 23);
        assertEquals(usersApp.getLogin(), login);
        usersApp.setLogin("");
        checkValidation("Login does not meet requirements.Use letters and numbers\nLogin should be between 2 and 200");
    }

    private void checkValidation(String expectedMessage) {
        Set<ConstraintViolation<UsersApp>> valid = validator.validate(usersApp);
        assertTrue(valid.size() > 0);
        String actualMessage = valid.stream().map(v -> v.getMessage()).collect(Collectors.joining("\n"));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void validationAgeTest() {
        Integer age = 21;
        usersApp = new UsersApp(1L,"Irina" , age);
        assertEquals(usersApp.getAge(), age);
        usersApp.setAge(-12);
        checkValidation("Age should be greater than 0");
    }
}