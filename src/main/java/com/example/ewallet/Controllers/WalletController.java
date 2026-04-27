package com.example.ewallet.Controllers;

import com.example.ewallet.PayLoads.RequestDTOs.DepositRequestDTO;
import com.example.ewallet.PayLoads.ResponseDTOs.WalletResponseDTO;
import com.example.ewallet.Services.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {

    public final WalletService walletService;

    //Get Logged-in User Wallet
    @GetMapping("/me")
    public ResponseEntity<WalletResponseDTO> getWallet() {
        WalletResponseDTO response = walletService.getWalletDetails();
        return ResponseEntity.ok(response);
    }

    //Deposit Money
    @PostMapping("/deposit")
    public ResponseEntity<WalletResponseDTO> deposit(
            @Valid @RequestBody DepositRequestDTO request
    ) {
        WalletResponseDTO response = walletService.deposit(request);
        return ResponseEntity.ok(response);
    }

}
