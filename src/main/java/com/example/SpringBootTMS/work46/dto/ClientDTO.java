package com.example.SpringBootTMS.work46.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    @NotNull(message = "Login should not be missing")
    @Size(min = 2, max = 255, message = "Login should be between 2 and 255")
    private String login;
}

