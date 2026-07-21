package com.example.ewallet.PayLoads.ResponseDTOs;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionHistoryResponseDTO {

    private long totalElements;
    private int totalPages;
    private List<TransactionResponseDTO> transactions;
}
