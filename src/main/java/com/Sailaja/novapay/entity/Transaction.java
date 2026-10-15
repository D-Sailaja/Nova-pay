package com.uday.novapay.entity;
import com.uday.novapay.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class Transaction {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Enumerated(EnumType.STRING)
        private TransactionType type;

        @Column(nullable = false)
        private BigDecimal amount;

        @ManyToOne
        @JoinColumn(name = "sender_id")
        private User sender;

        @ManyToOne
        @JoinColumn(name = "receiver_id")
        private User receiver;

        private LocalDateTime transactionTime;
    }
