package com.example.wallet_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wallet_service.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    
}
