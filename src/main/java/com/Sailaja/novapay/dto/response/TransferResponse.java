package com.Sailaja .novapay.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

    @Data
    @Builder
    public class TransferResponse {

        private String message;
        private BigDecimal senderBalance;
    }

