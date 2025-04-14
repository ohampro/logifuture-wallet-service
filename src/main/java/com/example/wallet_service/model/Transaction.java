package com.example.wallet_service.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String walletId;

    // Transaction type (DEBIT / CREDIT)
    private String type;

    private double amount;

    // Bet ID (UUID) - also used for idempotency
    private String betId;
    
    private Instant createdAt;

    public Transaction(String id, String walletId, String type, double amount, String betId, Instant createdAt) {
        this.id = id;
        this.walletId = walletId;
        this.type = type;
        this.amount = amount;
        this.betId = betId;
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }
    
}