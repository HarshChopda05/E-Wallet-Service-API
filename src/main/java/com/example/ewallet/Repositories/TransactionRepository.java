package com.example.ewallet.Repositories;

import com.example.ewallet.Models.Transaction;
import com.example.ewallet.Models.Type.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findBySenderWallet_IdOrReceiverWallet_Id(
            Long senderId,
            Long receiverId,
            Pageable pageable
    );

    Page<Transaction> findByTypeAndSenderWallet_IdOrReceiverWallet_Id(
            TransactionType type,
            Long senderId,
            Long receiverId,
            Pageable pageable
    );
}
