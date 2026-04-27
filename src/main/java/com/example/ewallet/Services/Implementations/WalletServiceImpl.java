package com.example.ewallet.Services.Implementations;

import com.example.ewallet.Exceptions.UserNotFoundException;
import com.example.ewallet.Models.Transaction;
import com.example.ewallet.Models.Type.TransactionStatus;
import com.example.ewallet.Models.Type.TransactionType;
import com.example.ewallet.Models.User;
import com.example.ewallet.Models.Wallet;
import com.example.ewallet.PayLoads.RequestDTOs.DepositRequestDTO;
import com.example.ewallet.PayLoads.ResponseDTOs.WalletResponseDTO;
import com.example.ewallet.Repositories.TransactionRepository;
import com.example.ewallet.Repositories.UserRepository;
import com.example.ewallet.Repositories.WalletRepository;
import com.example.ewallet.Services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public WalletResponseDTO getWalletDetails() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        return mapToResponse(wallet);
    }

    @Override
    public WalletResponseDTO deposit(DepositRequestDTO request) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        wallet.setBalance(wallet.getBalance().add(request.getAmount()));
        walletRepository.save(wallet);

        Transaction transaction = Transaction.builder()
                .receiverWallet(wallet)
                .amount(request.getAmount())
                .type(TransactionType.DEPOSIT)
                .status(TransactionStatus.SUCCESS)
                .remarks("Wallet deposit")
                .build();

        transactionRepository.save(transaction);

        return mapToResponse(wallet);
    }

    private WalletResponseDTO mapToResponse(Wallet wallet) {
        return WalletResponseDTO.builder()
                .walletId(wallet.getId())
                .fullName(wallet.getUser().getFullName())
                .balance(wallet.getBalance())
                .currency(wallet.getCurrency())
                .build();
    }

}
