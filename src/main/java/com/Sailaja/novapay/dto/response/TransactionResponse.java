package com.Sailaja .novapay.dto.response;

import com.Sailaja .novapay.enums.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionResponse {

    private TransactionType type;
    private BigDecimal amount;
    private String senderEmail;
    private String receiverEmail;
    private LocalDateTime transactionTime;
}
