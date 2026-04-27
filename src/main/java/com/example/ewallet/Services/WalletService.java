package com.example.ewallet.Services;

import com.example.ewallet.PayLoads.RequestDTOs.DepositRequestDTO;
import com.example.ewallet.PayLoads.ResponseDTOs.WalletResponseDTO;
import jakarta.validation.Valid;

public interface WalletService {

    WalletResponseDTO getWalletDetails();
    WalletResponseDTO deposit(@Valid DepositRequestDTO request);
}
