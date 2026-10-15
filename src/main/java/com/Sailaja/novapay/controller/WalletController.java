package com.Sailaja.novapay.controller;

import com.Sailaja .novapay.dto.response.BalanceResponse;
import com.Sailaja .novapay.dto.response.DepositResponse;
import com.Sailaja .novapay.dto.response.WithdrawResponse;
import com.Sailaja .novapay.entity.Wallet;
import com.Sailaja .novapay.service.WalletService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.Sailaja .novapay.dto.request.TransferRequest;
import com.Sailaja .novapay.dto.response.TransferResponse;
import java.math.BigDecimal;

@RestController
    @RequestMapping("/api/wallet")
    public class WalletController {

        private final WalletService walletService;

        public WalletController(WalletService walletService) {
            this.walletService = walletService;
        }
        @PostMapping("/deposit/{userId}")
        public DepositResponse deposit(@PathVariable Long userId,
                                       @RequestParam BigDecimal amount) {

            return walletService.deposit(userId, amount);
        }
    @PostMapping("/withdraw/{userId}")
    public WithdrawResponse withdraw(@PathVariable Long userId,
                                     @RequestParam BigDecimal amount) {

        return walletService.withdraw(userId, amount);
    }

    @GetMapping("/balance/{userId}")
    public BalanceResponse getBalance(@PathVariable Long userId) {
        return walletService.getBalance(userId);
    }

    @PostMapping("/transfer/{senderId}")
    public TransferResponse transfer(@PathVariable Long senderId,
                                     @RequestBody @Valid TransferRequest request) {

        return walletService.transfer(senderId, request);
    }
    }

