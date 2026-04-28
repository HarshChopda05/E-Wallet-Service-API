package com.example.ewallet.PayLoads.RequestDTOs;

import com.example.ewallet.Models.Type.CurrencyType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDTO {


    @NotBlank
    private String email;

    @NotBlank
    // Strong password: 1 uppercase, 1 digit, min 8 chars
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "Password must contain at least 1 uppercase letter and 1 digit"
    )
    private String password;

    @NotBlank
    private String fullName;

    private CurrencyType initialCurrency;
}
