package com.example.SpringBootTMS.work49.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentGroup {
    @NotNull(message = "Id should not be missing")
    @Min(value = 1,message = "Id should be greater than 1")
    private Long id;
    @NotNull(message = "Group should not be missing")
    private String group;
}
