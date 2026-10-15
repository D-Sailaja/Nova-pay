package com.uday.novapay.dto.response;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class WithdrawResponse {

    private String message;
    private BigDecimal balance;
}
