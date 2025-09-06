package com.orderfy.auth_service.Auth.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AuthRequest {
    private String cpf;
    private String password;
    private String restaurantId;
}