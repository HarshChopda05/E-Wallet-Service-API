package com.example.ewallet.Services.Implementations;

import com.example.ewallet.Exceptions.InsufficientBalanceException;
import com.example.ewallet.Exceptions.UserNotFoundException;
import com.example.ewallet.Exceptions.WalletNotFoundException;
import com.example.ewallet.Models.Transaction;
import com.example.ewallet.Models.Type.TransactionStatus;
import com.example.ewallet.Models.Type.TransactionType;
import com.example.ewallet.Models.User;
import com.example.ewallet.Models.Wallet;
import com.example.ewallet.PayLoads.RequestDTOs.TransferRequestDTO;
import com.example.ewallet.PayLoads.ResponseDTOs.TransactionHistoryResponseDTO;
import com.example.ewallet.PayLoads.ResponseDTOs.TransactionResponseDTO;
import com.example.ewallet.Repositories.TransactionRepository;
import com.example.ewallet.Repositories.UserRepository;
import com.example.ewallet.Repositories.WalletRepository;
import com.example.ewallet.Services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public TransactionResponseDTO transfer(TransferRequestDTO request) {

        String senderEmail = SecurityContextHolder.getContext().
                getAuthentication().getName();

        User sender = userRepository.findByEmail(senderEmail)
                .orElseThrow(()-> new UserNotFoundException("Sender Not Found!"));

        User receiver = userRepository.findByEmail(request.getRecipientEmail())
                .orElseThrow(() -> new UserNotFoundException("Receiver Not Found!"));

        Wallet senderWallet = walletRepository.findByUser(sender)
                .orElseThrow(() -> new WalletNotFoundException("Sender wallet not found"));

        Wallet receiverWallet = walletRepository.findByUser(receiver)
                .orElseThrow(() -> new WalletNotFoundException("Receiver wallet not found"));


        if (senderWallet.getBalance().compareTo(request.getAmount()) < 0){
            throw new InsufficientBalanceException("Insufficient Fund!");
        }

        //Deduct & Add
        senderWallet.setBalance(senderWallet.getBalance().subtract(request.getAmount()));
        receiverWallet.setBalance(receiverWallet.getBalance().add(request.getAmount()));

        walletRepository.save(senderWallet);
        walletRepository.save(receiverWallet);

        Transaction transaction = Transaction.builder()
                .senderWallet(senderWallet)
                .receiverWallet(receiverWallet)
                .amount(request.getAmount())
                .type(TransactionType.TRANSFER)
                .status(TransactionStatus.SUCCESS)
                .remarks(request.getRemarks())
                .build();

        transactionRepository.save(transaction);

        return mapToResponse(transaction);
    }

    @Override
    public TransactionHistoryResponseDTO getTransactions(int page, int size, TransactionType type) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("User Not Found!"));

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(()-> new WalletNotFoundException("Wallet Not Found!"));

        Pageable pageable = PageRequest.of(page, size, Sort.by("created_At").descending());

        Page<Transaction> transactions;

        transactions = transactionRepository.findTransactionsWithFilters(wallet.getId(),
                type != null ? type.name() : null,
                null, //Not filter by status for this moment
                pageable
                );

        return TransactionHistoryResponseDTO.builder()
                .totalElements(transactions.getTotalElements())
                .totalPages(transactions.getTotalPages())
                .transactions(transactions.getContent().stream()
                        .map(this::mapToResponse).collect(toList()))
                .build();
    }


    private TransactionResponseDTO mapToResponse(Transaction t) {
        return TransactionResponseDTO.builder()
                .transactionId(t.getId())
                .amount(t.getAmount())
                .type(t.getType())
                .status(t.getStatus())
                .senderEmail(t.getSenderWallet() != null
                        ? t.getSenderWallet().getUser().getEmail()
                        : null)
                .receiverEmail(t.getReceiverWallet().getUser().getEmail())
                .remarks(t.getRemarks())
                .createdAt(t.getCreatedAt())
                .build();
    }
}
