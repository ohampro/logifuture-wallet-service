package com.example.wallet_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.wallet_service.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, String> {
    
}
