package com.example.wallet_service.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.wallet_service.dto.TransactionInfo;
import com.example.wallet_service.service.WalletService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(WalletController.class)
class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WalletService walletService;

    @Test
    void getBalance_returnsBalance() throws Exception {
        String userId = "user123";
        double balance = 100.0;

        when(walletService.getBalance(userId)).thenReturn(balance);
            
        mockMvc.perform(get("/users/{userId}/balance", userId))
            .andExpect(status().isOk())
            .andExpect(content().string("{\"balance\":100.0}"));
    }
    
    @Test
    void getBalance_returnsError() throws Exception {
        String userId = "user1";

        when(walletService.getBalance(userId))
            .thenThrow(new NoSuchElementException("User not found"));
            
        mockMvc.perform(get("/users/{userId}/balance", userId))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").value("User not found"))
            .andExpect(jsonPath("$.status").value(404));
    }
    
    @Test
    void debit_reduceBalance() throws Exception {
        String userId = "user123";
        String betId = "bet123";
        double balance = 100.0;
        double amount = 50.0;
        TransactionInfo transactionInfo = new TransactionInfo(userId, betId, amount);

        when(walletService.getBalance(userId)).thenReturn(balance);
        when(walletService.debit(userId, betId, amount)).thenReturn(amount);
            
        mockMvc.perform(
                post("/transactions/debit", transactionInfo)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(transactionInfo))
            )
            .andExpect(status().isOk())
            .andExpect(content().string("{\"balance\":50.0}"));
    }
}