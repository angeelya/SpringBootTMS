package com.example.SpringBootTMS.work46.dto;

import com.example.SpringBootTMS.work46.validation.Account;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    @Account
    @NotNull(message = "Account should not be missing")
    private String account;
    @NotNull(message = "Sum should not be missing")
    @Min(value = 0,message = "Sum should be greater than 0")
    private Integer sum;
    @NotNull(message = "Client id should not be missing ")
    @Min(value = 1, message = "Client id should be greater than 1")
    private Long client_id;
}
