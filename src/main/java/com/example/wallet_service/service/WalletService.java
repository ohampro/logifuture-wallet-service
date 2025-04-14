package com.example.wallet_service.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.wallet_service.model.Transaction;
import com.example.wallet_service.model.Wallet;
import com.example.wallet_service.repository.TransactionRepository;
import com.example.wallet_service.repository.WalletRepository;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;
    
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
    @Transactional
    public double debit(String userId, String betId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        
        Wallet wallet = walletRepository.findById(userId).orElseThrow();
        double balance = wallet.getBalance();

        // Check if the debit exists
        Optional<Transaction> lastDebit = transactionRepository.findByWalletIdAndBetIdAndType(
            userId,
            betId,
            "debit"
        );

        // Idempotency check:
        if (lastDebit.isPresent()){
            return balance;
        }

        if (balance < amount) {
            throw new UnsupportedOperationException("Insufficient balance");
        }

        // Insert Transaction Record
        Transaction debit = new Transaction();
        debit.setWalletId(userId);
        debit.setBetId(betId);
        debit.setType("debit");
        debit.setAmount(amount);
        transactionRepository.save(debit);

        wallet.setBalance(balance - amount);
        walletRepository.save(wallet);
        
        return wallet.getBalance();
    }

}
