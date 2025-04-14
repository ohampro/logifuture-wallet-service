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

}
