package com.example.ewallet.Models;
import com.example.ewallet.Models.Type.CurrencyType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "wallets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Balance → always BigDecimal (NEVER double)
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrencyType currency;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // Owner
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}