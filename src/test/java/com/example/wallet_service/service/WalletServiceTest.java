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
import com.example.wallet_service.model.Wallet;
import com.example.wallet_service.repository.WalletRepository;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

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
        double initialBalance = 100.0; 
        double debitAmount = 50.0;

        Wallet wallet = new Wallet(userId, initialBalance);
        when(walletRepository.findById(userId)).thenReturn(Optional.of(wallet));

        // Act
        double balance = walletService.debit(userId, debitAmount);

        // Assert
        assertThat(balance).isEqualTo(initialBalance - debitAmount);
	}

    @Test
    void debitShouldThrowExceptionForInsufficientBalance() {
        // Arrange
        String userId = "user123";
        double initialBalance = 100.0; 
        double debitAmount = 150.0; 

        Wallet wallet = new Wallet(userId, initialBalance);
        when(walletRepository.findById(userId)).thenReturn(Optional.of(wallet));

        // Act and Assert
        assertThrows(UnsupportedOperationException.class, () -> {
            walletService.debit(userId, debitAmount);
        });
    }

    @Test
    void debitShouldThrowExceptionForNegativeAmount() {
        // Arrange
        String userId = "user123";
        double debitAmount = -50.0; 

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            walletService.debit(userId, debitAmount);
        });
    }
    
    @Test
    void debitShouldThrowExceptionForZeroAmount() {
        // Arrange
        String userId = "user123";
        double debitAmount = 0.0; 

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            walletService.debit(userId, debitAmount);
        });
    }

}
