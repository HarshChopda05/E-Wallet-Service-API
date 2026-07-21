package com.example.ewallet.PayLoads.RequestDTOs;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferRequestDTO {

    @Email
    @NotNull(message = "Recipient Email must be required!")
    private String recipientEmail;

    @NotNull
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0 !")
    private BigDecimal amount;

    private String remarks;
}