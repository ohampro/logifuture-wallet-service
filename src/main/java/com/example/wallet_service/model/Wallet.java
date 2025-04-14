package com.example.wallet_service.model;

import java.time.Instant;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String userId;

    private double balance;

    private Instant createdAt;

    @Version
    private Long version;
    
    public Wallet(String userId, double balance) {
        this.userId = userId;
        this.balance = balance;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }
    
}
