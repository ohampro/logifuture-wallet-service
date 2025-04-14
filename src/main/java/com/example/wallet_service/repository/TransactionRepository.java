package com.example.wallet_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wallet_service.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    
    Optional<Transaction> findByWalletIdAndBetIdAndType(String walletId, String betId, String type);

    List<Transaction> findAllByWalletId(String walletId);

}
