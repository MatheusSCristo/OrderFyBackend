package com.orderfy.auth_service.Auth.dto;

public record CustomerSessionResponseDTO(
        String accessToken,
        String clientName,
        String sessionId
) {
}
