package com.example.ewallet.Controllers;

import com.example.ewallet.PayLoads.RequestDTOs.DepositRequestDTO;
import com.example.ewallet.PayLoads.ResponseDTOs.WalletResponseDTO;
import com.example.ewallet.Services.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Wallet APIs", description = "Wallet operations like balance check and adding funds")
@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {

    public final WalletService walletService;

    //Get Logged-in User Wallet
    @Operation(summary = "Get Wallet Details", description = "Fetches the wallet information of the authenticated user")
    @GetMapping("/me")
    public ResponseEntity<WalletResponseDTO> getWallet() {
        WalletResponseDTO response = walletService.getWalletDetails();
        return ResponseEntity.ok(response);
    }

    //Deposit Money
    @Operation(summary = "Add Funds to Wallet", description = "Adds a specified amount to the user's wallet balance")
    @PostMapping("/deposit")
    public ResponseEntity<WalletResponseDTO> deposit(
            @Valid @RequestBody DepositRequestDTO request
    ) {
        WalletResponseDTO response = walletService.deposit(request);
        return ResponseEntity.ok(response);
    }

}
