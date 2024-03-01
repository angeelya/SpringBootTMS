package com.example.SpringBootTMS.work46.dto;

import com.example.SpringBootTMS.work46.validation.Account;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Remittance {
    @NotNull(message = "Account To should not be missing ")
    @Account(message = "Account To does not meet requirements.Example XXX-XXX")
    private String accountTo;
    @NotNull(message = "Account From should not be missing ")
    @Account(message = "Account From does not meet requirements.Example XXX-XXX")
    private String accountFrom;
    @NotNull(message = "Sum should not be missing ")
    @Min(value = 0, message = "Sum should be greater than 0")
    private Integer sum;
}
