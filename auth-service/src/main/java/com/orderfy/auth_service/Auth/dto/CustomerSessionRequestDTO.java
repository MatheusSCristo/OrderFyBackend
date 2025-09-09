package com.orderfy.auth_service.Auth.dto;

public record CustomerSessionRequestDTO(
        String restaurantId,
        String tableId,
        String clientName
) {
}
