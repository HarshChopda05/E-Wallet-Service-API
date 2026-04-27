package com.example.ewallet.PayLoads.ResponseDTOs;

import com.example.ewallet.Models.Type.TransactionStatus;
import com.example.ewallet.Models.Type.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponseDTO {

    private Long transactionId;
    private BigDecimal amount;
    private TransactionType type;
    private TransactionStatus status;
    private String senderEmail;
    private String receiverEmail;
    private String remarks;
    private LocalDateTime createdAt;
}
