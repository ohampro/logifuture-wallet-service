package com.example.wallet_service.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionInfo {

    public final String userId;
    public final String betId;
    public final double amount;
    
}
