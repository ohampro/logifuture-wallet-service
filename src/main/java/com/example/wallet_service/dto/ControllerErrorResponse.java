package com.example.wallet_service.dto;

import lombok.Getter;

@Getter
public class ControllerErrorResponse {
    private String message;
    private int status;
    private String timestamp;

    public ControllerErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.timestamp = java.time.ZonedDateTime.now().toInstant().toString();
    }
}