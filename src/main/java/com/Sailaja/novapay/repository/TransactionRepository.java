package com.Sailaja .novapay.repository;

import com.Sailaja .novapay.entity.Transaction;
import com.Sailaja .novapay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findBySenderOrReceiver(User sender, User receiver);

}
