package com.example.SpringBootTMS.model;

import com.example.SpringBootTMS.validation.Speciality;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Size(min = 2, max=200 , message = "Login should be between 2 and 200")
    private String name;
    @Min(value = 0, message = "Age should be greater than 0")
    private int age;
    @Speciality
    private String speciality;
}
