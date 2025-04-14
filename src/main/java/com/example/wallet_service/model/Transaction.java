package com.example.wallet_service.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Transaction {

    // Transaction ID (UUID)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Uncomment if using JPA
    private String id;

    // Wallet ID (UUID) - Foreign Key to Wallet
    private String walletId;

    // Transaction type (DEBIT / CREDIT)
    private String type;

    // Amount (BigDecimal)
    private double amount;

    // Bet ID (String / UUID) - also used for idempotency
    private String betId;
    
    // Created at timestamp (Instant)
    private Instant createdAt;

    public Transaction(String id, String walletId, String type, double amount, String betId, Instant createdAt) {
        this.id = id;
        this.walletId = walletId;
        this.type = type;
        this.amount = amount;
        this.betId = betId;
        this.createdAt = createdAt;
    }
    
}