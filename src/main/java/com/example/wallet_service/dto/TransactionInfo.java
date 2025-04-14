package com.example.wallet_service.dto;

public class TransactionInfo {

    public final String userId;
    public final double amount;

    public TransactionInfo(String userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }
    
}
