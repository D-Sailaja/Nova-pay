package com.Sailaja .novapay.service;

import com.Sailaja .novapay.dto.response.TransactionResponse;
import com.Sailaja .novapay.entity.Transaction;
import com.Sailaja .novapay.entity.User;
import com.Sailaja .novapay.repository.TransactionRepository;
import com.Sailaja .novapay.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public List<TransactionResponse> getTransactionHistory(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return transactionRepository.findBySenderOrReceiver(user, user)
                .stream()
                .map(transaction -> TransactionResponse.builder()
                        .type(transaction.getType())
                        .amount(transaction.getAmount())
                        .senderEmail(
                                transaction.getSender() != null
                                        ? transaction.getSender().getEmail()
                                        : null
                        )
                        .receiverEmail(
                                transaction.getReceiver() != null
                                        ? transaction.getReceiver().getEmail()
                                        : null
                        )
                        .transactionTime(transaction.getTransactionTime())
                        .build())
                .toList();    }
}
