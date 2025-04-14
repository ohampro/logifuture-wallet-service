package com.example.wallet_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.wallet_service.dto.TransactionInfo;
import com.example.wallet_service.service.WalletService;

@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;
    
    public record BalanceResponse(double balance) {}
    @GetMapping("/users/{userId}/balance")
    public BalanceResponse getBalance(@PathVariable String userId) {
        double balance = walletService.getBalance(userId);
        return new BalanceResponse(balance);
    }
    
    @PostMapping("/transactions/debit")
    public BalanceResponse debit(@RequestBody TransactionInfo transactionInfo) {
        double balance = walletService.debit(transactionInfo.userId, transactionInfo.betId, transactionInfo.amount);
        return new BalanceResponse(balance);
    }

}
