package com.example.SpringBootTMS.model;

import com.example.SpringBootTMS.validation.Login;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsersApp {
    private Long id;
    @NotEmpty(message = "Login should not be empty")
    @Size(min = 2, max=200 , message = "Login should be between 2 and 200")
    @Login
    private String login;
    @Min(value = 0, message = "Age should be greater than 0")
    private int age;
}
