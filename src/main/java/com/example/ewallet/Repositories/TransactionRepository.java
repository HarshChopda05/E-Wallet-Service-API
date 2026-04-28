package com.example.ewallet.Repositories;

import com.example.ewallet.Models.Transaction;
import com.example.ewallet.Models.Type.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = """
    SELECT * FROM transactions t
    WHERE (t.sender_wallet_id = :walletId OR t.receiver_wallet_id = :walletId)
      AND (:type IS NULL OR t.type = :type)
      AND (:status IS NULL OR t.status = :status)
    ORDER BY t.created_at DESC
    """,
            countQuery = """
    SELECT COUNT(*) FROM transactions t
    WHERE (t.sender_wallet_id = :walletId OR t.receiver_wallet_id = :walletId)
      AND (:type IS NULL OR t.type = :type)
      AND (:status IS NULL OR t.status = :status)
    """,
            nativeQuery = true)
    Page<Transaction> findTransactionsWithFilters(
            @Param("walletId") Long walletId,
            @Param("type") String type,
            @Param("status") String status,
            Pageable pageable
    );
//
//    @Query(value = """
//    SELECT * FROM transactions t
//    WHERE (t.sender_wallet_id = :walletId OR t.receiver_wallet_id = :walletId)
//      AND t.status = :status
//    ORDER BY t.created_at DESC
//    """,
//            countQuery = """
//    SELECT COUNT(*) FROM transactions t
//    WHERE (t.sender_wallet_id = :walletId OR t.receiver_wallet_id = :walletId)
//      AND t.status = :status
//    """,
//            nativeQuery = true)
//    Page<Transaction> findTransactionsByWalletIdAndStatus(
//            @Param("walletId") Long walletId,
//            @Param("status") String status,
//            Pageable pageable
//    );
}
