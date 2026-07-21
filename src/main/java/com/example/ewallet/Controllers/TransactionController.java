package com.example.ewallet.Controllers;

import com.example.ewallet.Models.Type.TransactionType;
import com.example.ewallet.PayLoads.RequestDTOs.TransferRequestDTO;
import com.example.ewallet.PayLoads.ResponseDTOs.TransactionHistoryResponseDTO;
import com.example.ewallet.PayLoads.ResponseDTOs.TransactionResponseDTO;
import com.example.ewallet.Services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Transaction APIs", description = "Money transfer and transaction history")
@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    //Transfer Money
    @Operation(summary = "Transfer Money",
            description = "Transfers funds from the authenticated user's wallet to another user's wallet")
    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponseDTO> transfer(@Valid @RequestBody TransferRequestDTO request) {
        TransactionResponseDTO response = transactionService.transfer(request);
        return ResponseEntity.ok(response);
    }

    //Transaction History (Paginated)
    @Operation(summary = "Get Transaction History",
            description = "Retrieves paginated transaction history with filtering by transaction type")
    @GetMapping
    public ResponseEntity<TransactionHistoryResponseDTO> getTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) TransactionType type
    ) {
        TransactionHistoryResponseDTO response =
                transactionService.getTransactions(page, size, type);

        return ResponseEntity.ok(response);
    }

}
