package com.example.SpringBootTMS.work49.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAdd {
    @NotNull(message = "Name should not be missing")
    private String name;
    @NotNull(message = "Lastname should not be missing")
    private String lastname;
    @NotNull(message = "Sum should not be missing")
    @Min(value = 0,message = "Sum should be greater than 0")
    private Integer sum;
    @NotNull(message = "Group should not be missing")
    private String group;
    @NotNull(message = "IsPaid should not be missing")
    private Boolean isPaid;
}
