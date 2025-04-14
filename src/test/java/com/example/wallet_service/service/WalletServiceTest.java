package com.example.wallet_service.service;

import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.wallet_service.model.Transaction;
import com.example.wallet_service.model.Wallet;
import com.example.wallet_service.repository.TransactionRepository;
import com.example.wallet_service.repository.WalletRepository;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private WalletService walletService;
    
    @Test
	void shouldGetBalance() {
        // Arrange
        String userId = "user123";
        double initialBalance = 100.0; 

        Wallet wallet = new Wallet(userId, initialBalance);
        when(walletRepository.findById(userId)).thenReturn(Optional.of(wallet));

        // Act
        double balance = walletService.getBalance(userId);

        // Assert
        assertThat(balance).isEqualTo(initialBalance);
	}

    @Test
	void shouldThrowExceptionForInvalidUserId() {
        // Arrange
        String userId = "user123";

        when(walletRepository.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> {
            walletService.getBalance(userId);
        });
	}

    // Test for debit method ----------------------------------------------------
    @Test
	void debitShould_decreaseBalance() {
        // Arrange
        String userId = "user123";
        String betId = "bet123";
        double initialBalance = 100.0; 
        double debitAmount = 50.0;

        Wallet wallet = new Wallet(userId, initialBalance);
        when(walletRepository.findById(userId)).thenReturn(Optional.of(wallet));

        // Act
        double balance = walletService.debit(userId, betId, debitAmount);

        // Assert
        assertThat(balance).isEqualTo(initialBalance - debitAmount);
	}

    @Test
    void debitShouldThrowExceptionForInsufficientBalance() {
        // Arrange
        String userId = "user123";
        String betId = "bet123";
        double initialBalance = 100.0; 
        double debitAmount = 150.0; 

        Wallet wallet = new Wallet(userId, initialBalance);
        when(walletRepository.findById(userId)).thenReturn(Optional.of(wallet));

        // Act and Assert
        assertThrows(UnsupportedOperationException.class, () -> {
            walletService.debit(userId, betId, debitAmount);
        });
    }

    @Test
    void debitShouldThrowExceptionForNegativeAmount() {
        // Arrange
        String userId = "user123";
        String betId = "bet123";
        double debitAmount = -50.0; 

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            walletService.debit(userId, betId, debitAmount);
        });
    }
    
    @Test
    void debitShouldThrowExceptionForZeroAmount() {
        // Arrange
        String userId = "user123";
        String betId = "bet123";
        double debitAmount = 0.0; 

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            walletService.debit(userId, betId, debitAmount);
        });
    }

    // Test for credit method ----------------------------------------------------
    @Test
    void creditShould_increaseBalance() {
        // Arrange
        String userId = "user123";
        String betId = "bet123";
        double initialBalance = 100.0; 
        double debitAmount = 50.0;

        Wallet wallet = new Wallet(userId, initialBalance);
        when(walletRepository.findById(userId)).thenReturn(Optional.of(wallet));
        
        Transaction debit = new Transaction();
        debit.setWalletId(userId);
        debit.setBetId(betId);
        debit.setType("debit");
        when(transactionRepository.findByWalletIdAndBetIdAndType(userId, betId, "debit"))
        .thenReturn(Optional.of(debit));

        when(transactionRepository.findByWalletIdAndBetIdAndType(userId, betId, "credit"))
        .thenReturn(Optional.empty());

        // Act
        double balance = walletService.credit(userId, betId, debitAmount);

        // Assert
        assertThat(balance).isEqualTo(initialBalance + debitAmount);
    }

    @Test
    void credit_idempotancy() {
        // Arrange
        String userId = "user123";
        String betId = "bet123";
        double initialBalance = 100.0; 
        double debitAmount = 50.0;

        Wallet wallet = new Wallet(userId, initialBalance);
        when(walletRepository.findById(userId)).thenReturn(Optional.of(wallet));
        
        Transaction credit = new Transaction();
        credit.setWalletId(userId);
        credit.setBetId(betId);
        credit.setType("credit");
        when(transactionRepository.findByWalletIdAndBetIdAndType(userId, betId, "credit"))
        .thenReturn(Optional.of(credit));

        // Act
        double balance = walletService.credit(userId, betId, debitAmount);

        // Assert
        assertThat(balance).isEqualTo(initialBalance);
    }

    
    @Test
    void creditError_withoutDebit() {
        // Arrange
        String userId = "user123";
        String betId = "bet123";
        double initialBalance = 100.0; 
        double debitAmount = 50.0;

        Wallet wallet = new Wallet(userId, initialBalance);
        when(walletRepository.findById(userId)).thenReturn(Optional.of(wallet));
        
        when(transactionRepository.findByWalletIdAndBetIdAndType(userId, betId, "credit"))
        .thenReturn(Optional.empty());
        
        when(transactionRepository.findByWalletIdAndBetIdAndType(userId, betId, "debit"))
        .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            walletService.credit(userId, betId, debitAmount);
        });
    }
}
