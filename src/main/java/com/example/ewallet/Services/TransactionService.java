package com.example.ewallet.Services;

import com.example.ewallet.Models.Type.TransactionType;
import com.example.ewallet.PayLoads.RequestDTOs.TransferRequestDTO;
import com.example.ewallet.PayLoads.ResponseDTOs.TransactionHistoryResponseDTO;
import com.example.ewallet.PayLoads.ResponseDTOs.TransactionResponseDTO;
import jakarta.validation.Valid;

public interface TransactionService {

    TransactionResponseDTO transfer(@Valid TransferRequestDTO request);
    TransactionHistoryResponseDTO getTransactions(int page, int size, TransactionType type);
}
