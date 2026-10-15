package com.Sailaja.novapay.controller;

import com.Sailaja .novapay.dto.response.TransactionResponse;
import com.Sailaja .novapay.entity.Transaction;
import com.Sailaja .novapay.service.TransactionService;
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
