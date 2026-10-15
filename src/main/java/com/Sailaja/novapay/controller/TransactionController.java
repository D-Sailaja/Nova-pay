package com.uday.novapay.controller;

import com.uday.novapay.dto.response.TransactionResponse;
import com.uday.novapay.entity.Transaction;
import com.uday.novapay.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{userId}")
    public List<TransactionResponse> getHistory(@PathVariable Long userId) {
        return transactionService.getTransactionHistory(userId);
    }
}
