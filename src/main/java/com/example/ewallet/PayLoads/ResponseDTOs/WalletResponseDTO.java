package com.example.ewallet.PayLoads.ResponseDTOs;

import com.example.ewallet.Models.Type.CurrencyType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletResponseDTO {

    private Long walletId;
    private String fullName;
    private BigDecimal balance;
    private CurrencyType currency;
}
