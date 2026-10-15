package com.uday.novapay.service;
import com.uday.novapay.dto.response.BalanceResponse;
import com.uday.novapay.dto.response.DepositResponse;
import com.uday.novapay.dto.response.WithdrawResponse;
import com.uday.novapay.entity.Transaction;
import com.uday.novapay.enums.TransactionType;
import com.uday.novapay.exception.InsufficientBalanceException;
import com.uday.novapay.exception.UserNotFoundException;
import com.uday.novapay.exception.WalletNotFoundException;
import com.uday.novapay.repository.TransactionRepository;
import com.uday.novapay.dto.response.TransferResponse;
import com.uday.novapay.entity.User;
import com.uday.novapay.entity.Wallet;
import com.uday.novapay.repository.UserRepository;
import com.uday.novapay.repository.WalletRepository;
import org.springframework.stereotype.Service;
import com.uday.novapay.dto.request.TransferRequest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
    public class WalletService {
        private final UserRepository userRepository;

        private final WalletRepository walletRepository;
        private final TransactionRepository transactionRepository;

        public WalletService(UserRepository userRepository, WalletRepository walletRepository, TransactionRepository transactionRepository) {
            this.userRepository = userRepository;

            this.walletRepository = walletRepository;
            this.transactionRepository = transactionRepository;
        }

        public Wallet createWallet(User savedUser) {

            Wallet wallet = Wallet.builder()
                    .balance(BigDecimal.ZERO)
                    .user(savedUser)
                    .build();

            return walletRepository.save(wallet);
        }
    public DepositResponse deposit(Long userId, BigDecimal amount) {

        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));

        wallet.setBalance(wallet.getBalance().add(amount));
        Transaction transaction = Transaction.builder()
                .type(TransactionType.DEPOSIT)
                .amount(amount)
                .receiver(wallet.getUser())
                .transactionTime(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

        walletRepository.save(wallet);

        return DepositResponse.builder()
                .message("Deposit Successful")
                .balance(wallet.getBalance())
                .build();
    }

        public WithdrawResponse withdraw(Long userId, BigDecimal amount) {

            Wallet wallet = walletRepository.findByUserId(userId)
                    .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));

            if (wallet.getBalance().compareTo(amount) < 0) {
                throw new InsufficientBalanceException("Insufficient Balance");
            }

            wallet.setBalance(wallet.getBalance().subtract(amount));
            Transaction transaction = Transaction.builder()
                    .type(TransactionType.WITHDRAW)
                    .amount(amount)
                    .sender(wallet.getUser())
                    .transactionTime(LocalDateTime.now())
                    .build();

            transactionRepository.save(transaction);


            walletRepository.save(wallet);

            return WithdrawResponse.builder()
                    .message("Withdraw Successful")
                    .balance(wallet.getBalance())
                    .build();
        }

        public BalanceResponse getBalance(Long userId) {

            Wallet wallet = walletRepository.findByUserId(userId)
                    .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));

            return BalanceResponse.builder()
                    .balance(wallet.getBalance())
                    .build();
        }
@Transactional
        public TransferResponse transfer(Long senderId, TransferRequest request) {

            Wallet senderWallet = walletRepository.findByUserId(senderId)
                    .orElseThrow(() -> new WalletNotFoundException("Sender wallet not found"));

            User receiver = userRepository.findByEmail(request.getReceiverEmail())
                    .orElseThrow(() -> new UserNotFoundException("Receiver not found"));

            Wallet receiverWallet = walletRepository.findByUserId(receiver.getId())
                    .orElseThrow(() -> new WalletNotFoundException("Receiver wallet not found"));

            if (senderWallet.getBalance().compareTo(request.getAmount()) < 0) {
                throw new InsufficientBalanceException("Insufficient Balance");
            }

            senderWallet.setBalance(
                    senderWallet.getBalance().subtract(request.getAmount())
            );

            receiverWallet.setBalance(
                    receiverWallet.getBalance().add(request.getAmount())
            );

            walletRepository.save(senderWallet);
            walletRepository.save(receiverWallet);
            Transaction transaction = Transaction.builder()
                    .type(TransactionType.TRANSFER)
                    .amount(request.getAmount())
                    .sender(senderWallet.getUser())
                    .receiver(receiver)
                    .transactionTime(LocalDateTime.now())
                    .build();

            transactionRepository.save(transaction);

            return TransferResponse.builder()
                    .message("Transfer Successful")
                    .senderBalance(senderWallet.getBalance())
                    .build();
        }
    }

