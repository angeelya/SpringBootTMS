package com.example.SpringBootTMS.work49.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdate {
    @NotNull(message = "Id should not be missing")
    @Min(value = 1,message = "Id should be greater than 1")
    private Long id;
    @NotNull(message = "Name should not be missing")
    private String name;
    @NotNull(message = "Lastname should not be missing")
    private String lastname;
    @NotNull(message = "IsPaid should not be missing")
    private Boolean isPaid;
}
