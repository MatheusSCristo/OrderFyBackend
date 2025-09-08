package com.orderfy.auth_service.Auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String employeeName;
    private String restaurantId;
    private String cpf;


}