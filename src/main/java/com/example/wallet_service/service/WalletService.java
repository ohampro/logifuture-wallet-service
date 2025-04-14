package com.example.wallet_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wallet_service.model.Wallet;
import com.example.wallet_service.repository.WalletRepository;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;
    
    /**
     * Retrieves the balance of a user's wallet.
     *
     * @param userId the ID of the user whose wallet balance is to be retrieved
     * @return the balance of the user's wallet
     * @throws NoSuchElementException if the wallet does not exist
     */
    public double getBalance(String userId) {
        Wallet wallet = walletRepository.findById(userId).orElseThrow();
        return wallet.getBalance();
    }

    /**
     * Debits a specified amount from a user's wallet.
     *
     * @param userId the ID of the user whose wallet is to be debited
     * @param amount the amount to debit from the user's wallet
     * @throws UnsupportedOperationException if the method is not implemented
     */
    
    public double debit(String userId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        
        Wallet wallet = walletRepository.findById(userId).orElseThrow();
        double balance = wallet.getBalance();

        if (balance < amount) {
            throw new UnsupportedOperationException("Insufficient balance");
        }

        wallet.setBalance(balance - amount);
        walletRepository.save(wallet);
        
        return wallet.getBalance();
    }

}
